#!/bin/sh

export JAVA_HOME=/usr/java/jdk1.8.0_60
export CXF_HOME=/opt/apache-cxf-2.7.17
export SOA_HOME=/soa/SoamSvcHostLinux
export CLASSPATH=.:$SOA_HOME/Microsoft-HpcSession-3.0.jar:$SOA_HOME/HpcSoam.jar:$CXF_HOME/lib/cxf-manifest.jar


find . -name *.java -print | xargs javac  -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed" 

jar cvfm HpcSoamSvc.jar manifest_linux org com

cp -f HpcSoamSvc.jar $SOA_HOME


