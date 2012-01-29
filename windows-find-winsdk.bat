@echo off
REM This batch file is used by the Windows build targets to automatically find the location of the Windows SDK.
for /F "tokens=2* delims=	 " %%A in ('reg query "HKLM\Software\Microsoft\Microsoft SDKs\Windows" /v CurrentInstallFolder') DO SET SDK_DIR=%%B
echo %SDK_DIR%
