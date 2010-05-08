@echo off
set backdir=@backdir@
set vmstop=@vmstop@
for /f "delims=" %%i in ('date /t') do set "ddd=%%i"
set d1=%ddd:~0,4%
set d2=%ddd:~5,2%
set d3=%ddd:~8,2%
set date=%d1%%d2%%d3%
set fdir=%d1%-%d2%-%d3%
md "%backdir%\%fdir%"
"%vmstop%\bin\mysqldump" -hlocalhost -u@DATABASE_USERNAME@ -p@DATABASE_USERNAME@ -R sxtvdb > "%backdir%\%fdir%\%date%.sql"
