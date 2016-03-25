@echo off
setlocal

if [%1]==[] (
    echo Usage: to compile,  run: RunTest.cmd src
    echo Usage: to run test, run: RunTest.cmd run
    exit /b 0
)    

set JAVA_HOME=%ProgramFiles%\Java\jdk1.8.0_60
set CXF_HOME=C:\java\apache-cxf-2.7.17
set SOA_HOME=C:\HPCSOA\SoamSvcHost
set Keystore_Password=password
set CLASSPATH=.;%SOA_HOME%\Microsoft-HpcSession-3.0.jar;%CXF_HOME%\lib\cxf-manifest.jar;%SOA_HOME%\HpcSoam.jar;%SOA_HOME%\HpcSoamSvc.jar;%SOA_HOME%\SampleService.jar;%SOA_HOME%\jackson-all-1.9.0.jar

if [%1]==[src] (
    "%JAVA_HOME%\bin\javac" -cp %CLASSPATH% -Djava.endorsed.dirs="%CXF_HOME%\lib\endorsed" HelloWorld.java
)
if [%1]==[run] (
    "%JAVA_HOME%\bin\java" -cp %CLASSPATH% HelloWorld -Djavax.net.ssl.trustStore="%JAVA_HOME%\jre\lib\security\cacerts" -Djavax.net.ssl.trustStorePassword=%Keystore_Password%  -Djava.endorsed.dirs="%CXF_HOME%\lib\endorsed"
)


