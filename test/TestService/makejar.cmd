@echo off
setlocal

set JAVA_HOME=%ProgramFiles%\Java\jdk1.6.0_23
set JAVAC="%JAVA_HOME%\bin\javac.exe"
set CXF_HOME=c:\java\apache-cxf-2.4.0
set CLASSPATH=.;..\Microsoft-HpcSession-3.0.jar;%CXF_HOME%\lib\cxf-manifest.jar


dir src.list > nul 2>&1 && del src.list

echo Compiling
FOR /F %%i IN ('dir /b/s *.java') DO echo %%i >> src.list 
%javac% -Djava.endorsed.dirs="%CXF_HOME%\lib\endorsed" @src.list

echo Packing
"%JAVA_HOME%\bin\jar.exe" cf JavaTestService.jar org com

echo Done

