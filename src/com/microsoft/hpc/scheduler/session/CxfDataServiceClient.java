//------------------------------------------------------------------------------
// <copyright file="CxfClientBase.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      CxfDataServiceClient class
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

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import com.microsoft.hpc.dataservice.IDataService;
import com.microsoft.hpc.dataservice.IDataServiceAssociateDataClientWithSessionDataFaultFaultFaultMessage;
import com.microsoft.hpc.dataservice.IDataServiceCreateDataClientDataFaultFaultFaultMessage;
import com.microsoft.hpc.dataservice.IDataServiceDeleteDataClientDataFaultFaultFaultMessage;
import com.microsoft.hpc.dataservice.IDataServiceOpenDataClientDataFaultFaultFaultMessage;
import com.microsoft.hpc.exceptions.DataException;

/**
 * @author junsu
 * Wrapper for cxf data service client
 */
public class CxfDataServiceClient extends CxfClientBase
{
    private String epr;
    IDataService dataServiceClient;

    public CxfDataServiceClient(String username, String password,
            String headnode) {
        this.epr = "https://" + headnode + "/DataService";

        QName portName = new QName("http://hpc.microsoft.com",
                "HpcDataServiceHttpsPort");
        javax.xml.ws.Service soaService = Service.create(new QName(
                "http://hpc.microsoft.com/dataservice/", "DataService"));
        soaService.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, this.epr);
        dataServiceClient = soaService.getPort(portName, IDataService.class);

        Initialize(username, password, dataServiceClient);
    }

    public String openDataClient(String dataClientId) throws DataException {
        try {
            return dataServiceClient.openDataClient(dataClientId);
        } catch (IDataServiceOpenDataClientDataFaultFaultFaultMessage e) {
            throw Utility.translateDataFaultException(e.getFaultInfo());
        }
    }

    public String createDataClient(String dataclientid, String[] allowedusers)
            throws DataException {
        com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring allowedUsersArray = null;
        if (allowedusers != null) {
            allowedUsersArray = new com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring();
            for (String user : allowedusers) {
                allowedUsersArray.getString().add(user);
            }
        }

        try {
            return dataServiceClient.createDataClient(dataclientid,
                    allowedUsersArray);
        } catch (IDataServiceCreateDataClientDataFaultFaultFaultMessage e) {
            throw Utility.translateDataFaultException(e.getFaultInfo());
        }
    }

    public void deleteDataClient(String dataclientid) throws DataException {
        try {
            dataServiceClient.deleteDataClient(dataclientid);
        } catch (IDataServiceDeleteDataClientDataFaultFaultFaultMessage e) {
            throw Utility.translateDataFaultException(e.getFaultInfo());
        }
    }

    public void associateDataClientWithSession(String dataClientId,
            int sessionId) throws DataException {
        try {
            dataServiceClient.associateDataClientWithSession(dataClientId,
                    sessionId);
        } catch (IDataServiceAssociateDataClientWithSessionDataFaultFaultFaultMessage e) {
            throw Utility.translateDataFaultException(e.getFaultInfo());
        }
    }
}
