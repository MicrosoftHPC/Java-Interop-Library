Introduction
	The tests will run a simple SOA session job using Java.
	It uses the Microsoft-HpcSession-3.0.jar and CcpEchoSvc.jar created previous steps.

How to run the tests
	- Go to ..\SessionAPI, create Microsoft.Hpc.Scheduler.jar and copy it here.

        - Edit RunTest.cmd for Java and CXF path.
        - Edit RunTest.cmd for Keystore_Password if necessary.
        - Edit HelloWorld.java for username, password, headnode, and service name.
        - Run: RunTest.cmd
	

