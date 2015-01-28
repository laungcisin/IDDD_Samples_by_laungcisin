@echo off
rem -------------------------------------------
rem IDDD collaboration views database setup
rem -------------------------------------------

echo Creating IDDD Collaboration Event Store and Views database...
mysql -uroot -proot < collaboration.sql

echo Completed
