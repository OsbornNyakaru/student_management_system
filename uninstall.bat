@echo off
setlocal enabledelayedexpansion
title Student Management System Uninstaller

:: Set colors
set "RED=[91m"
set "GREEN=[92m"
set "YELLOW=[93m"
set "BLUE=[94m"
set "RESET=[0m"

:: Clear screen
cls

:: Show welcome message
echo %BLUE%╔════════════════════════════════════════════════════════════════════════════╗
echo ║                   Student Management System Uninstaller                     ║
echo ╚════════════════════════════════════════════════════════════════════════════╝%RESET%
echo.

:: Ask for confirmation
echo %YELLOW%Are you sure you want to uninstall Student Management System?%RESET%
echo This will remove the desktop shortcut and clean up the application.
echo.
set /p "CONFIRM=Type 'yes' to confirm: "

if /i not "%CONFIRM%"=="yes" (
    echo %YELLOW%Uninstallation cancelled.%RESET%
    pause
    exit /b 0
)

:: Remove desktop shortcut
echo %YELLOW%Removing desktop shortcut...%RESET%
set "DESKTOP=%USERPROFILE%\Desktop"
set "SHORTCUT=%DESKTOP%\Student Management System.lnk"

if exist "%SHORTCUT%" (
    del "%SHORTCUT%"
    echo %GREEN%✓ Desktop shortcut removed%RESET%
) else (
    echo %YELLOW%Desktop shortcut not found%RESET%
)

:: Clean up build files
echo %YELLOW%Cleaning up build files...%RESET%
if exist "target" (
    rmdir /s /q "target"
    echo %GREEN%✓ Build files removed%RESET%
)

:: Show completion message
echo.
echo %BLUE%╔════════════════════════════════════════════════════════════════════════════╗
echo ║                     Uninstallation Completed Successfully!                    ║
echo ╚════════════════════════════════════════════════════════════════════════════╝%RESET%
echo.
echo %GREEN%Student Management System has been uninstalled successfully.%RESET%
echo.
echo %YELLOW%Press any key to exit...%RESET%
pause >nul 