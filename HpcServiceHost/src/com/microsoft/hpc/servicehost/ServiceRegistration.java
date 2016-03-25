// ------------------------------------------------------------------------------
// <copyright file="ServiceRegistration.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Save the Service Registration Information
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

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.microsoft.hpc.properties.ErrorCode;
import com.microsoft.hpc.scheduler.session.servicecontext.JavaTraceLevelConverterEnum;
import com.microsoft.hpc.scheduler.session.servicecontext.TraceHelper;

/**
 * @author t-junchw
 * @date May 10, 2011
 * @description store the service registration information
 */
public class ServiceRegistration
{

    public final String serviceConfigurationsName = "microsoft.Hpc.Session.ServiceRegistration";
    public final String serviceDiagnosticsName = "system.diagnostics";
    private final String sourcesNodeName = "sources";
    private final String sourceNodeName = "source";
    private final String traceLevelAttributeName = "switchValue";
    private final String serviceNodeName = "service";
    private final String assemblyAttributeName = "assembly";
    private final String serviceContractAttributeName = "contract";
    private final String serviceTypeAttributeName = "ServiceType";
    private final String includeFaultedExceptionAttributeName = "includeFaultedException";
    private final String soaDiagTraceLevelAttributeName = "soaDiagTraceLevel";
    public Node serviceConfigNode = null;
    public String serviceAssemblyFullPath = null;
    public String traceLevel = "OFF";
    public String soaDiagTraceLevel = "Off";
    
    private boolean includeFaultedException;

    public boolean isIncludeFaultedException()
    {
        return includeFaultedException;
    }

    /**
     * @field user service type name
     */
    public String serviceTypeName;

    /**
     * @field user service Contact name
     */
    public String serviceContractName;

    /**
     * @return serviceTypeName
     */
    public String getServiceTypeName()
    {
        return serviceTypeName;
    }

    /**
     * @return serviceContractName
     */
    public String getServiceContractName()
    {
        return serviceContractName;
    }
    

    
    /**
     * @field user jar file name
     */
    public String serviceAssemblyFileName;

    /**
     * @description parse the XML node list to get the service registration settings
     * @param nodelist
     */
    public void setserviceConfigNode(NodeList nodelist)
    {
        NodeList serviceConfigurationNodeList = nodelist;
        if (serviceConfigurationNodeList != null)
        {
            // get the user jar setting information
            Node rootNode = serviceConfigurationNodeList.item(0);
            for (rootNode = rootNode.getFirstChild(); rootNode != null; rootNode = rootNode
                    .getNextSibling())
            {
                if (rootNode.getNodeName() == serviceNodeName)
                {
                    serviceConfigNode = rootNode;
                    NamedNodeMap map = serviceConfigNode.getAttributes();
                    try
                    {
                        serviceAssemblyFullPath = map.getNamedItem(
                                assemblyAttributeName).getNodeValue();
                    } catch (DOMException e)
                    {
                        TraceHelper.traceError(e.toString());
                        System.exit(ErrorCode.ServiceHost_BadServiceRegistrationFileFormat);
                    }
                    
                    try
                    {
                        serviceContractName = map.getNamedItem(
                                serviceContractAttributeName).getNodeValue();

                    } catch (Exception e)
                    {
                        TraceHelper.traceError(e.toString());
                    }
                    
                    try
                    {
                        serviceTypeName = map.getNamedItem(
                                serviceTypeAttributeName).getNodeValue();

                    } catch (Exception e)
                    {
                        TraceHelper.traceError(e.toString());
                    }
                    
                    try
                    {
                        includeFaultedException = Boolean.parseBoolean(map
                                .getNamedItem(
                                        includeFaultedExceptionAttributeName)
                                .getNodeValue());

                    } catch (Exception e)
                    {
                        TraceHelper.traceError(e.toString());
                    }
                    
                    try 
                    {
                        if(map.getNamedItem(soaDiagTraceLevelAttributeName) != null &&
                                map.getNamedItem(soaDiagTraceLevelAttributeName).getNodeValue() != null) {
                            soaDiagTraceLevel = map.getNamedItem(soaDiagTraceLevelAttributeName).getNodeValue().split(",")[0];
                        }
                    } catch(Exception e) {
                        TraceHelper.traceError(e.toString());
                    }
                }
            }
        }
    }

    /**
     * @description parse the XML node list to get the java trace level
     * @param nodeList
     */
    public void setLoggerLevel(NodeList nodeList)
    {
        if (nodeList != null)
        {
            // get the java trace setting information
            Node rootNode = nodeList.item(0);
            for (rootNode = rootNode.getFirstChild(); rootNode != null; rootNode = rootNode
                    .getNextSibling())
            {
                if (rootNode.getNodeName() == sourcesNodeName)
                {
                    for (Node subNode = rootNode.getFirstChild(); subNode != null; subNode = subNode
                            .getNextSibling())
                    {
                        if (subNode.getNodeName() == sourceNodeName)
                        {
                            NamedNodeMap map = subNode.getAttributes();
                            try
                            {
                                traceLevel = map.getNamedItem(
                                        traceLevelAttributeName).getNodeValue();
                                traceLevel = traceLevel.substring(0,
                                        traceLevel.indexOf(','));
                                
                                // transfer the ETW trace level to java version
                                traceLevel = JavaTraceLevelConverterEnum.valueOf(traceLevel.trim()).getValue();
                                break;
                            } catch (DOMException e)
                            {
                                TraceHelper.traceError(e.toString());
                                System.exit(ErrorCode.ServiceHost_BadServiceRegistrationFileFormat);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @return serviceAssemblyFullPath
     */
    public String getServiceAssemblyFullPath()
    {
        return serviceAssemblyFullPath;
    }

}
