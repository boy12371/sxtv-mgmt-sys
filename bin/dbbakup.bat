@echo off
set backdir=@backdir@
set vmstop=@vmstop@
set d1=%date:~0,4%
set d2=%date:~5,2%
set d3=%date:~8,2%
set date=%d1%%d2%%d3%
set fdir=%d1%-%d2%-%d3%
md %backdir%\%fdir%
%vmstop%\bin\mysqldump -hlocalhost -u@DATABASE_USERNAME@ -p@DATABASE_USERNAME@ -R sxtvdb > %backdir%\%fdir%\%date%.sql
