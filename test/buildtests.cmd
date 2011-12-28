    @echo off
    set JAVAC="%JAVA_HOME%\bin\javac.exe"
    set JAR="%JAVA_HOME%\bin\jar.exe"
    set CLASSPATH=.;%CXF_HOME%\lib\cxf-manifest.jar;junit.jar;org.hamcrest.core_1.1.0.v20090501071000.jar;AITestLibService.jar;..\..\SOA\src
    
    rd /s /q bin
    md bin
    echo Compiling 
    %javac%  -sourcepath functiontest\*.java -d bin
    %javac%  fullpass\Full.java -d bin
    echo Done
   
    