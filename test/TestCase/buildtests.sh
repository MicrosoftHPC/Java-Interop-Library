#!bin/sh
export CXF_HOME=/usr/java/apache-cxf-2.4.0
export CLASSPATH=.:$CXF_HOME/lib/cxf-manifest.jar:./junit.jar:./org.hamcrest.core_1.1.0.v20090501071000.jar:./JavaTestServiceClient.jar:../Microsoft-HpcSession-3.0.jar

echo Compiling
find ./functiontest -name *.java -print | xargs javac  -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed"

find ./fullpass -name *.java -print | xargs javac  -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed"
echo Done

