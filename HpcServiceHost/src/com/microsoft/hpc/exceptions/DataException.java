//------------------------------------------------------------------------------
// <copyright file="DataException.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      DataException class.
// </summary>
//------------------------------------------------------------------------------

package com.microsoft.hpc.exceptions;


import com.microsoft.hpc.exceptions.SOAFaultCode;

/**
 * The class <code>DataException</code> is a form of <code>Exception</code> that indicates there was an issue with the Microsoft HPC DataClient
 */
public class DataException extends Exception
{
    private int errorCode = SOAFaultCode.UnknownError.getCode();

    /**
     * Constructs a DataException with the specified DataErrorCode and error message
     * @param errorCode DataErrorCode
     * @param message the detail message
     */
    public DataException(int errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Constructs an exception with the specified DataErrorCode, error message and the exception that caused it.
     * @param errorCode DataErrorCode.
     * @param message   the detail message
     * @param innerException    the exception that caused the outer exceptions
     */
    public DataException(int errorCode, String message, Exception innerException)
    {
        super(message, innerException);
        this.errorCode = errorCode;
    }

    public int getErrorCode()
    {
        return this.errorCode;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 6364247049633637822L;
}
