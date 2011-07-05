//------------------------------------------------------------------------------
// <copyright file="DurableSession.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      The implementation of the Durable Session Class
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

import java.net.SocketTimeoutException;

import com.microsoft.hpc.sessionlauncher.SessionInfo;

/**
 * Creates a session whose requests and responses gets stored in MSMQ.
 * <p>
 * This allows a user to disconnect the client or use a different client
 * altogether to retrieve messages.
 * </p>
 * <p>
 * Constructors are called from <code>SessionBase</code> during
 * <code>CreateSession</code>
 * </p>
 * 
 * @See {@link #createSession(SessionStartInfo)}
 */
public class DurableSession extends SessionBase
{
    /**
     * Initializes a new instance of the DurableSession class
     * 
     * @param info
     *            Session info
     * @param headnode
     *            the head node machine name.
     * @param traceSource
     *            the trace source
     */
    DurableSession(SessionInfo info, String headnode) {
        super(info, headnode);
    }

    /**
     * Creates a new instance of durable session
     * 
     * @param startInfo
     *            the session start info
     * @return a durable session instance
     * @throws SessionException
     *             If an exception occurred during the setup of a session
     * @throws TimeoutException
     *             If the setup of a session took too long
     */
    public static DurableSession createSession(SessionStartInfo startInfo)
            throws SocketTimeoutException, SessionException {
        return createSession(startInfo, 0);
    }

    /**
     * Create a durable session
     * 
     * @param startInfo
     *            the session start info
     * @param timeoutMilliseconds
     *            timeout for creating the session
     * @return a durable session instance
     * @throws SessionException
     *             If an exception occurred during the setup of a session
     * @throws TimeoutException
     *             If the setup of a session takes too long
     */
    public static DurableSession createSession(SessionStartInfo startInfo,
            int timeoutMilliseconds) throws SocketTimeoutException,
            SessionException {
        if (!startInfo.getTransportScheme().contains(
                TransportScheme.Http.getName()))
            throw new IllegalArgumentException(
                    SR.v("TransportSchemeNotSupport"));
        return (DurableSession) createSessionInternal(startInfo,
                timeoutMilliseconds, true);

    }

    /**
     * Attach to an existing session with session id
     * 
     * @param attachInfo
     *            the attach info
     * @param timeoutMilliseconds
     *            timeout for attaching to the session
     * @return a durable session instance
     * @throws SessionException
     *             If an exception occurred while trying to connect to an
     *             existing session
     */
    public static DurableSession attachSession(SessionAttachInfo attachInfo,
            int timeoutMilliseconds) throws SessionException,
            SocketTimeoutException {
        return (DurableSession) attachInternal(attachInfo, timeoutMilliseconds);
    }

    /**
     * Attach to an existing session with session id
     * 
     * @param attachInfo
     *            the attach info
     * @return the durable session instance
     * @throws SessionException
     *             If an exception occurred while trying to connect to an
     *             existing session
     */
    public static DurableSession attachSession(SessionAttachInfo attachInfo)
            throws SessionException, SocketTimeoutException {
        return attachSession(attachInfo, 0);
    }

    /**
     * the help function to check the sanity of SessionStartInfo before creating
     * a session
     * 
     * @param startInfo
     */
    protected static void checkSessionStartInfo(SessionStartInfo startInfo) {
        SessionBase.checkSessionStartInfo(startInfo);
        if (TransportScheme.valueOf(startInfo.getTransportScheme().get(0)) != TransportScheme.Http) {
            throw new UnsupportedOperationException(
                    SR.v("TransportSchemeNotSupport"));
        }
    }
}
