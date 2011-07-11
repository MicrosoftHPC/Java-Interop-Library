@echo off
setlocal

if [%1]==[] (
    echo Usage: to compile,  run: RunTest.cmd src
    echo Usage: to run test, run: RunTest.cmd run
    exit /b 0
)    

set JAVA_HOME=c:\Program Files\Java\jdk1.6.0_23
set CXF_HOME=C:\java\apache-cxf-2.4.0
set Keystore_Password=changeit
set CLASSPATH=.;Microsoft-HpcSession-3.0.jar;CcpEchoSvc.jar;%CXF_HOME%\lib\cxf-manifest.jar

if [%1]==[src] (
    "%JAVA_HOME%\bin\javac" -Djava.endorsed.dirs="%CXF_HOME%\lib\endorsed" Sample.java
)
if [%1]==[run] (
    "%JAVA_HOME%\bin\java" -Djavax.net.ssl.trustStore="%JAVA_HOME%\jre\lib\security\cacerts" -Djavax.net.ssl.trustStorePassword=%Keystore_Password% -Djava.endorsed.dirs="%CXF_HOME%\lib\endorsed" -Djava.util.logging.config.file=C:\Java\apache-cxf-2.1.4\etc\logging.properties Sample /scheduler headnode /username username /password yourpasswd /nrequests 1024
)


