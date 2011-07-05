// ------------------------------------------------------------------------------
// <copyright file="ServiceContext.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// capture Ctrl-C signal to invoke Exiting event handler
// Log runtime information
// expose common data interface
// </summary>
// ------------------------------------------------------------------------------
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
package com.microsoft.hpc.scheduler.session.servicecontext;

import java.util.logging.Level;
import com.microsoft.hpc.exceptions.DataErrorCode;
import com.microsoft.hpc.exceptions.DataException;
import com.microsoft.hpc.scheduler.session.DataClient;
import com.microsoft.hpc.scheduler.session.Constant;


/**
 * @author t-junchw The implementation of the Service Context Class
 */
public final class ServiceContext
{
    public static ExitEventRegister exitingEvents = new ExitEventRegister();

    /**
     * @field the instance of logger to do the service tracing
     */
    public static LoggerContext Logger;

    /**
     * @field lock object for bDataServerInfoInitialized
     */
    private static Object lockDataServerInfoInitialized = new Object();

    // <summary>
    // a flag indicating if dataServerInfo has been initialized
    // </summary>
    private static Boolean bDataServerInfoInitialized = false;

    /**
     * @field the soa data server information
     */
    private static String dataServerAddress;

    /**
     * @field unrecoverable data exception that happens on data server info
     *        initialization
     */
    private static DataException DataServerInfoConfigException;

    /**
     * @param traceLevel
     */
    public static void setTraceLevel(String traceLevel)
    {
        if (Logger == null)
        {
            Logger = new LoggerContext(traceLevel);
        }
    }

    /**
     * @description Used to manually fire Exiting event. Called within session
     *              API and from service host when shrinking service instances
     * 
     * @param sender
     *            event sender
     * @param args
     *            event args.
     * @param traceSource
     *            the trace source
     */
    @SuppressWarnings("unused")
    private static void fireExitingEvent(Sender sender, SOAEventArg args,
            int millis)
    {
        ServiceContext.Logger.traceEvent(Level.WARNING,
                StringResource.getResource("ServiceHostcanceledbyscheduler"));

        if (exitingEvents != null)
        {
            try
            {
                // millis stands for limited time for execution
                if (millis > 0)
                    // execute event asynchronously
                    exitingEvents.onExitingAsynchronized(sender, args, millis);
                else
                {
                    // execute event synchronously
                    exitingEvents.onExiting(sender, args);
                }
            } catch (Exception e)
            {
                ServiceContext.Logger
                        .traceEvent(
                                Level.WARNING,
                                String.format(
                                        "[HpcServiceHost]: Exception thrown when firing OnExiting event - %s",
                                        e.toString()));
            }
        }
    }

    /**
     * @description Get the DataClient instance with the specified DataClient ID
     * @param dataClientId
     * @param username
     * @param password
     * @return
     * @throws DataException
     */
    public static DataClient getDataClient(String dataClientId) throws DataException
    {
        synchronized (lockDataServerInfoInitialized)
        {
            if (!bDataServerInfoInitialized) {
                String strDataServerInfo = Environment
                        .getEnvironmentVariable(Constant.SoaDataServerInfoEnvVar);
                if (strDataServerInfo != null && !strDataServerInfo.isEmpty()) {
                    dataServerAddress = strDataServerInfo;
                } else {
                    dataServerAddress = null;
                    ServiceContext.Logger
                            .traceEvent(Level.SEVERE,
                                    "[ServiceContext] .GetDataClient: no data server configured");
                    DataServerInfoConfigException = new DataException(
                            DataErrorCode.NoDataServerConfigured.getCode(),
                            StringResource
                                    .getResource("NoDataServerConfigured"));
                }
                // ensure this code block is only executed once
                bDataServerInfoInitialized = true;
            }
        }

        if(dataServerAddress != null) {
            return DataClient.open(dataServerAddress, dataClientId);
        } else {
            throw DataServerInfoConfigException;
        }
    }
}
