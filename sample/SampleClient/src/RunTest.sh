#/bin/bash

if [ "$#" = 0 ] ; then
    echo Usage: to compile, run: ./RunTest.sh src
    echo Usage: to run test, run: ./RunTest.sh run
    exit 0
fi

export JAVA_HOME=/usr/java/jdk1.8.0_60
export CXF_HOME=/opt/apache-cxf-2.7.17
export SOA_HOME=/soa/SoamSvcHostLinux
Keystore_Password=password
CLASSPATH=.:$SOA_HOME/Microsoft-HpcSession-3.0.jar:$CXF_HOME/lib/cxf-manifest.jar:$SOA_HOME/HpcSoam.jar:$SOA_HOME/HpcSoamSvc.jar:$SOA_HOME/SampleService.jar:$SOA_HOME/jackson-all-1.9.0.jar

PATH=$PATH:$JAVA_HOME/bin

if [ "$1" = "src" ] ; then
	"$JAVA_HOME/bin/javac" -cp $CLASSPATH -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed" HelloWorld.java
fi

if [ "$1" = "run" ] ; then
	# set envs for soa common data test
	export HPC_SOADATASERVER=\\\\SOATEST-HN\\Runtime$\\SOA
	export HPC_SOADATASERVER_FOR_LINUX=/hpcruntime/SOA

	"$JAVA_HOME/bin/java" -cp $CLASSPATH HelloWorld -Djavax.net.ssl.trustStore="$JAVA_HOME/jre/lib/security/cacerts" -Djavax.net.ssl.trustStorePassword=$Keystore_Password -Djava.endorsed.dirs="$CXF_HOME/lib/endorsed"
fi
