@REM Please make sure you have svcutil.exe in your path.
@REM Svcutil.exe is part of windows SDK.

@echo off
setlocal

set serviceName=CcpEchoSvc
set dllFile=EchoSvcLib.dll
set ServiceWSDL=tempuri.org.wsdl

set JAVA_HOME=c:\Program Files\Java\jdk1.6.0_18
set CXF_HOME=C:\java\apache-cxf-2.1.4

set JAVAC="%JAVA_HOME%\bin\javac.exe"
set CLASSPATH=.;%CXF_HOME%\lib\cxf-manifest.jar;

@echo on

svcutil %dllFile%

powershell -command "& {%~dp0EditWsdlFile.ps1 -servicename %serviceName% -wsdl %ServiceWSDL% }"

copy /y temp_%ServiceWSDL% %ServiceWSDL%

call %CXF_HOME%\bin\wsdl2java %ServiceWSDL%

FOR /F %%i IN ('dir /b/s *.java') DO %javac% %%i

"%JAVA_HOME%\bin\jar.exe" cf %serviceName%.jar org com *.wsdl *.xsd

