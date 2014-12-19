@echo off
REM This batch file is used by the Windows build targets to automatically find the location of the MS Visual Studio.

set UNKNOWN="?"
set RESULT=%UNKNOWN%

REM MSVC++ 2013 (12.0)
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Microsoft\VisualStudio\12.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Wow6432Node\Microsoft\VisualStudio\12.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Microsoft\VisualStudio\12.0\Setup\VC" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Wow6432Node\Microsoft\VisualStudio\12.0\Setup\VC" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Microsoft\VCExpress\12.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Wow6432Node\Microsoft\VCExpress\12.0\Setup\VS" "ProductDir" 2> NUL

echo %RESULT%
goto:eof

:regQuery
for /F "tokens=2* delims=	 " %%A in ('reg query "%~1" /v %~2') do set RESULT=%%B
goto:eof
