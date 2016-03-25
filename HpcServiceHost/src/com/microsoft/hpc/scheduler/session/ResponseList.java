//------------------------------------------------------------------------------
// <copyright file="ResponseList.java" company="Microsoft">
//   Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//   Implement ResponseList class
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


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;



/**
 * this class is used to maintain the list of response
 * 
 * @author junsu
 * 
 * @param <E>
 */
class ResponseList<E> implements java.util.Iterator<E>, Runnable, Iterable<E>
{
    static private int windowSize = 100;
    private boolean eomReceived = false;

    private CxfBrokerControllerClient controllerClient;
    private java.util.concurrent.ConcurrentLinkedQueue<E> queue;
    private String action;
    private String clientId;
    int timeoutMS;
    private Exception innerException;
    Map<String, Class<?>> responseTypeMapping;

    public ResponseList(Class<?> messageClass,
            CxfBrokerControllerClient controllerClient, String action,
            String clientId, int timeoutMS) {
        this.responseTypeMapping = new HashMap<String, Class<?>>();
        this.responseTypeMapping.put(action, messageClass);
        this.clientId = clientId;
        this.action = action;
        this.timeoutMS = timeoutMS;
        queue = new java.util.concurrent.ConcurrentLinkedQueue<E>();
        this.controllerClient = controllerClient;
        Thread thread = new Thread(this);
        thread.start();
        innerException = null;
    }

    public ResponseList(Map<String, Class<?>> typeMapping, CxfBrokerControllerClient controllerClient,
            String clientId, int timeoutMS) {
        this.responseTypeMapping = typeMapping;
        this.clientId = clientId;
        this.action = "";
        this.timeoutMS = timeoutMS;
        queue = new java.util.concurrent.ConcurrentLinkedQueue<E>();
        this.controllerClient = controllerClient;
        Thread thread = new Thread(this);
        thread.start();
        innerException = null;
    }
    
    private void cleanup() {
        if (controllerClient != null) {
            this.controllerClient.destory();
            this.controllerClient = null;
        }
    }

    @Override
    public boolean hasNext() {
        while (queue.isEmpty()) {
            if (eomReceived) 
            	return false;
            
            synchronized (queue) {
                // wait queue is notify
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                }
            }

            if (innerException != null) {
                RuntimeException ex = new RuntimeException(innerException);
                throw ex;
            }    
        }
        
        assert(!queue.isEmpty());
        
        return true;
    }
    
    @Override
    public E next() {
		assert(!queue.isEmpty());
		if (hasNext())
		    return queue.remove();
		else
		    throw new NoSuchElementException();
    }

    /**
     * not support to remove
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void run() {
        boolean first = true;
        controllerClient.setTimeout(timeoutMS);
        while (!eomReceived) {
            try {
                eomReceived = controllerClient.pullResponses(responseTypeMapping,
                        action, first, windowSize, clientId, queue);
                first = false;
            } catch (Exception ex) {
                innerException = ex;
            }
            synchronized (queue) {
                queue.notify();
            }
            
            if (innerException != null)
                break;
        }
        
        cleanup();
    }

    @Override
    public Iterator<E> iterator() {
        return this;
    }
}
