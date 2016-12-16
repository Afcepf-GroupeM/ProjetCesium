@ECHO OFF
echo +++++++++++++++++++++++++++++++++++++
echo ++  Creation de la base de donnees ++
echo +++++++++++++++++++++++++++++++++++++
echo.
echo +  Entrer le chemin d'installation de MySQL:
set /p mysqlpath= -^> 
echo.

echo +  Entrer nom d'utilisateur (root?):
set /p user= -^> 
echo.

echo +  Entrer le mot de passe:
set /p password= -^> 
echo. 

SET mysql_exe= "%mysqlpath%\bin\mysql.exe"

SET sql_file= EcommerceDB.sql

echo +  MySQL.exe :               %mysql_exe%
echo +  Fichier SQL a importer :  %sql_file%
echo. 

CALL %mysql_exe% --user=%user% --password=%password% < %sql_file%
IF %ERRORLEVEL% NEQ 0 ECHO Error executing SQL file
echo +  Importation terminee.
echo.
PAUSE
EXIT