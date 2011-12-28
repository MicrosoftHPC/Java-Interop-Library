@echo off
setlocal

set JAVA_HOME=%ProgramFiles%\Java\jdk1.6.0_23
set JAVAC="%JAVA_HOME%\bin\javac.exe"
set JAR="%JAVA_HOME%\bin\jar.exe"
set CXF_HOME=c:\java\apache-cxf-2.4.0
set CLASSPATH=.;..\Microsoft-HpcSession-3.0.jar;%CXF_HOME%\lib\cxf-manifest.jar

call %CXF_HOME%\bin\wsdl2java tempuri.org.wsdl

dir src.list > nul 2>&1 && del src.list
FOR /F %%i IN ('dir /b/s *.java') DO echo %%i >> src.list 
%javac% -Djava.endorsed.dirs="%CXF_HOME%\lib\endorsed" @src.list


%jar% cf JavaTestServiceClient.jar org *.wsdl *.xsd

