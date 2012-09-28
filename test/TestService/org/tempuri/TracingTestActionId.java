package org.tempuri;

public enum TracingTestActionId {
    Default, 
    TraceWithDelay, 
    TraceLargeAmount, 
    TraceBigSize, 
    TraceFaultException, 
    TraceProcessExit, 
    TraceRequestProcessing, 
    NoUserTrace, 
    BrokerNodeOffline, 
    TestTraceResponseTwice, 
    RetryOperationError;
    
    public static TracingTestActionId fromInteger(int value)
    {
        switch(value)
        {
        case 0:
            return Default;
        case 1:
            return TraceWithDelay;
        case 2:
            return TraceLargeAmount;
        case 3:
            return TraceBigSize;
        case 4:
            return TraceFaultException;
        case 5:
            return TraceProcessExit;
        case 6:
            return TraceRequestProcessing;
        case 7:
            return NoUserTrace;
        case 8:
            return BrokerNodeOffline;
        case 9:
            return TestTraceResponseTwice;
        case 10:
            return RetryOperationError;
        }
        return null;
    }
}