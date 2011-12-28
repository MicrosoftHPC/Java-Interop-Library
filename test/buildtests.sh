#!bin/sh
export CXF_HOME=/usr/java/apache-cxf-2.4.0
export CLASSPATH=.:$CXF_HOME/lib/cxf-manifest.jar:./junit.jar:./org.hamcrest.core_1.1.0.v20090501071000.jar:./AITestLibService.jar:../../SOA/src

rm -rf bin
mkdir bin

echo Compiling
find ./functiontest -name *.java -print | xargs javac -d ./bin -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed"

find ./fullpass -name *.java -print | xargs javac -d ./bin -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed"
echo Done

