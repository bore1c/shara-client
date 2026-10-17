@echo off
setlocal

REM SHARA Client helper - Windows launcher (improved)
REM Usage: run-shara.bat [run|build|help]

:: If gradlew.bat missing, try to bootstrap using local gradle (gradle wrapper)
set ROOT=%~dp0nset WRAPPER=%ROOT%gradlew.bat
n:: Check Java availability first (required by Gradle)
java -version >nul 2>&1
if ERRORLEVEL 1 (
  echo Java not found or not on PATH. Please install JDK 21 and make sure "java" is available in PATH.
  echo You can download from https://adoptium.net/ or https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
  pause
  exit /b 1
)
n:: If gradlew.bat missing, try to run 'gradle wrapper' if gradle is installed
if not exist "%WRAPPER%" (
  echo gradlew.bat not found in repository root.
  where gradle >nul 2>&1
  if ERRORLEVEL 1 (
    echo Neither gradlew.bat nor system gradle found.
    echo Please install Gradle CLI or generate the wrapper from another machine, or use GitHub Actions build.
    echo Instructions: https://gradle.org/install/
    pause
    exit /b 1
  ) else (
    echo Found system 'gradle'. Attempting to generate Gradle wrapper (this requires network access)...
    pushd "%ROOT%"
    gradle wrapper
    popd
    if exist "%WRAPPER%" (
      echo Gradle wrapper created.
    ) else (
      echo Failed to create gradle wrapper. You can still run 'gradle' directly if installed.
    )
  )
)
nset ACTION=%1nif "%ACTION%"=="" set ACTION=run
nif /I "%ACTION%"=="run" goto :runClientnif /I "%ACTION%"=="build" goto :buildJarnif /I "%ACTION%"=="help" goto :help
necho Unknown action: %ACTION%
echo Use: run-shara.bat [run|build|help]
pause
exit /b 1
n:runClientnecho Running SHARA Client in dev mode (Gradle Loom runClient)...
if exist "%WRAPPER%" (
  call "%WRAPPER%" runClient
) else (
  gradle runClient
)
if ERRORLEVEL 1 (
  echo runClient failed. See output above for details.
  pause
  exit /b %ERRORLEVEL%
)
echo runClient finished.
pause
exit /b 0
n:buildJarnecho Building release JAR (Gradle build)...
if exist "%WRAPPER%" (
  call "%WRAPPER%" build
) else (
  gradle build
)
if ERRORLEVEL 1 (
  echo Build failed. See Gradle output.
  pause
  exit /b %ERRORLEVEL%
)
echo Build succeeded. Built artifacts are in build\libs\
echo Example: build\libs\shara-client-0.1.0.jar
pause
exit /b 0
n:helpnecho SHARA Client launcher helper (improved)
echo.
echo Usage: run-shara.bat [run|build|help]
echo.
echo run   - Run the mod in dev mode (./gradlew runClient or 'gradle runClient' if wrapper missing)
echo build - Build a release JAR (./gradlew build or 'gradle build')
echo help  - Show this help text
pause
exit /b 0
