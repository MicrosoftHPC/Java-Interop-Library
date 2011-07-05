//------------------------------------------------------------------------------
// <copyright file="Utility.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Utility Functions
// </summary>
//------------------------------------------------------------------------------
/*
JAVA INTEROP LIBRARY FOR WINDOWS HPC SERVER

Copyright (c) Microsoft Corporation.  All rights reserved.

This license governs use of the accompanying software. If you use the
software, you accept this license. If you do not accept the license, do not
use the software.

1. Definitions
The terms "reproduce," "reproduction," "derivative works," and "distribution"
have the same meaning here as under U.S. copyright law.
A "contribution. is the original software, or any additions or changes to
the software.
A "contributor. is any person that distributes its contribution under this
license.
"Licensed patents. are a contributor.s patent claims that read directly on
its contribution.

2. Grant of Rights
(A) Copyright Grant- Subject to the terms of this license, including the
license conditions and limitations in section 3, each contributor grants you
a non-exclusive, worldwide, royalty-free copyright license to reproduce its
contribution, prepare derivative works of its contribution, and distribute
its contribution or any derivative works that you create.
(B) Patent Grant- Subject to the terms of this license, including the license
conditions and limitations in section 3, each contributor grants you a
non-exclusive, worldwide, royalty-free license under its licensed patents to
make, have made, use, sell, offer for sale, import, and/or otherwise dispose
of its contribution in the software or derivative works of the contribution
in the software.

3. Conditions and Limitations
(A) No Trademark License- This license does not grant you rights to use any
contributors' name, logo, or trademarks.
(B) If you bring a patent claim against any contributor over patents that
you claim are infringed by the software, your patent license from such
contributor to the software ends automatically.
(C) If you distribute any portion of the software, you must retain all
copyright, patent, trademark, and attribution notices that are present in
the software.
(D) If you distribute any portion of the software in source code form,
you may do so only under this license by including a complete copy of this
license with your distribution. If you distribute any portion of the software
in compiled or object code form, you may only do so under a license that
complies with this license.
(E) The software is licensed "as-is." You bear the risk of using it. The
contributors give no express warranties, guarantees or conditions. You may
have additional consumer rights under your local laws which this license
cannot change. To the extent permitted under your local laws, the contributors
exclude the implied warranties of merchantability, fitness for a particular
purpose and non-infringement.
(F) Platform Limitation- The licenses granted in sections 2(A) & 2(B) extend
only to the software or derivative works that you create that operate with
Windows HPC Server.
*/

package com.microsoft.hpc.scheduler.session;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.soap.SOAPFaultException;

import com.microsoft.hpc.dataservice.DataFault;
import com.microsoft.hpc.dataservice.IDataServiceOpenDataClientDataFaultFaultFaultMessage;
import com.microsoft.hpc.exceptions.DataErrorCode;
import com.microsoft.hpc.exceptions.DataException;
import com.microsoft.hpc.exceptions.SOAFaultCode;
import com.microsoft.hpc.session.SessionFault;

/**
 * Utility Functions
 */
class Utility
{
    static Pattern pattern = Pattern.compile("^[0-9a-zA-Z_\\-\\{\\}\\s]*$");
    static Pattern dataClientIdPattern = Pattern
            .compile("^[0-9a-zA-Z_\\-\\{\\}\\s]*[0-9a-zA-Z_\\-\\{\\}]$");

    static boolean isEOM(SOAPMessage message) {
        return Constant.EndRequestsAction.equalsIgnoreCase(getAction(message));
        // Note: Currently, action in SOAPHeader == null;
    }

    /**
     * Logs error
     * 
     * @param message
     *            Error Message
     * @param args
     *            Arguments into error message
     */
    static void logError(String message, Object... args) {
    }

    /**
     * Throws Exception if arg is null
     * 
     * @param arg
     *            arg to check
     * @param name
     *            name of argument
     */
    public static void throwIfNull(Object arg, String name) {
        if (arg == null) {
            throw new NullPointerException(String.format(
                    "Argument Error: Parameter %s is null.", name));
        }
    }

    /**
     * Throws Exception if arg is null
     * 
     * @param arg
     *            arg to check
     * @param name
     *            name of argument
     */
    public static void throwIfInvalidClientId(String clientId) {
        Matcher match = pattern.matcher(clientId);
        if (!match.find()) {
            throw new IllegalArgumentException("ClientId is invalid.");
        }
    }

    /**
     * Throw IllegalArgumentException if argument is an invalid DataClient id
     * 
     * @param dataClientId
     */
    public static void throwIfInvalidDataClientId(String dataClientId,
            String message) {
        Matcher match = dataClientIdPattern.matcher(dataClientId);
        if (!match.find()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Throws exception if arg is null or empty
     * 
     * @param arg
     *            arg to check
     * @param name
     *            name of argument
     */
    public static void throwIfNullOrEmpty(String arg, String name) {
        if (arg == null) {
            throw new NullPointerException(String.format(
                    "Argument Error: Parameter %s is null or empty.", name));
        }
        if (arg == "") {
            throw new IllegalArgumentException(String.format(
                    "Argument Error: Parameter %s is null or empty.", name));
        }
    }

    /**
     * Throws Exception if specified len is too large
     * 
     * @param len
     *            length to check
     * @param name
     *            name of argument
     * @param maxLen
     *            maximum length
     * @param message
     *            error message
     * @param args
     *            error message arguments
     */
    public static void throwIfTooLong(int len, String name, int maxLen,
            String message, Object... args) {
        if (len > maxLen) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }

    /**
     * if valid is not true, throws an exception
     * 
     * @param valid
     *            statement to verify
     * @param name
     *            argument name
     */
    public static void throwIfInvalid(Boolean valid, String name) {
        if (!valid) {
            throw new IllegalArgumentException("Argument %d is invalid." + name);
        }
    }

    // internal static void SafeCloseCommunicateObject(SOATraceSource
    // traceSource, I...)
    // no communicate objects to close

    /**
     * Takes a SessionFault and translates to SessionException
     * 
     * @param fault
     *            retrieved by an exception with a getFault() method
     * @return SessionException
     */
    public static SessionException translateFaultException(SessionFault fault) {
        SessionException sessionException = null;
        if (fault != null) {
            SOAFaultCode code = SOAFaultCode.getEnum(fault.getCode());

            String errorMessage = SR.v(code.toString());
            if (errorMessage == null) {
                errorMessage = fault.getReason().getValue().replace("%", "%%");

                // replace {0} from .net style to Java style %s
                errorMessage = errorMessage.replace("{0}", "%s");
                errorMessage = errorMessage.replace("{1}", "%s");
            }
            if (fault.getContext() != null &&
                fault.getContext().getValue() != null &&
                fault.getContext().getValue().getAnyType() != null)
                    errorMessage = String.format(errorMessage, fault.getContext()
                            .getValue().getAnyType().toArray());

            sessionException = new SessionException(fault.getCode(),
                    errorMessage);
        }
        return sessionException;
    }

    /**
     * Takes a SessionFault and translates to SessionException
     * 
     * @param fault
     *            retrieved by an exception with a getFault() method
     * @param errorMessage
     *            detail message
     * @return SessionException
     */
    static SessionException translateFaultException(SessionFault fault,
            String errorMessage) {
        SessionException sessionException = null;
        if (fault != null) {
            sessionException = new SessionException(fault.getCode(),
                    errorMessage);
        }
        return sessionException;
    }

    // Java Only
    /**
     * Get action from the header of the SOAPMessage in messageBuffer
     */
    static String getAction(SOAPMessage message) {
        String action = "";
        SOAPHeader soapHeader = null;
        try {
            soapHeader = message.getSOAPHeader();
        } catch (Exception e) {
            return "";
        }

        while (soapHeader.getChildElements().hasNext()) {
            Node node = (Node) soapHeader.getChildElements().next();
            if (node.getNodeName().equalsIgnoreCase("Action")) {
                action = node.getFirstChild().getTextContent();
                break;
            }
        }
        return action;
    }

    static SOAPVersion getSOAPVersion(SOAPMessage message)
            throws SOAPFaultException, SOAPException {
        String contentType = message.getMimeHeaders().getHeader("Content-Type")[0];

        if (contentType == SOAPConstants.SOAP_1_1_CONTENT_TYPE) {
            return SOAPVersion.valueOf("SOAP11");
        } else if (contentType == SOAPConstants.SOAP_1_2_CONTENT_TYPE) {
            return SOAPVersion.valueOf("SOAP12");
        } else
            throw new SOAPFaultException(SOAPFactory.newInstance().createFault(
                    "Invalid SOAP version",
                    SOAPConstants.SOAP_VERSIONMISMATCH_FAULT));
    }

    public static DataException translateDataFaultException(
            DataFault fault) {
        DataException dataException = null;
        if (fault != null) {
            DataErrorCode code = DataErrorCode.getEnum(fault.getCode());
            String errorMessage = SR.v(code.toString());
            if (errorMessage == null) {
                errorMessage = fault.getReason().getValue().replace("%", "%%");

                // replace {0} from .net style to Java style %s
                errorMessage = errorMessage.replace("{0}", "%s");
                errorMessage = errorMessage.replace("{1}", "%s");
            }

            if (fault.getContext() != null &&
                fault.getContext().getValue() != null &&
                fault.getContext().getValue().getAnyType() != null)
                    errorMessage = formatErrorMessageForDataException(code,
                            errorMessage,
                            fault.getContext().getValue().getAnyType().toArray());
            else
                errorMessage = formatErrorMessageForDataException(code,
                        errorMessage,
                        null);

            dataException = new DataException(fault.getCode(),
                    errorMessage);
        }
        return dataException;
        
    }
    
    private static String formatErrorMessageForDataException(
            DataErrorCode code, String rawErrorMessage, Object[] context) {
        String errorMessage = rawErrorMessage;
        switch (code) {
            case DataClientAlreadyExists:
            case DataClientNotFound:
            case DataClientBusy:
            case DataClientBeingCreated:
            case DataClientNotWritable:
            case DataClientReadOnly:
            case DataClientLifeCycleSet:
            case DataClientDeleted:
            case DataInconsistent:
            case NoDataAvailable:
                // context[0] is data client id
                errorMessage = String.format(errorMessage, context[0]);
                break;
            case DataServerMisconfigured:
            case DataServerUnreachable:
            case DataServerBadAddress:
                // context[1] is data server address
                errorMessage = String.format(errorMessage, context[1]);
                break;
            default:
                break;
        }
        return errorMessage;
    }
}
