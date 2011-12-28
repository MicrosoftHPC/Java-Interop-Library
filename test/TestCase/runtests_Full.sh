#!bin/sh
export CXF_HOME=/usr/java/apache-cxf-2.4.0
export CLASSPATH=.:$CXF_HOME/lib/cxf-manifest.jar:./junit.jar:./org.hamcrest.core_1.1.0.v20090501071000.jar:./JavaTestServiceClient.jar:../Microsoft-HpcSession-3.0.jar

java -Xmx1024m -Djava.ext.dirs=/usr/java/jdk1.6.0_23/jre/lib/ext:/usr/java/apache-cxf-2.4.0/lib:/usr/java/apache-cxf-2.4.0/lib/endorsed  org.junit.runner.JUnitCore  fullpass.Full
