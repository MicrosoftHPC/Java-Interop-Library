Introduction
	The tests will run a simple SOA session job using Java.
	It uses the Microsoft-HpcSession-3.0.jar and CcpEchoSvc.jar created previous steps.

How to run the tests
	- Go to ../../src, create Microsoft-HpcSession-3.0.jar and copy it here.
        - Edit RunTest.sh for Java and CXF path.
        - Edit RunTest.sh for Keystore_Password if necessary.
    	- Edit Sample.java for username, password, headnode, and service name.
	- Run: ./RunTest.sh
	

