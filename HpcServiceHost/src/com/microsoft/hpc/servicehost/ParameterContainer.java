// ------------------------------------------------------------------------------
// <copyright file="ParameterContainer.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Contain parameters for the debug mode in the service host
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author t-junchw
 * @date May 10, 2011
 * @description the container of command parameters
 */
public class ParameterContainer {

    /**
     * @field store all the parameters in a list
     */
    private List<Parameter> parameters = new ArrayList<Parameter>();

    /**
     * @field job id parameter
     */
    private IntParameter jobIdParameter = new IntParameter("/JobId",
            "the job id");

    /**
     * @field task id parameter
     */
    private IntParameter taskIdParameter = new IntParameter("/TaskId",
            "the task id");

    /**
     * @field code id parameter
     */
    private IntParameter coreIdParameter = new IntParameter("/CoreId",
            "the core id");

    /**
     * @field service name parameter
     */
    private StringParameter fileNameParameter = new StringParameter(
            "/RegistrationFileName",
            "the name of the service registration file");

    /**
     * @field service registration path parameter
     */
    private StringParameter pathParameter = new StringParameter(
            "/RegistrationPath",
            "the full path of the service registration folder");

    /**
     * CCP DATA path parameter
     */
    private StringParameter cCPDATAParameter = new StringParameter("/CcpData",
            "the path of the CCPData folder");

    /**
     * SOA_HOME path parameter
     */
    private StringParameter soaHomeParameter = new StringParameter("/SoaHome",
            "the path of the SOA_HOME folder");

    /**
     * @field service registration path parameter
     */
    private IntParameter taskinstanceIdParameter = new IntParameter(
            "/TaskInstanceId", "the task instance id");

    /**
     * ENABLE_BACKEND_SECURITY parameter
     */
    private StringParameter enableSecurityParameter = new StringParameter("/EnableSecurity",
            "whether to enable backend security (true or false)");

    /**
     * @field command line arguments
     */
    private String[] args;

    /**
     * @description constructor of the ParameterContainer class.
     * @param args
     */
    public ParameterContainer(String[] args) {
        this.args = args;
        this.parameters.add(this.jobIdParameter);
        this.parameters.add(this.taskIdParameter);
        this.parameters.add(this.coreIdParameter);
        this.parameters.add(this.fileNameParameter);
        this.parameters.add(this.pathParameter);
        this.parameters.add(this.cCPDATAParameter);
        this.parameters.add(this.soaHomeParameter);
        this.parameters.add(this.taskinstanceIdParameter);
        this.parameters.add(this.enableSecurityParameter);
    }

    /**
     * @return CCP_Data
     */
    public StringParameter getCCPDATAParameter() {
        return cCPDATAParameter;
    }

    public void setCCPDATAParameter(StringParameter ccpdataparameter) {
        this.cCPDATAParameter = ccpdataparameter;
    }

    /**
     * @return SOA_HOME
     */
    public StringParameter getSoaHomeParameter() {
        return soaHomeParameter;
    }

    public void setSoaHomeParameter(StringParameter soahomeparameter) {
        this.soaHomeParameter = soahomeparameter;
    }

    /**
     * @return task instance id
     */
    public IntParameter getTaskinstanceIdParameter() {
        return taskinstanceIdParameter;
    }

    public void setTaskinstanceIdParameter(IntParameter taskinstanceidparameter) {
        this.taskinstanceIdParameter = taskinstanceidparameter;
    }

    /**
     * @return job id
     */
    public IntParameter getJobIdParameter() {
        return jobIdParameter;
    }

    /**
     * @param jobIdParameter
     */
    public void setJobIdParameter(IntParameter jobIdParameter) {
        this.jobIdParameter = jobIdParameter;
    }

    /**
     * @return task id
     */
    public IntParameter getTaskIdParameter() {
        return taskIdParameter;
    }

    /**
     * @param taskIdParameter
     */
    public void setTaskIdParameter(IntParameter taskIdParameter) {
        this.taskIdParameter = taskIdParameter;
    }

    /**
     * @return core id
     */
    public IntParameter getCoreIdParameter() {
        return coreIdParameter;
    }

    /**
     * @param coreIdParameter
     */
    public void setCoreIdParameter(IntParameter coreIdParameter) {
        this.coreIdParameter = coreIdParameter;
    }

    /**
     * @returnsoa service name
     */
    public StringParameter getFileNameParameter() {
        return fileNameParameter;
    }

    /**
     * @param fileNameParameter
     */
    public void setFileNameParameter(StringParameter fileNameParameter) {
        this.fileNameParameter = fileNameParameter;
    }

    /**
     * @return full path of service registration folder
     */
    public StringParameter getPathParameter() {
        return pathParameter;
    }

    /**
     * @param pathParameter
     */
    public void setPathParameter(StringParameter pathParameter) {
        this.pathParameter = pathParameter;
    }

    /**
     * @return full path of service registration folder
     */
    public StringParameter getEnableSecurity() {
        return enableSecurityParameter;
    }

    /**
     * @param pathParameter
     */
    public void setEnableSecurity(StringParameter securityParameter) {
        this.enableSecurityParameter = securityParameter;
    }

    /**
     * @description Parse the parameters
     * @throws ParseException
     */
    public void Parse() throws ParseException {
        for (Parameter p : this.parameters) {
            p.parse(this.args);
        }
    }

    /**
     * @description print the help information of command
     * @return print help instruction successfully or not
     */
    public boolean printHelp() {
        Set<String> helpParameters = new HashSet<String>();
        helpParameters.add("/h");
        helpParameters.add("-h");
        helpParameters.add("/help");
        helpParameters.add("-help");
        helpParameters.add("/?");
        helpParameters.add("-?");

        boolean exist = false;
        for (String arg : this.args) {
            exist = helpParameters.contains(arg);
            if (exist) {
                break;
            }
        }

        if (exist) {
            System.out.println("Usage:");
            System.out
                    .println("java [option] -jar /JobId jid /TaskId tid /CoreId cid "
                            + "/RegistrationFileName filename /RegistrationPath path "
                            + "/Ccp_Data ccpdata /TaskInstanceId taskinstancsid "
                            + "$HpcServiceHost.jar$");

            System.out.println();
            System.out.println("Parameters:");
            for (Parameter p : this.parameters) {
                p.printHelp();
            }
            System.out.println();
            return true;
        } else {
            return false;
        }
    }

}
