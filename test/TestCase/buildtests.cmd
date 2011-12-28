    @echo off
    set JAVA_HOME=%ProgramFiles%\Java\jdk1.6.0_23
    set CXF_HOME=c:\java\apache-cxf-2.4.0
    set JAVAC="%JAVA_HOME%\bin\javac.exe"
    set JAR="%JAVA_HOME%\bin\jar.exe"
    set CLASSPATH=.;%CXF_HOME%\lib\cxf-manifest.jar;junit.jar;org.hamcrest.core_1.1.0.v20090501071000.jar;JavaTestServiceClient.jar;..\Microsoft-HpcSession-3.0.jar
    
    echo Compiling 
    %javac%  -sourcepath functiontest\*.java 
    %javac%  fullpass\Full.java
    echo Done
   
    