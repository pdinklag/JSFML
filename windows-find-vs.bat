@echo off
REM This batch file is used by the Windows build targets to automatically find the location of the MS Visual Studio.
for /F "tokens=2* delims=	 " %%A in ('reg query "HKCU\Software\Microsoft\VCExpress\10.0_Config" /v ShellFolder') DO SET VS_DIR=%%B
echo %VS_DIR%
