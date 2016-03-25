#!/bin/sh

export JAVA_HOME=/usr/java/jdk1.8.0_60
export CXF_HOME=/opt/apache-cxf-2.7.17
export SOA_HOME=/soa/SoamSvcHostLinux
export CLASSPATH=.:$SOA_HOME/Microsoft-HpcSession-3.0.jar


find . -name *.java -print | xargs javac  -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed" 

jar cvfm HpcSoam.jar manifest_linux com

cp -f HpcSoam.jar $SOA_HOME


