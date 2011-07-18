@echo off
setlocal

set JAVAC="%JAVA_HOME%\bin\javac.exe"
set CLASSPATH=.;..\Microsoft-HpcSession-3.0.jar;%CXF_HOME%\lib\cxf-manifest.jar;

@echo on

cd src

FOR /F %%i IN ('dir /b/s *.java') DO %javac% -Djava.endorsed.dirs="%CXF_HOME%\lib\endorsed" %%i

"%JAVA_HOME%\bin\jar.exe" cvfm ..\JavaEchoSvc.jar manifestecho org com

