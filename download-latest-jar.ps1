<#
PowerShell script to download the latest successful build artifact (shara-client JAR)
from the GitHub Actions workflow "Build and upload JAR" and extract the JAR into the
current directory. No authentication required for public repos (rate-limited).

Usage (PowerShell):
  .\download-latest-jar.ps1
  .\download-latest-jar.ps1 -Owner "bore1c" -Repo "shara-client" -WorkflowFile "build-and-artifact.yml"
#>
param(
    [string]$Owner = "bore1c",
    [string]$Repo = "shara-client",
    [string]$WorkflowFile = "build-and-artifact.yml",
    [string]$ArtifactName = "shara-client-jar",
    [string]$OutDir = ".\artifacts"
)

function Abort($msg) { Write-Host "ERROR: $msg" -ForegroundColor Red; exit 1 }

$headers = @{ "User-Agent" = "shara-client-downloader" }

Write-Host "Querying GitHub Actions for latest successful run of workflow $WorkflowFile..."
$workflowRunsUrl = "https://api.github.com/repos/$Owner/$Repo/actions/workflows/$WorkflowFile/runs?status=success&per_page=1"
try {
    $runs = Invoke-RestMethod -Uri $workflowRunsUrl -Headers $headers -UseBasicParsing -ErrorAction Stop
} catch {
    Abort "Failed to query workflow runs: $($_.Exception.Message)"
}
if (-not $runs.workflow_runs -or $runs.total_count -eq 0) { Abort "No successful workflow runs found for $WorkflowFile" }
$run = $runs.workflow_runs[0]
Write-Host "Found run id: $($run.id)  (event: $($run.event))"

$runId = $run.id
$artifactsUrl = "https://api.github.com/repos/$Owner/$Repo/actions/runs/$runId/artifacts"
try { $arts = Invoke-RestMethod -Uri $artifactsUrl -Headers $headers -UseBasicParsing -ErrorAction Stop } catch { Abort "Failed to list artifacts: $($_.Exception.Message)" }
if (-not $arts.artifacts -or $arts.total_count -eq 0) { Abort "No artifacts found for run $runId" }
$artifact = $arts.artifacts | Where-Object { $_.name -eq $ArtifactName } | Select-Object -First 1
if (-not $artifact) { Abort "Artifact named '$ArtifactName' not found in run $runId" }
Write-Host "Found artifact id: $($artifact.id) size: $([math]::Round($artifact.size_in_bytes/1KB)) KB"

$zipUrl = "https://api.github.com/repos/$Owner/$Repo/actions/artifacts/$($artifact.id)/zip"
$OutZip = Join-Path -Path $env:TEMP -ChildPath "shara_artifact_$($artifact.id).zip"
Write-Host "Downloading artifact zip to $OutZip ..."
try {
    Invoke-RestMethod -Uri $zipUrl -Headers $headers -OutFile $OutZip -UseBasicParsing -ErrorAction Stop
} catch {
    Abort "Failed to download artifact zip: $($_.Exception.Message)"
}

# Extract
if (-Not (Test-Path $OutDir)) { New-Item -ItemType Directory -Path $OutDir | Out-Null }
Write-Host "Extracting artifact to $OutDir ..."
try { Expand-Archive -Path $OutZip -DestinationPath $OutDir -Force -ErrorAction Stop } catch { Abort "Failed to extract zip: $($_.Exception.Message)" }

# Find JAR
$jar = Get-ChildItem -Path $OutDir -Recurse -Filter "*.jar" | Select-Object -First 1
if (-not $jar) { Abort "No JAR found inside artifact" }
Write-Host "Found JAR: $($jar.FullName)"
$dest = Join-Path -Path (Get-Location) -ChildPath $jar.Name
Write-Host "Copying JAR to current directory: $dest"
Copy-Item -Path $jar.FullName -Destination $dest -Force
Write-Host "Done. You can now place the JAR into your Minecraft mods folder or inspect it: $dest" -ForegroundColor Green

# Cleanup
Remove-Item -Path $OutZip -Force -ErrorAction SilentlyContinue
Write-Host "Temporary files cleaned up."