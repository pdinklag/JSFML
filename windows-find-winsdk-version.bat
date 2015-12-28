@echo off
REM This batch file is used by the Windows build targets to automatically find the location of the Windows SDK.

set UNKNOWN="?"
set RESULT=%UNKNOWN%

call:regQuery "HKLM\Software\WOW6432Node\Microsoft\Microsoft SDKs\Windows\v10.0" "ProductVersion" 2> NUL

echo %RESULT%
goto:eof

:regQuery
for /F "tokens=2* delims=	 " %%A in ('reg query "%~1" /v %~2') do set RESULT=%%B
goto:eof
