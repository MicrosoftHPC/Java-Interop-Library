#!/bin/sh
export CXF_HOME=/usr/java/apache-cxf-2.4.0 
export CLASSPATH=$CXF_HOME/lib/cxf-manifest.jar

find . -name *.java -print | xargs javac  -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed" 

jar cvfm Microsoft-HpcSession-3.0.jar manifestclient_linux org com/microsoft/schemas com/microsoft/hpc/brokercontroller com/microsoft/hpc/brokerlauncher com/microsoft/hpc/dataservice com/microsoft/hpc/exceptions com/microsoft/hpc/genericservice com/microsoft/hpc/properties com/microsoft/hpc/scheduler com/microsoft/hpc/session com/microsoft/hpc/sessionlauncher com/microsoft/hpc/*.class com/microsoft/hpc/*.java *.wsdl *.xsd FaultCodeMessageBundle.properties StringTableBundle.properties

jar cvfm Microsoft-HpcServiceHost-3.0.jar manifesthost_linux com/microsoft/hpc/servicehost
