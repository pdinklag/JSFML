@echo off
REM This batch file is used by the Windows build targets to automatically find the location of the MS Visual Studio.

set UNKNOWN="?"
set RESULT=%UNKNOWN%

if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\Software\Microsoft\Microsoft SDKs\Windows\v7.1" "InstallationFolder" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\Software\Microsoft\Microsoft SDKs\Windows\v7.0A" "InstallationFolder" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\Software\Microsoft\Microsoft SDKs\Windows\v6.0A" "InstallationFolder" 2> NUL

echo %RESULT%
goto:eof

:regQuery
for /F "tokens=2* delims=	 " %%A in ('reg query "%~1" /v %~2') do set RESULT=%%B
goto:eof
