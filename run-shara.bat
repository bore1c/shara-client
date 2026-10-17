@echo off
setlocal

REM SHARA Client helper - Windows launcher
REM Usage: run-shara.bat [run|build|help]
n:: Ensure we're running from repository root (where gradlew.bat is)
if not exist "%~dp0gradlew.bat" (
  echo gradlew.bat not found in this directory. Please run this script from the repository root.
  pause
  exit /b 1
)
n:: Check Java availability
java -version >nul 2>&1
if ERRORLEVEL 1 (
  echo Java not found or not on PATH. Please install JDK 21 and make sure "java" is available in PATH.
  echo You can download from https://adoptium.net/ or https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
  pause
  exit /b 1
)
nset ACTION=%1nif "%ACTION%"=="" set ACTION=run
nif /I "%ACTION%"=="run" goto :runClientnif /I "%ACTION%"=="build" goto :buildJarnif /I "%ACTION%"=="help" goto :helpnnecho Unknown action: %ACTION%
echo Use: run-shara.bat [run|build|help]
pause
exit /b 1
n:runClientnecho Running SHARA Client in dev mode (Gradle Loom runClient)...n:: Use wrapper from script directoryncall "%~dp0gradlew.bat" runClientnif ERRORLEVEL 1 (
  echo runClient failed. See output above for details.
  pause
  exit /b %ERRORLEVEL%
)necho runClient finished.
pause
exit /b 0
n:buildJarnecho Building release JAR (Gradle build)...ncall "%~dp0gradlew.bat" buildnif ERRORLEVEL 1 (
  echo Build failed. See Gradle output.
  pause
  exit /b %ERRORLEVEL%
)
echo Build succeeded. Built artifacts are in build\libs\necho Example: build\libs\shara-client-0.1.0.jar
pause
exit /b 0
n:helpnecho SHARA Client launcher helper
echo.
echo Usage: run-shara.bat [run|build|help]
echo.
echo run   - Run the mod in dev mode (./gradlew runClient)
echo build - Build a release JAR (./gradlew build)
echo help  - Show this help text
pause
exit /b 0
