#/bin/bash

if [ "$#" = 0 ] ; then
    echo Usage: to compile,  run: ./RunTest.sh src
    echo Usage: to run test, run: ./RunTest.sh run
    exit 0
fi

JAVA_HOME=/usr/java/jdk1.6.0_23
CXF_HOME=/usr/java/apache-cxf-2.4.0
Keystore_Password=[password]
CLASSPATH=.:Microsoft-HpcSession-3.0.jar:CcpEchoSvc.jar:$CXF_HOME/lib/cxf-manifest.jar

PATH=$PATH:$JAVA_HOME/bin:/home

if [ "$1" = "src" ] ; then
	"$JAVA_HOME/bin/javac" -cp $CLASSPATH -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed" HelloWorld.java
fi

if [ "$1" = "run" ] ; then
	"$JAVA_HOME/bin/java" -cp $CLASSPATH HelloWorld -Djavax.net.ssl.trustStore="$JAVA_HOME/jre/lib/security/cacerts" -Djavax.net.ssl.trustStorePassword=$Keystore_Password -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed"
fi
