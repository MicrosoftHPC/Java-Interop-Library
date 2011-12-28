Introduction
============
This readme.txt shows the steps to run HPC Java Session API test cases


Prerequisites
============
1) JDK 1.6.0_23
2) CXF 2.4.0
3) JUNIT 4.8.1


On Windows Server 2008 R2:
 

1. Download and compile the HPC Java Session API source code

a) Download HPC Java Session API source code to %basedir%\SOA\src
b) Build the HPC Java Session API source code by makejar.cmd, remember
   to set up %JAVA_HOME% and %CXF_HOME% before run the script.


2. Download and compile the HPC Java Session API test cases

a) Download HPC Java Session API test code to %basedir%\SOAtest\test
b) Download and copy AITestLibService.jar, junit.jar and org.hamcrest.core_1.1.0.v20090501071000.jar to %basedir%\SOAtest\test
c) Build the HPC Java Session API test code by buildtests.cmd, remember
   to set up %JAVA_HOME% and %CXF_HOME% before running the script.

3. Run the HPC Java Session API test cases

a) Modify the TestData.xml with the correct headnode name, username and password, and copy it to the bin folder
b) Under %basedir%\SOAtest\test run cmd to launch the BVT test
   java -Xmx1024m -Djava.ext.dirs="%JAVA_HOME%\jre\lib\ext";%CXF_HOME%\lib;%CXF_HOME%\lib\endorsed org.junit.runner.JUnitCore functionest.BVT
c) To run the full pass, under %basedir%\SOAtest\test run
   java -Xmx1024m -Djava.ext.dirs="%JAVA_HOME%\jre\lib\ext";%CXF_HOME%\lib;%CXF_HOME%\lib\endorsed org.junit.runner.JUnitCore fullpass.Full 



On RHEL5.5:

1. Download and compile the HPC Java Session API source code

a) Download HPC Java Session API source code to $basedir/SOA/src
b) Build the HPC Java Session API source code by makejar.sh, remember
   to export CXF_HOME and make sure $JAVA_HOME and $JAVA_BIN are set before run the script.


2. Download and compile the HPC Java Session API test cases

a) Download HPC Java Session API test code to $basedir/SOAtest/test
b) Download and copy AITestLibService.jar, junit.jar and org.hamcrest.core_1.1.0.v20090501071000.jar to $basedir/SOAtest/test

   
d) Build the HPC Java Session API test code by buildtests.sh, remember
   to export $JAVA_HOME and $CXF_HOME before running the script.

3. Run the HPC Java Session API test cases

a) Modify the TestData.xml with the correct headnode name, username and password, and copy it to the bin folder
b) Make sure the CLASSPATH is set correctly, e.g.
   export CLASSPATH=.:$CXF_HOME/lib/cxf-manifest.jar:./junit.jar:./org.hamcrest.core_1.1.0.v20090501071000.jar:./AITestLibService.jar:../../SOA/src:./bin
c) To run CommonData test cases, the command data share folder should be mounted by
   mount -t cifs -o username=[domain]/\[username],password='[password]' //[headnode]/runtime$ /mnt/winshare/
d) Under $basedir/SOAtest/test run cmd to launch the BVT test
   java -Xmx1024m -Djava.ext.dirs=/usr/java/jdk1.6.0_23/jre/lib/ext:/usr/java/apache-cxf-2.4.0/lib:/usr/java/apache-cxf-2.4.0/lib/endorsed       org.junit.runner.JUnitCore functiontest.BVT
e) To run the full pass, under %basedir%\SOAtest\test run
   java -Xmx1024m -Djava.ext.dirs=/usr/java/jdk1.6.0_23/jre/lib/ext:/usr/java/apache-cxf-2.4.0/lib:/usr/java/apache-cxf-2.4.0/lib/endorsed             org.junit.runner.JUnitCore fullpass.Full 



