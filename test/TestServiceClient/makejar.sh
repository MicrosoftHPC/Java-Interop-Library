#!bin/sh
export CXF_HOME=/usr/java/apache-cxf-2.4.0
export JAVA_HOME=/usr/java/jdk1.6.0_23
export CLASSPATH=.:Microsoft-HpcSession-3.0.jar:$CXF_HOME/lib/cxf-manifest.jar

$CXF_HOME/bin/wsdl2java tempuri.org.wsdl

echo Compiling
find . -name *.java -print | xargs $JAVA_HOME/bin/javac -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed"

echo Packing

$JAVA_HOME/bin/jar cf JavaTestServiceClient.jar org *.wsdl *.xsd

echo Done

