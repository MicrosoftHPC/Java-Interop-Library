//------------------------------------------------------------------------------
// <copyright file="SOAFaultCodeCategory.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Define the fault code for the SOA session.
// </summary>
//------------------------------------------------------------------------------
package com.microsoft.hpc.exceptions;

    /// <summary>
    /// Categories for SOAFaultCodes
    /// </summary>
    public enum SOAFaultCodeCategory
    {
        /// <summary>
        /// If the client has a valid session ID, it could react to this category of errors by trying to reattach a limited number of times. If the client does,
        /// not have a valid session ID, it could react to this category of errors by trying to create a new session a limited number of times.
        /// </summary>
        SessionConnectionError(0x01000000),

        /// <summary>
        /// The client could react to this category of errors by trying to create a new session a limited number of times. Attempting to reattach will not work.
        /// </summary>
        SessionError(0x02000000),

        /// <summary>
        /// The client should not try to create or attach to a session. It should report the error to their helpdesk. There is something wrong with app's implementation or installation
        /// </summary>
        SessionFatalError(0x03000000),

        /// <summary>
        /// The error is at the app level and did not fault the session. The session can still be used with current connection
        /// </summary>
        ApplicationError(0x04000000),

        /// <summary>
        /// The error happens in the proxy on Azure. Proxy sends the error back to the broker.
        /// </summary>
        BrokerProxyError(0x05000000),

        /// <summary>
        /// Common data operation error, for example, due to temporary bad network condition, data server busy, etc.
        /// </summary>
        DataError(0x06100000),

        /// <summary>
        /// Fatal data operation error
        /// </summary>
        DataFatalError(0x06200000),

        /// <summary>
        /// Data operation error happened at application level
        /// </summary>
        DataApplicationError(0x06300000),

        /// <summary>
        /// Unknown error. The application will need to handle as it wishes
        /// </summary>
        Unknown(0x0f000000);

	private int code;

	private SOAFaultCodeCategory(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static SOAFaultCodeCategory getEnum(int code) {
		return SOAFaultCodeCategory.class.getEnumConstants()[code];
	}

}
