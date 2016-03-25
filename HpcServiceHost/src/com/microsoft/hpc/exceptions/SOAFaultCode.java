//------------------------------------------------------------------------------
// <copyright file="SOAFaultCode.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Define the fault code for the SOA session.
// </summary>
//------------------------------------------------------------------------------
package com.microsoft.hpc.exceptions;

public enum SOAFaultCode {
	    ConnectToSchedulerFailure ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0000),
        OpenJobFailure ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0001),
        OperationTimeout ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0002),
        Broker_BrokerNodeUnavailable ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0003),
        ConnectSessionLauncherFailure ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0004),
        Broker_CustomBrokerReadyTimeout ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0005),
        Broker_CustomBrokerExitBeforeReady ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0006),
        Broker_CannotGetUserSID ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0007),
        Broker_FailedToGetSecurityDescriptor ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0008),
        Broker_RegisterJobFailed ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0009),
        Broker_FailedToGetJobState ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x000a),
        Broker_EOMRejected ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x000b),
        Broker_FailedToGetJobPropertyId ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x000c),
        Broker_BrokerIsOffline ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x000d),
        Broker_FailedToStartBrokerServiceProcess ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x000e),
        Broker_AlreadyInitialized ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x000f),
        Broker_GetResponsesHandlerDisposed ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0010),
        ConnectBrokerLauncherFailure ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0011),
        GetJobPropertyFailure ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0012),
        Broker_TooManyBrokerRunning ( SOAFaultCodeCategory.SessionConnectionError.getCode() | 0x0013),
        NoAvailableBrokerNodes ( SOAFaultCodeCategory.SessionError.getCode() | 0x0000),
        CreateJobFailure ( SOAFaultCodeCategory.SessionError.getCode() | 0x0001),
        CreateJobPropertiesFailure ( SOAFaultCodeCategory.SessionError.getCode() | 0x0002),
        GetClusterPropertyFailure ( SOAFaultCodeCategory.SessionError.getCode() | 0x0003),
        CreateJobTasksFailure ( SOAFaultCodeCategory.SessionError.getCode() | 0x0004),
        SubmitJobFailure ( SOAFaultCodeCategory.SessionError.getCode() | 0x0005),
        BrokerQueueFailure ( SOAFaultCodeCategory.SessionError.getCode() | 0x0006),
        Broker_BrokerUnavailable ( SOAFaultCodeCategory.SessionError.getCode() | 0x0007),
        Broker_LoadNetworkPrefixFailed ( SOAFaultCodeCategory.SessionError.getCode() | 0x0008),
        Broker_ConnectionToSchedulerIsNotReady ( SOAFaultCodeCategory.SessionError.getCode() | 0x0009),
        Broker_BrokerQueueFailure ( SOAFaultCodeCategory.SessionError.getCode() | 0x000a),
        Broker_SessionFailure ( SOAFaultCodeCategory.SessionError.getCode() | 0x000b),
        
        ClientPurged ( SOAFaultCodeCategory.ApplicationError.getCode() | 0x0000),
        Broker_RetryLimitExceeded ( SOAFaultCodeCategory.ApplicationError.getCode() | 0x0001),
        Broker_SendBackResponseFailed ( SOAFaultCodeCategory.ApplicationError.getCode() | 0x0002),
        ClientTimeout ( SOAFaultCodeCategory.ApplicationError.getCode() | 0x0003),
        Service_InitializeFailed ( SOAFaultCodeCategory.ApplicationError.getCode() | 0x0004),
        Service_Unreachable( SOAFaultCodeCategory.ApplicationError.getCode() | 0x0005),
        
        InvalidArgument ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0000),
        InvalidSessionId ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0001),
        AuthenticationFailure ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0002),
        AccessDenied_Broker ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0003),
        AccessDenied_BrokerLauncher ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0004),
        AccessDenied_BrokerQueue ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0005),
        StorageServiceNotAvailble ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0006),
        StorageSpaceNotSufficient ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0007),
        StorageFailure ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0008),
        StorageClosed ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0009),
        InvalidAttachDurableSession ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x000a),
        InvalidAttachInteractiveSession ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x000b),
        Broker_OpenFrontEndFailed ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x000c),
        Broker_NotSupportedTransportScheme ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x000d),
        Broker_InvalidConfiguration ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x000e),
        Broker_SessionIdAlreadyExists ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x000f),
        Broker_InvalidSessionId ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0010),
        Broker_BindingSecurityModeMismatched ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0011),
        Broker_BindingNotSupported ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0012),
        Broker_NoDefaultUriForScheme ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0013),
        Broker_CannotFindCustomBindingConfiguration ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0014),
        Broker_AuthenticationFailure ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0015),
        Broker_InvalidClientIdOrTooLong ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0016),
        Broker_ClientIdNotMatch ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0017),
        Broker_UserNameNotMatch ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0018),
        Session_ValidateJobFailed_NotServiceJob ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0019),
        Session_ValidateJobFailed_NotDurableSession ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x001a),
        Session_ValidateJobFailed_AlreadyFinished ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x001b),
        Session_ValidateJobFailed_JobCanceled ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x001c),
        ConfigFile_Invalid ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x001d),
        Service_NotFound ( SOAFaultCodeCategory.SessionFatalError.getCode() | 0x001e),
        Service_RegistrationDirsMissing (SOAFaultCodeCategory.SessionFatalError.getCode() | 0x001f),
	    ServiceVersion_NotFound (SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0020),
	    Broker_UnsupportedVersion (SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0021),
	    Broker_UnsupportedOperation (SOAFaultCodeCategory.SessionFatalError.getCode() | 0x0022),
	    Service_Preempted (SOAFaultCodeCategory.SessionFatalError.getCode() + 0x0024),
	    AuthenticationFailure_NeedEitherTypeCred (SOAFaultCodeCategory.SessionFatalError.getCode() + 0x0025),
	    AuthenticationFailure_NeedCertOnly (SOAFaultCodeCategory.SessionFatalError.getCode() + 0x0026),
	    
        UnknownError ( SOAFaultCodeCategory.Unknown.getCode() | 0x0000);

	private int code;

	private SOAFaultCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SOAFaultCode getEnum(int code) {
	    //@todo: if this turns out a bottleneck, we need a do a hash lookup
	    for(SOAFaultCode c  : SOAFaultCode.class.getEnumConstants())
	    {
	        if (c.getCode() == code)
	            return c;
	    }
	    
	    return SOAFaultCode.UnknownError;
	}

}
