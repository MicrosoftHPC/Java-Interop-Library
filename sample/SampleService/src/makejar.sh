#!/bin/sh

export JAVA_HOME=/usr/java/jdk1.8.0_60
export CXF_HOME=/opt/apache-cxf-2.7.17
export SOA_HOME=/soa/SoamSvcHostLinux
export CLASSPATH=.:$SOA_HOME/jackson-all-1.9.0.jar:$SOA_HOME/HpcSoam.jar


find . -name *.java -print | xargs javac  -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed" 

jar cvfm SampleService.jar manifest_linux sample

cp -f SampleService.jar $SOA_HOME


