// ------------------------------------------------------------------------------
// <copyright file="ExitEventRegister.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Register for the Exit Event
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

import java.util.Vector;

/**
 * @author t-junchw
 * @date May 10, 2011
 * @description Register for the Exit Event
 */
public class ExitEventRegister
{
    /**
     * @field container for the exit event listeners
     */
    protected Vector<ExitEventListener> listenerList = new Vector<ExitEventListener>();

    /**
     * @param listener
     */
    public synchronized void addMyEventListener(ExitEventListener listener)
    {
        listenerList.add(listener);
    }

    /**
     * @param listener
     */
    public synchronized void removedMyEventListener(ExitEventListener listener)
    {
        listenerList.removeElement(listener);
    }

    /**
     * @description synchronized onExiting event
     * @param sender
     * @param soaEventArg
     */
    @SuppressWarnings({ "unchecked" })
    public void onExiting(Sender sender, SOAEventArg soaEventArg)
    {
        Vector<ExitEventListener> currentListenerList = null;
        synchronized (this)
        {
            currentListenerList = (Vector<ExitEventListener>) listenerList
                    .clone();
        }

        for (int i = 0; i < currentListenerList.size(); i++)
        {
            try
            {
                ExitEventListener listener = (ExitEventListener) currentListenerList
                        .elementAt(i);
                listener.OnExiting(sender, soaEventArg);
            } catch (Exception e)
            {
                TraceHelper.traceStackError(e);
            }
        }
    }

    /**
     * @description start the onExiting Event asynchronously
     * @param sender
     *            Event sender
     * @param soaEventArg
     *            SOA CODE
     * @param millis
     *            limited time of Execution
     * @throws InterruptedException
     */
    @SuppressWarnings("unchecked")
    public void onExitingAsynchronized(Sender sender, SOAEventArg soaEventArg,
            int millis) throws InterruptedException
    {
        Vector<ExitEventListener> currentListenerList = null;
        synchronized (this)
        {
            currentListenerList = (Vector<ExitEventListener>) listenerList
                    .clone();
        }
        try
        {
            ExitingThread exitingThread = new ExitingThread(
                    currentListenerList, sender, soaEventArg);
            TraceHelper.traceInformation("Exiting Thread Begins");
            exitingThread.start();
            
            synchronized (exitingThread)
            {
                // wait exitingThread to finish within millis seconds
                exitingThread.wait(millis);
            }
            // to judge if it finished or time is up
            if (exitingThread.isAlive())
            {
                exitingThread.interrupt();
            }
        } catch (InterruptedException e)
        {
            TraceHelper.traceInformation("Exiting Thread Ends");
            return;
        }
        TraceHelper.traceInformation("Exiting Thread Ends");
    }
}
