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
package com.microsoft.hpc.scheduler.session.servicecontext.etw;

import java.util.List;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.w3c.dom.Element;

import com.microsoft.hpc.scheduler.session.Constant;
import com.microsoft.hpc.scheduler.session.servicecontext.Environment;
import com.microsoft.hpc.scheduler.session.servicecontext.JavaTraceLevelConverterEnum;

/**
 * @author t-hengz
 * @date Aug 6, 2012
 * @description Write event with ETW
 */
public class ETWTraceEvent {
    /**
     * @field messageid dispatchid sessionid used for ETW
     */
	private UUID	messageid;
	private UUID	dispatchid;
	private int		sessionid;
	
    /**
     * @description get messageid dispatchid sessionid from headers
     * @param wsContext
     */
	public ETWTraceEvent(WebServiceContext wsContext)
	{
		//get headers
		MessageContext mc = wsContext.getMessageContext();
	    Message message = ((WrappedMessageContext) mc).getWrappedMessage();
	    List<Header> headers = CastUtils.cast((List<?>) message.get(Header.HEADER_LIST));
	    
	    //get dispatchid & messageid
	    for (Header h : headers) {
	        QName name = h.getName();
	        if (name.getLocalPart().equals(Constant.DISPATCHIDLOCALPART) &&
	                name.getNamespaceURI().equals(Constant.HpcHeaderNS))
	        {
                Element e = (Element) h.getObject();
                try{
                	dispatchid = UUID.fromString(e.getTextContent());
                }
                catch(Exception exception){
                	dispatchid = null;
                }
            }
			if (name.getLocalPart().equals(Constant.MESSAGEIDLOCALPART) &&
	                name.getNamespaceURI().equals(Constant.HpcActionHeaderNS))
			{
				Element e = (Element) h.getObject();
				//cause the format is urn:uuid:xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
				try{
					messageid = UUID.fromString(e.getTextContent().toString().substring(9));
				}
				catch(Exception exception){
					messageid = null;
				}
			}
        }
	    
	    //getsessionid
	    sessionid = Integer.parseInt(Environment.getEnvironmentVariable(Constant.JobIDEnvVar));
	}
	
    /**
     * @param message
     */
	public int TraceCritical(String msg)
	{
		return UserEvent.WriteUserEvent(JavaTraceLevelConverterEnum.Critical, sessionid, messageid, dispatchid, msg);
	}
	
    /**
     * @param message
     */	
	public int TraceError(String msg)
	{
		return UserEvent.WriteUserEvent(JavaTraceLevelConverterEnum.Error, sessionid, messageid, dispatchid, msg);
	}
	
    /**
     * @param message
     */	
	public int TraceWarning(String msg)
	{
		return UserEvent.WriteUserEvent(JavaTraceLevelConverterEnum.Warning, sessionid, messageid, dispatchid, msg);
	}
	
    /**
     * @param message
     */	
	public int TraceInfo(String msg)
	{
		return UserEvent.WriteUserEvent(JavaTraceLevelConverterEnum.Information, sessionid, messageid, dispatchid, msg);
	}
	
    /**
     * @param message
     */	
	public int TraceVerbose(String msg)
	{
		return UserEvent.WriteUserEvent(JavaTraceLevelConverterEnum.Verbose, sessionid, messageid, dispatchid, msg);
	}
}
