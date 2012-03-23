@echo off
REM This batch file is used by the Windows build targets to automatically find the location of the MS Visual Studio.

set NOTFOUND="?"
set RESULT=%UNKNOWN%

REM MSVC++ 2010 (10.0)
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Microsoft\VisualStudio\10.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Wow6432Node\Microsoft\VisualStudio\10.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Microsoft\VCExpress\10.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Wow6432Node\Microsoft\VCExpress\10.0\Setup\VS" "ProductDir" 2> NUL

REM MSVC++ 2008 (9.0)
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Microsoft\VisualStudio\9.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Wow6432Node\Microsoft\VisualStudio\9.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Microsoft\VCExpress\9.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Wow6432Node\Microsoft\VCExpress\9.0\Setup\VS" "ProductDir" 2> NUL

REM MSVC++ 2005 (8.0)
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Microsoft\VisualStudio\8.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Wow6432Node\Microsoft\VisualStudio\8.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Microsoft\VCExpress\8.0\Setup\VS" "ProductDir" 2> NUL
if "%RESULT%"=="%UNKNOWN%" call:regQuery "HKLM\SOFTWARE\Wow6432Node\Microsoft\VCExpress\8.0\Setup\VS" "ProductDir" 2> NUL

echo %RESULT%
goto:eof

:regQuery
for /F "tokens=2* delims=	 " %%A in ('reg query %~1 /v %~2') do set RESULT=%%B
goto:eof
