# To run this script directly, run this in an elevated admin PowerShell prompt:
#     Invoke-WebRequest -UseBasicParsing https://raw.githubusercontent.com/isidore/RefactoringClass/master/Resources/install.windows.ps1 | Invoke-Expression

# This script is intended to setup a dev machine from scratch. Very useful for setting up a EC2 instance for mobbing.
#


#install chocolatey
iwr -useb cin.st | iex
Set-Service Audiosrv -StartupType Automatic

#install common tools
choco install -y vscode win-no-annoy anydesk.install git github-desktop anydesk.install googlechrome paint.net
start-process https://github.com/GreatWebGuy/MobTime/releases

#install project specific tools 
choco install -y notepadplusplus beyondcompare jdk8 intellijidea maven

# Clone repo
& "C:\Program Files\Git\cmd\git.exe" clone https://github.com/isidore/RefactoringClass.git C:\Code\RefactoringClass
& "C:\Program Files\Git\cmd\git.exe" clone https://github.com/isidore/GildedRose_Demo.git C:\Code\GildedRose_Demo

& "C:\Program Files\Git\cmd\git.exe" clone https://github.com/LearnWithLlew/KeyboardShortcutKatas.java.git C:\Code\KeyboardShortcutKatas
& "C:\Program Files\Git\cmd\git.exe" clone https://github.com/LearnWithLlew/DuplicationColoringBook.git C:\Code\DuplicationColoringBook
& "C:\Program Files\Git\cmd\git.exe" clone https://github.com/LearnWithLlew/DuplicationKata.java.git C:\Code\DuplicationKata
## Todo: Add early returns 

#Set desktop background image
 set-itemproperty -path "HKCU:Control Panel\Desktop" -name WallPaper -value c:\Code\TestingLegacyCodeCourse.cpp\Resources\desktop_background_dark.png
 RUNDLL32.EXE USER32.DLL,UpdatePerUserSystemParameters ,1 ,True

# Done
cls
echo "Done!"