// ------------------------------------------------------------------------------
// <copyright file="ShutdownThread.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Capture the Ctrl-C Signal
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
package com.microsoft.hpc.servicehost;

import java.lang.reflect.Method;
import java.util.logging.Level;

import com.microsoft.hpc.scheduler.session.servicecontext.SOAEventArg;
import com.microsoft.hpc.scheduler.session.servicecontext.Sender;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext;
import com.microsoft.hpc.scheduler.session.servicecontext.TraceHelper;

/**
 * @author t-junchw
 * @date May 10, 2011
 * @description invoke onexit event when capture the Ctrl-C signal
 */
public class ShutdownThread extends Thread
{
    private HpcServiceHostWrapper serviceHostWrapper;

    public ShutdownThread(HpcServiceHostWrapper serviceHostWrapper)
    {
        this.serviceHostWrapper = serviceHostWrapper;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    public void run()
    {
        ServiceContext.Logger.traceEvent(Level.INFO, "Ctrl-C Signal Received");
        ServiceContext.Logger.traceEvent(Level.WARNING,
                "ServiceHost get canceled by scheduler");

        serviceHostWrapper.receivedCancelEvent = true;
        try
        {
            synchronized (serviceHostWrapper.syncObjOnExitingCalled)
            {
                if (!serviceHostWrapper.isOnExitingCalled)
                {
                  
                    // Trigger OnExiting event as soon as Ctrl-C signal is
                    // received.
                    // Expose the SOAFaultCode.Service_Preempted error code to
                    // users through the EventArgs.
                    Method method = ServiceContext.class.getDeclaredMethod(
                            "fireExitingEvent", new Class[] { Sender.class,
                                    SOAEventArg.class, int.class });
                    method.setAccessible(true);

                    // Invoke the OnExitingAsynchronized method
                    Sender sender = new Sender(new Object());
                    SOAEventArg soaEventArg = new SOAEventArg(0);
                    Object[] argsObjects = { sender, soaEventArg, 0};
                    method.invoke(ServiceContext.class.newInstance(),
                            argsObjects);
                    this.serviceHostWrapper.isOnExitingCalled = true;
                   
                }
            }
        } catch (Exception excep)
        {
            TraceHelper.traceError(excep.toString());
            ServiceContext.Logger
                    .traceEvent(
                            Level.SEVERE,
                            String.format(
                                    "[HpcServiceHost]: Calling ServiceContext.FireExitingEvent failed. %s",
                                    excep.toString()));
        }

        if (serviceHostWrapper.enableMessageLevelPreemption)
        {
            if (serviceHostWrapper.processingMessageIds.isEmpty())
            {
                // Two cases can't depend on the response message to notice the
                // broker that the host is preempted.
                // So we need to close the host and exit.
                // (1) This service host never receives any message.
                // (2) The Ctrl-C signal happens after the last response is sent
                // back.
                try
                {
                    serviceHostWrapper.host.getBus().shutdown(true);
                } catch (Exception excep)
                {
                    ServiceContext.Logger
                            .traceEvent(
                                    Level.SEVERE,
                                    String.format(
                                            "[HpcServiceHost]: Calling ServiceHost.Close failed. %s",
                                            excep));
                }
            } else
            {
                // Wait for broker to call Exit method after it receives all the
                // responses.

                synchronized (this)
                {
                    try
                    {
                        this.wait();
                    } catch (InterruptedException e)
                    {
                        TraceHelper.traceStackError(e);
                    }
                }
            }
        }
    }

}
