//------------------------------------------------------------------------------
// <copyright file="CxfBrokerControllerClient.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      CxfBrokerControllerClient class
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

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.AddressingFeature;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

import com.microsoft.hpc.ArrayOfRequestData;
import com.microsoft.hpc.ArrayOfTraceEventItem;
import com.microsoft.hpc.RequestContinuationToken;
import com.microsoft.hpc.RequestData;
import com.microsoft.hpc.RequestState;
import com.microsoft.hpc.soadiagservice.ISoaDiagService;
import com.microsoft.hpc.soadiagservice.ISoaDiagServiceCleanupTraceSessionFaultFaultFaultMessage;
import com.microsoft.hpc.soadiagservice.ISoaDiagServiceDumpTraceSessionFaultFaultFaultMessage;
import com.microsoft.hpc.soadiagservice.ISoaDiagServiceQueryForRequestSessionFaultFaultFaultMessage;
import com.microsoft.hpc.soadiagservice.ISoaDiagServiceQuerySessionTraceSessionFaultFaultFaultMessage;
import com.microsoft.hpc.soadiagservice.ISoaDiagServiceQueryUserTraceByRequestSessionFaultFaultFaultMessage;

public class CxfSoaDiagClient {
    ISoaDiagService soadiagservice;
    String epr;

    public CxfSoaDiagClient(String hostname) {
        this.epr = String.format("http://%s/SoaDiagService", hostname);

        QName portName = new QName("http://hpc.microsoft.com",
                "HpcSoaDiagServiceHttpPort");

        javax.xml.ws.Service soaService = javax.xml.ws.Service
                .create(new QName("http://hpc.microsoft.com/SoaDiagService/",
                        "SoaDiagService"));
        soaService.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, this.epr);

        soadiagservice = soaService.getPort(portName, ISoaDiagService.class);

        // Enable logging
        Client client = ClientProxy.getClient(soadiagservice);
        org.apache.cxf.endpoint.Endpoint cxfEndpoint = client.getEndpoint();
        cxfEndpoint.getInInterceptors().add(new LoggingInInterceptor());
        cxfEndpoint.getOutInterceptors().add(new LoggingOutInterceptor());

    }

    public void cleanupTrace(int sessionId) throws SessionException {
        try {
            soadiagservice.cleanupTrace(sessionId);
        } catch (ISoaDiagServiceCleanupTraceSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public void queryUserTraceByRequest(int sessionId, RequestData requestData) throws SessionException {
        Holder<com.microsoft.hpc.RequestData> request = new Holder<com.microsoft.hpc.RequestData>();
        request.value = requestData;
        try {
            soadiagservice.queryUserTraceByRequest(sessionId, request);
            requestData.setDispatchHistory(request.value.getDispatchHistory());
            requestData.setContext(request.value.getContext());
        } catch (ISoaDiagServiceQueryUserTraceByRequestSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
    }

    public List<RequestData> queryForRequest(int sessionId, RequestState state) throws SessionException {
        List<RequestData> requests = null;
        Holder<RequestContinuationToken> token = new Holder<RequestContinuationToken>();
        Holder<ArrayOfRequestData> queryForRequestResult = new Holder<ArrayOfRequestData>();
        try {
            soadiagservice.queryForRequest(sessionId, state, token, queryForRequestResult);
            requests = queryForRequestResult.value.getRequestData();
        } catch (ISoaDiagServiceQueryForRequestSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
        return requests; 
    }

    public ArrayOfTraceEventItem querySessionTrace(int sessionId)
            throws SessionException {
        ArrayOfTraceEventItem items = null;
        try {
            items = soadiagservice.querySessionTrace(sessionId);
        } catch (ISoaDiagServiceQuerySessionTraceSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
        return items;
    }

    public byte[] dumpTrace(int sessionId) throws SessionException {
        byte[] data = null;
        try {
            data = soadiagservice.dumpTrace(sessionId);
        } catch (ISoaDiagServiceDumpTraceSessionFaultFaultFaultMessage e) {
            throw Utility.translateFaultException(e.getFaultInfo());
        }
        return data;
    }
}