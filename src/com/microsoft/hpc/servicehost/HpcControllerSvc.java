// ------------------------------------------------------------------------------
// <copyright file="HpcControllerSvc.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Controller Servicer to handle the Exit invoking
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
import javax.jws.WebService;
import com.microsoft.hpc.properties.ErrorCode;
import com.microsoft.hpc.scheduler.session.servicecontext.SOAEventArg;
import com.microsoft.hpc.scheduler.session.servicecontext.Sender;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext;
import com.microsoft.hpc.scheduler.session.servicecontext.StringResource;

/**
 * @author t-junchw
 * @date May 9, 2011
 * @description Controller Service working with the broker worker
 */
@WebService(targetNamespace = "http://hpc.microsoft.com/hpcservicehost/", name = "IHpcServiceHost")
public class HpcControllerSvc implements IHpcController
{

    /**
     * @field How long to wait for Exiting event to return
     */
    int cancelTaskGracePeriod = 0;

    /**
     * @field sessionID
     */
    int sessionId = 0;

    private HpcServiceHostWrapper hostWrapper;

    /**
     * @description Constructor for service host
     * @param sessionId
     * @param cancelTaskGracePeriod
     * @param hostWrapper
     */
    public HpcControllerSvc(int sessionId, int cancelTaskGracePeriod,
            HpcServiceHostWrapper hostWrapper)
    {
        this.sessionId = sessionId;
        this.cancelTaskGracePeriod = cancelTaskGracePeriod;
        this.hostWrapper = hostWrapper;
    }

    /**
     * @description Shutdowns down service host
     */
    @Override
    public void Exit()
    {
        try
        {
            synchronized (this.hostWrapper.syncObjOnExitingCalled)
            {
                // No need to call OnExiting again if it is already called when
                // Ctrl-C signal is received.
                if (!this.hostWrapper.isOnExitingCalled)
                {
                    // Invoke user's Exiting event async with a timeout
                    // specified by TaskCancelGracePeriod cluster parameter.
                    Method method = ServiceContext.class.getDeclaredMethod(
                            "fireExitingEvent", new Class[] { Sender.class,
                                    SOAEventArg.class, int.class });
                    method.setAccessible(true);

                    // Invoke the OnExitingAsynchronized method
                    Sender sender = new Sender(new Object());
                    SOAEventArg soaEventArg = new SOAEventArg(0);
                    Object[] argsObjects = { sender, soaEventArg,
                            cancelTaskGracePeriod };
                    method.invoke(ServiceContext.class.newInstance(),
                            argsObjects);

                    this.hostWrapper.isOnExitingCalled = true;
                }
            }
        } catch (Exception ex)
        {
            ServiceContext.Logger.traceEvent(Level.WARNING, ex.toString());
        } finally
        {
            // remove the Ctrl-C hook from runtime
            synchronized (this.hostWrapper.shutdownThread)
            {
                try
                {
                    Runtime.getRuntime().removeShutdownHook(
                            this.hostWrapper.shutdownThread);
                } catch (IllegalStateException e)
                {
                    // If the virtual machine is already in the process of
                    // shutting down
                    this.hostWrapper.shutdownThread.notify();
                    return;
                } catch (Exception ex)
                {
                    ServiceContext.Logger.traceEvent(Level.SEVERE,
                            ex.toString());
                    return;
                }
            }

            // Keep the exit code the same as before. If the host is canceled by
            // user or scheduler,
            // it exits with -1. If the host is closed by graceful shrink, it
            // exits with 0.
            if (this.hostWrapper.receivedCancelEvent)
            {
                System.out.println(StringResource
                        .getResource("TaskCanceledOrPreempted"));
                System.out.flush();
                ServiceContext.Logger.traceEvent(Level.INFO,
                        StringResource.getResource("TaskCanceledOrPreempted"));
                System.exit(-1);
            } else
            {
                System.out.println(StringResource
                        .getResource("ServiceShutdownFromBrokerShrink"));
                System.out.flush();
                ServiceContext.Logger.traceEvent(Level.INFO, StringResource
                        .getResource("ServiceShutdownFromBrokerShrink"));
                System.exit(ErrorCode.Success);
            }
        }
    }
}
