//------------------------------------------------------------------------------
// <copyright file="DataErrorCode.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Define the error code for data operations
// </summary>
//------------------------------------------------------------------------------
package com.microsoft.hpc.exceptions;

public enum DataErrorCode {
    Unknown ( SOAFaultCodeCategory.Unknown.getCode() | 0x0000),
    DataServerUnsupported ( SOAFaultCodeCategory.DataFatalError.getCode() | 0x0001),
    DataServerMisconfigured ( SOAFaultCodeCategory.DataFatalError.getCode() | 0x0002),
    DataVersionUnsupported ( SOAFaultCodeCategory.DataFatalError.getCode() | 0x0003),
    NoDataServerConfigured ( SOAFaultCodeCategory.DataFatalError.getCode() | 0x0004),
    DataFeatureNotSupported ( SOAFaultCodeCategory.DataFatalError.getCode() | 0x005),
    DataServerBadAddress ( SOAFaultCodeCategory.DataFatalError.getCode() | 0x0006),
    ConnectDataServiceFailure ( SOAFaultCodeCategory.DataFatalError.getCode() | 0x0007),
    ConnectDataServiceTimeout ( SOAFaultCodeCategory.DataFatalError.getCode() | 0x0008),
    
    DataServerUnreachable ( SOAFaultCodeCategory.DataError.getCode() | 0x0001),
    DataServerNoSpace ( SOAFaultCodeCategory.DataError.getCode() | 0x0002),
    DataInconsistent ( SOAFaultCodeCategory.DataError.getCode() | 0x0003),
    DataRetry ( SOAFaultCodeCategory.DataError.getCode() | 0x0004),
    UnknownIOError ( SOAFaultCodeCategory.DataError.getCode() | 0x0005),
    DataClientIdTooLong ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0001),
    DataClientIdInvalid ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0002),
    DataMaxSizeExceeded ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0003),
    DataClientAlreadyExists ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0004),
    DataClientNotFound ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0005),
    DataClientBusy ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0006),
    DataClientBeingCreated ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0007),
    DataClientNotWritable ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0008),
    DataClientDisposed ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0009),
    DataTypeMismatch ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x000a),
    DataClientReadOnly ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x000b),
    DataClientLifeCycleSet ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x000c),
    DataClientDeleted ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x000d),
    NoDataAvailable ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x000e),
    DataNoPermission ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x000f),
    InvalidAllowedUser ( SOAFaultCodeCategory.DataApplicationError.getCode() | 0x0010)
    
    ;
    
    private int code;
    
    private DataErrorCode(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }

    public static DataErrorCode getEnum(int code) {
        // @todo: if this turns out a bottleneck, we need a do a hash lookup
        for (DataErrorCode c : DataErrorCode.class.getEnumConstants()) {
            if (c.getCode() == code)
                return c;
        }

        return DataErrorCode.Unknown;
    }
}
