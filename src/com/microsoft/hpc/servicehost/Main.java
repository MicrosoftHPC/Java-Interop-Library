// ------------------------------------------------------------------------------
// <copyright file="Main.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// the set-up method for the java service host
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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicReference;
import com.microsoft.hpc.properties.ErrorCode;
import com.microsoft.hpc.scheduler.session.Constant;
import com.microsoft.hpc.scheduler.session.servicecontext.Environment;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceRegistration;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceRegistrationRepo;
import com.microsoft.hpc.scheduler.session.servicecontext.StringResource;
import com.microsoft.hpc.scheduler.session.servicecontext.TraceHelper;

/**
 * @author t-junchw
 * @date May 9, 2011
 * @description entry point of service host 
 */
public class Main
{
    /**
     * @param args
     *            java -jar Microsoft-HpcHost-3.0.jar
     *            /JobId jid /TaskSystemId tid /CoreId cid 
     *            /RegistrationFileName filename /RegistrationPath path 
     *            /Ccp_Data ccpdata /TaskInstanceId taskinstancsid 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {

        TraceHelper.traceInformation("HpcServiceHost entry point is called.");

        AtomicReference<Object> exitCode = new AtomicReference<Object>(0);
        if (RunPrePostTask(exitCode))
        {
            // return the exitcode
            System.exit(Integer.parseInt(exitCode.get().toString()));
        }

        // jar running without a job
        if (args.length > 0)
        {
            // set the debug mode true
            Environment.isDebug = true;
            
            ParameterContainer param = new ParameterContainer(args);
            try
            {
                if (param.printHelp())
                {
                    System.exit(ErrorCode.ServiceHost_PrintCommandHelp);
                } else
                {
                    param.Parse();
                }
            } catch (ParseException e)
            {
                TraceHelper.traceError(e.toString());
                System.exit(ErrorCode.ServiceHost_IncorrectCommandLineParameter);
            } catch (Exception e)
            {
                TraceHelper.traceError(e.toString());
                System.exit(ErrorCode.ServiceHost_UnexpectedException);
            }

            // set the Environment Variable for the debug
            Environment.setEnvironmentVariable(Constant.JobIDEnvVar, param.getJobIdParameter().getValue().toString());
            Environment.setEnvironmentVariable(Constant.TaskSystemIDEnvVar, param.getJobIdParameter().getValue().toString());
            Environment.setEnvironmentVariable(Constant.CoreIdsEnvVar, param.getCoreIdParameter().getValue().toString());
            Environment.setEnvironmentVariable(Constant.RegistryPathEnv, param.getPathParameter().getValue());
            Environment.setEnvironmentVariable(Constant.ServiceConfigFileNameEnvVar, param.getFileNameParameter()
                    .getValue());
            Environment.setEnvironmentVariable(Constant.CCP_DATAEnvVar, param.getCCPDATAParameter().getValue());
            Environment.setEnvironmentVariable(Constant.TASKINSTANCEIDEnvVar, param.getTaskinstanceIdParameter()
                    .getValue().toString());
            // use default values for following environment variables
            Environment.setEnvironmentVariable(Constant.ProcNumEnvVar, "1");
            Environment.setEnvironmentVariable(Constant.NetworkPrefixEnv, Constant.EnterpriseNetwork);
            Environment.setEnvironmentVariable(Constant.ServiceInitializationTimeoutEnvVar, "60000");
            Environment.setEnvironmentVariable(Constant.CancelTaskGracePeriodEnvVar, "15");
            Environment.setEnvironmentVariable(Constant.ServiceConfigServiceOperatonTimeoutEnvVar, "86400000");
        }

        String serviceConfigFullPath;
        HpcServiceHostWrapper hpcServiceHostWrapper = null;
        boolean isOpenDummy = false;
        try
        {
            String serviceConfigFileName = Environment.getEnvironmentVariable(Constant.ServiceConfigFileNameEnvVar);

            // exit if there is not such env var
            if (serviceConfigFileName == null || serviceConfigFileName.isEmpty())
            {
                isOpenDummy = true;
                TraceHelper.traceError(StringResource.getResource("ServiceConfigFileNameNotSpecified"));
                throw new Exception(String.valueOf(ErrorCode.ServiceHost_ServiceConfigFileNameNotSpecified));
            }

            //get the service config file full path
            serviceConfigFullPath = GetServiceInfo(serviceConfigFileName);
            if (!new File(serviceConfigFullPath).exists())
            {
                isOpenDummy = true;
                TraceHelper.traceError(StringResource
                        .getResource("CantFindServiceRegistrationFileserviceConfigFullPath"));
                throw new Exception(String.valueOf(ErrorCode.ServiceHost_ServiceRegistrationFileNotFound));
            }

            TraceHelper.traceInformation("serviceConfigFullPath = " + serviceConfigFullPath);

            //import the configuration to the service registration
            ServiceRegistration serviceRegistration = Utility.getServiceRegistration(serviceConfigFullPath, exitCode);
            if ((Integer) exitCode.get() != ErrorCode.Success)
            {
                isOpenDummy = true;
                throw new Exception(exitCode.get().toString());
            }
             
            // Open the host
            hpcServiceHostWrapper = new HpcServiceHostWrapper(serviceRegistration);
            hpcServiceHostWrapper.publish();
            
        } catch (Exception e)
        {
            TraceHelper.traceError(e.toString());
            TraceHelper.traceStackError(e);
        }
        finally
        {
            if(isOpenDummy)
            {
                if(hpcServiceHostWrapper==null)
                {
                    hpcServiceHostWrapper = new HpcServiceHostWrapper(new ServiceRegistration());
                }
                hpcServiceHostWrapper.openDummyService();
            }
        }
    }

    /**
     * @description get the service config file full path by the file name
     * @param serviceConfigFileName
     * @return service config file full path
     */
    private static String GetServiceInfo(String serviceConfigFileName)
    {
        String serviceConfigFile = null;

        try
        { 
            
            String headnode = Environment.getEnvironmentVariable(Constant.HeadnodeEnvVar);
            TraceHelper.traceInformation(Environment.getEnvironmentVariable(Constant.RegistryPathEnv));
            ServiceRegistrationRepo serviceRegistration = new ServiceRegistrationRepo(headnode,
                    Environment.getEnvironmentVariable(Constant.RegistryPathEnv));
                
            // Get the path to the service config file name
            serviceConfigFile = serviceRegistration.getServiceRegistrationPath(serviceConfigFileName);
            
            if (serviceConfigFile == null)
            {
                // Make a part for the error message
                String CentrialPath = serviceRegistration.getCentrialPath();

                StringBuilder serviceRegDirsBuilder = new StringBuilder();
                if (CentrialPath != null && !CentrialPath.isEmpty())
                {
                    serviceRegDirsBuilder.append("\n\t");
                    serviceRegDirsBuilder.append(CentrialPath);
                }
                serviceRegDirsBuilder.append("\n");

                TraceHelper.traceError(String.format(
                        StringResource.getResource("CannotFindServiceRegistrationFileUnderFolders"),
                        serviceRegDirsBuilder.toString()));
            }
        } catch (Exception e)
        {   
            e.printStackTrace();
            TraceHelper.traceError(String.format(StringResource.getResource("ExceptionInReadingRegistrationFile"),
                    serviceConfigFile, e.toString()));
        }

        return serviceConfigFile;
    }

    /**
     * @description run a pre or post task if it exists
     * @param exitCode
     * @return
     */
    private static boolean RunPrePostTask(AtomicReference<Object> exitCode)
    {
        boolean prePostTaskExists = false;
        String prePostTaskCommandLine = Environment.getEnvironmentVariable(Constant.PrePostTaskCommandLineEnvVar);

        // Check if a pre/post task exists
        prePostTaskExists = prePostTaskCommandLine != null;

        if (prePostTaskExists)
        {
            String serviceWorkingDirectory = null;
            serviceWorkingDirectory = Environment.getEnvironmentVariable(Constant.PrePostTaskOnPremiseWorkingDirEnvVar);
           
            // Run command from comspec (like node manager babysitter) to ensure
            // env vars are expanded and command runs as if launched from node manager
            String commandLine = String.format("\"%s\" /S /c \"%s\"", Environment.getEnvironmentVariable("ComSpec"),
                    prePostTaskCommandLine);
            ProcessBuilder pb = new ProcessBuilder(commandLine);
            pb.directory(new File(serviceWorkingDirectory));
            pb.redirectErrorStream();
            TraceHelper.traceInformation(String.format("Executing '%s'", prePostTaskCommandLine));

            try
            {
                Process subp = pb.start();
                subp.getErrorStream();
                InputStream is = subp.getErrorStream();
                InputStreamReader isr = new InputStreamReader(is, "GBK");
                BufferedReader br = new BufferedReader(isr);
               
                //wait the sub process to finish
                exitCode.set(subp.waitFor());
                TraceHelper.traceError(br.toString());                
                TraceHelper.traceInformation(String.format("ExitCode = %d", exitCode));

                if (exitCode.equals(0))
                {
                    TraceHelper.traceInformation(String.format("Error output:%s", br.toString()));
                }

            } catch (InterruptedException e)
            {
                TraceHelper.traceError(String.format("Cannnot start pre/post task: %s, error message: %s", commandLine,
                        e.toString()));
            } catch (IOException e)
            {
                TraceHelper.traceError(String.format("Cannnot start pre/post task: %s, error message: %s", commandLine,
                        e.toString()));
            }

        }
        return prePostTaskExists;
    }
}
