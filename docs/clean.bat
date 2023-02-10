REM Turn echo off and clear the screen.
@echo off
cls

REM Mr. SP. Sithungu, Mr. A. Maganlal
REM Mr. KM KIYO (I made slight alterations)

REM KM KIYO, 221049296, Practical 09

REM Good batch file coding practice.
setlocal enabledelayedexpansion

REM Move to correct folder.
cd ..

REM Variables for batch
set ERRMSG=
set BIN=.\bin
set DOCS=.\docs


REM Clean all class files from bin folder and the JavaDocs folder from docs folder.
:CLEAN
echo ------CLEANING PROJECT------
DEL /S %BIN%\*.class
RMDIR /Q /S %BIN%\csc2a
RMDIR /Q /S %DOCS%\JavaDocs

IF /I "%ERRORLEVEL%" NEQ "0" (
    echo !!! Error Cleaning Project !!!
)
echo ------CLEANING DONE------
PAUSE
