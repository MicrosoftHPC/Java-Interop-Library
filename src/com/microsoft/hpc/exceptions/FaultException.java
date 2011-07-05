//------------------------------------------------------------------------------
// <copyright file="FaultException.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Implement FaultException
// </summary>
//------------------------------------------------------------------------------

package com.microsoft.hpc.exceptions;

/**
 * a FaultException is an <code>Exception</code> written in a SOAPMessage
 * 
 */
public class FaultException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 105001278295539108L;

    /**
     * Creates a new exception with the specified detail message
     * @param fault the detail message
     */
    public FaultException(Object fault)
    {
        super("Fault thrown: " + fault);
    }
    
}
