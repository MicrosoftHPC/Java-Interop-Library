// ------------------------------------------------------------------------------
// <copyright file="Utility.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Utility for service host
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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.microsoft.hpc.properties.ErrorCode;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceRegistration;
import com.microsoft.hpc.scheduler.session.servicecontext.StringResource;
import com.microsoft.hpc.scheduler.session.servicecontext.TraceHelper;

/**
 * @author t-junchw
 * @date May 10, 2011
 * @description utility for service host
 */
public class Utility
{
    /**
     * @description get service registration info from config file
     * @param serviceConfigName service configuration file
     * @param exitCode error code
     * @return ServiceRegistration
     */
    public static ServiceRegistration getServiceRegistration(String serviceConfigName,
            AtomicReference<Object> exitCode)
    {
        // open the service registration configuration section
        ServiceRegistration serviceRegistration = new ServiceRegistration();
        // initiate the exitCode
        exitCode.set(ErrorCode.Success);
        
        DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
        String serviceAssemblyFullPath = null;
        try
        {
            // parse the service registration XML
            DocumentBuilder dombuilder = domfac.newDocumentBuilder();
            InputStream is = new FileInputStream(serviceConfigName);
            Document doc = dombuilder.parse(is);
            Element root = doc.getDocumentElement();
            serviceRegistration.setserviceConfigNode(root
                    .getElementsByTagName(serviceRegistration.serviceConfigurationsName));
            serviceRegistration.setEnableWSAddressing(doc);
            serviceAssemblyFullPath = serviceRegistration.getServiceAssemblyFullPath();
            try
            {
                serviceRegistration.setLoggerLevel(root
                        .getElementsByTagName(serviceRegistration.serviceDiagnosticsName));
            } catch (Exception e)
            {
                // system.diagnostics tag in hpc xml is not necessary
            }
            TraceHelper.traceInformation("Trace Level is "+serviceRegistration.traceLevel);
            ServiceContext.setTraceLevel(serviceRegistration.traceLevel);

        } catch (Exception e)
        {
            // exception occurs when parsing the XML
            ServiceContext.Logger.traceEvent(Level.SEVERE, e.toString());
            TraceHelper.traceError(e.toString());
            exitCode.set(ErrorCode.ServiceHost_BadServiceRegistrationFileFormat);
            //return null;
        }

        if (serviceAssemblyFullPath == null || serviceAssemblyFullPath.isEmpty())
        {
            String message = String.format(StringResource.getResource("AssemblyFileNotRegistered"),
                    serviceConfigName);
            TraceHelper.traceError(message);
            ServiceContext.Logger.traceEvent(Level.SEVERE, message);
            exitCode.set(ErrorCode.ServiceHost_AssemblyFileNameNullOrEmpty);
            //return null;
        }

        if (!new File(serviceAssemblyFullPath).exists())
        {
            // If the obtained service assembly path is not valid or not
            // existing
            String message = String.format(StringResource.getResource("AssemblyFileCantFind"),
                    serviceAssemblyFullPath);
            TraceHelper.traceError(message);
            ServiceContext.Logger.traceEvent(Level.SEVERE, message);
            exitCode.set(ErrorCode.ServiceHost_AssemblyFileNotFound);
        }
        return serviceRegistration;
    }

}
