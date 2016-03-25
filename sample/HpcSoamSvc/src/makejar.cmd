@echo off
setlocal

set JAVA_HOME=%ProgramFiles%\Java\jdk1.8.0_60
set CXF_HOME=c:\java\apache-cxf-2.7.17
set SOA_HOME=C:\HPCSOA\SoamSvcHost
set CLASSPATH=.;%SOA_HOME%\Microsoft-HpcSession-3.0.jar;%SOA_HOME%\HpcSoam.jar;%CXF_HOME%\lib\cxf-manifest.jar;
set JAVAC="%JAVA_HOME%\bin\javac.exe"


FOR /F %%i IN ('dir /b/s *.java') DO %javac% -Djava.endorsed.dirs="%CXF_HOME%\lib\endorsed" %%i

"%JAVA_HOME%\bin\jar.exe" cvfm HpcSoamSvc.jar manifest org com

copy /Y HpcSoamSvc.jar %SOA_HOME%
