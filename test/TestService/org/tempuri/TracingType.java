package org.tempuri;

public enum TracingType {
    TraceEvent, 
    TraceInformation, 
    TraceData, 
    TraceTransfer;
    
    public static TracingType fromInteger(int value) {
        switch(value) {
        case 0:
            return TraceEvent;
        case 1:
            return TraceInformation;
        case 2:
            return TraceData;
        case 3:
            return TraceTransfer;
        }
        return null;
    }
}