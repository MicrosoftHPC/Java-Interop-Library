@echo off

set JAVA_HOME=%ProgramFiles%\Java\jdk1.8.0_60
set CXF_HOME=c:\java\apache-cxf-2.7.17
set SOA_HOME=C:\HPCSOA\SoamSvcHost
set CLASSPATH=.;%CXF_HOME%\lib\cxf-manifest.jar

set JAVAC="%JAVA_HOME%\bin\javac.exe"
set JAR="%JAVA_HOME%\bin\jar.exe"
set JARFILEClient=Microsoft-HpcSession-3.0.jar
set JARFILEHost=Microsoft-HpcServiceHost-3.0.jar


echo Compiling
dir src.list > nul 2>&1 && del src.list

FOR /F %%i IN ('dir /b/s *.java') DO  echo %%i >> src.list
%javac% -Djava.endorsed.dirs="%CXF_HOME%\lib\endorsed" @src.list

echo Create Jar
%jar% cfm %JARFILEClient% manifestclient com\microsoft\schemas com\microsoft\hpc\*.class com\microsoft\hpc\*.java com\microsoft\hpc\brokercontroller com\microsoft\hpc\brokerlauncher com\microsoft\hpc\dataservice com\microsoft\hpc\exceptions com\microsoft\hpc\genericservice com\microsoft\hpc\properties com\microsoft\hpc\scheduler com\microsoft\hpc\session com\microsoft\hpc\sessionlauncher org FaultCodeMessageBundle.properties *.wsdl *.xsd StringTableBundle.properties

%jar% cfm %JARFILEHost% manifesthost com\microsoft\hpc\servicehost

echo Build completed

copy /Y Microsoft-HpcServiceHost-3.0.jar %SOA_HOME%
copy /Y Microsoft-HpcSession-3.0.jar %SOA_HOME%


