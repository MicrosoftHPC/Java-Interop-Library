//------------------------------------------------------------------------------
// <copyright file="Constant.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Global constants
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

/**
 * The Constant variables
 */
public class Constant
{
    /**
     * SOAP Action of broker's EOM message
     */
    final public static String EndRequestsAction = "http://hpc.microsoft.com/EndOfGetResponse";

    /**
     * Element name of user data SOAP header
     */
    final public static String UserDataHeaderName = "HPCServer2008_Broker_UserDataNS";

    /**
     * Element name of user data SOAP header
     */
    final public static String ClientIdHeaderName = "HPCServer2008_Broker_ClientIdNS";

    /**
     * Element name of request action SOAP Header
     */
    final public static String ActionHeaderName = "HPCServer2008_Broker_RequestActionNS";

    /**
     * Namespace of user data SOAP header
     */
    final public static String HpcHeaderNS = "http://www.microsoft.com/hpc";

    /**
     * Element name of client callback ID header
     */
    final public static String ResponseCallbackIdHeaderName = "HPCServer2008_Broker_ResponseCallbackId";

    /**
     * Namespace name of client callback ID header
     */
    final public static String ResponseCallbackIdHeaderNS = HpcHeaderNS;

    /**
     * Maximum length of user data SOAP header
     */
    final public static int MaxUserDataLen = 1024;

    /**
     * Maximum size of message TODO: Need to pull from current binding
     */
    final public static int MaxBufferSize = 200 * 1024;

    /**
     * Default EOM timeout is 60 sec
     */
    final public static int EOMTimeoutMS = 60 * 1000;

    /**
     * Default Purge timeout is 10 sec
     */
    final public static int PurgeTimeoutMS = 10 * 1000;

    /**
     * Min WCF operation timeout
     */
    final public static int MinOperationTimeout = 60 * 1000;

    /**
     * Request all responses
     */
    final public static int GetResponse_All = -1;

    /**
     * Stores session launcher port
     */
    final public static int SessionLauncherPort = 9090;

    /**
     * The environment variable for data server location
     */

    final public static String DataServerEnv = "HPC_SOADATASERVER";

    /**
     * @field Namespace of Action SOAP header
     **/
    final public static String HpcActionHeaderNS = "http://www.w3.org/2005/08/addressing";

    /**
     * @field Namespace of XMLSchema-instance
     **/
    final public static String XMLSchemaInstanceNS = "http://www.w3.org/2001/XMLSchema-instance";

    /**
     * @field Environment Variable to pass service initialization timeout
     **/
    final public static String ServiceInitializationTimeoutEnvVar = "CCP_SERVICE_INITIALIZATION_TIMEOUT";

    /**
     * @field Environment Variable to pass cancel task grace period
     **/
    final public static String CancelTaskGracePeriodEnvVar = "CCP_CANCEL_TASK_GRACEPERIOD";

    /**
     * @field Folder Name for saving the necessary data
     **/
    final public static String CCP_DATAEnvVar = "CCP_DATA";

    /**
     * File name for the service config file
     **/
    final public static String ServiceConfigFileNameEnvVar = "CCP_SERVICE_CONFIG_FILENAME";

    /**
     * @field Environment Variable to pass the value indicating enable
     *        MessageLevelPreemption or not.
     **/
    final public static String EnableMessageLevelPreemptionEnvVar = "CCP_MESSAGE_LEVEL_PREEMPTION";

    /**
     * @fieldEnvironment Variable to pass the localtion of the process
     **/
    final public static String TASKINSTANCEIDEnvVar = "CCP_TASKINSTANCEID";

    /**
     * @fieldDefault value for cancel task grace period. Same as node managers
     **/
    final public static int DefaultCancelTaskGracePeriod = 5000;

    /**
     * @field Name of the ServiceJob property
     **/
    final public static String ServiceJobId = "HPC_ServiceJobId";

    /**
     * @field Name of the head node
     **/
    final public static String HeadnodeName = "HPC_Headnode";

    /**
     * @field Store cluster name environment
     **/
    final public static String HeadnodeEnvVar = "CCP_CLUSTER_NAME";

    /**
     * @field Env var to pass serviceOperationTimeout to service hosts
     **/
    final public static String ServiceConfigServiceOperatonTimeoutEnvVar = "CCP_SERVICE_SERVICEOPERATIONTIMEOUT";

    /**
     * @field Default value for serviceOperationTimeout service config setting
     **/
    final public static int DefaultServiceOperationTimeout = 24 * 60 * 60 * 1000;

    /**
     * @field Store the service host port
     **/
    final public static int ServiceHostPort = 9100;

    /**
     * @field Store the service host port for IHpcServiceHost
     **/
    final public static int ServiceHostControllerPort = 9200;

    /**
     * @field Stores the network prefix env to get the network prefix
     **/
    final public static String NetworkPrefixEnv = "WCF_NETWORKPREFIX";

    /**
     * @field Stores the enterprise network prefix
     **/
    final public static String EnterpriseNetwork = "Enterprise";

    /**
     * @field Store registry path environment
     **/
    final public static String RegistryPathEnv = "CCP_SERVICEREGISTRATION_PATH";

    /**
     * @field Store service name environment
     **/
    final public static String ServiceNameEnvVar = "CCP_SERVICENAME";

    /**
     * @field Store job id environment
     **/
    final public static String JobIDEnvVar = "CCP_JOBID";

    /**
     * @field Store task system id environment
     **/
    final public static String TaskSystemIDEnvVar = "CCP_TASKSYSTEMID";
    
    /**
     * @field Store task id environment
     **/
    final public static String TaskIDEnvVar = "CCP_TASKID";

    /**
     * @field Store processor number environment
     **/
    final public static String ProcNumEnvVar = "CCP_NUMCPUS";

    /**
     * @field Store core id list environment
     **/
    final public static String CoreIdsEnvVar = "CCP_COREIDS";

    /**
     * @field Env var used to store pre/post command for HpcServiceHost to use
     *        when launching the command
     **/
    final public static String PrePostTaskCommandLineEnvVar = "CCP_PREPOST_COMMANDLINE";

    /**
     * @field Env var used to store pre/post working dir for HpcServiceHost to
     *        use when launching the command on premise
     **/
    final public static String PrePostTaskOnPremiseWorkingDirEnvVar = "CCP_PREPOST_ONPREMISE_WORKINGDIR";

    /**
     * @field Env var used to pass soa data server information to service hosts
     **/
    final public static String SoaDataServerInfoEnvVar = "HPC_SOADATASERVER";

    /**
     * @field Message header name of Preemption The service host adds the header
     *        to the response message when the process is preempted, and the
     *        broker reads it. The value type is integer.
     **/
    final public static String MessageHeaderPreemption = "preemption";
    
    /**
     * @field DispatchID LocalPart in Header
     **/
    final public static String DISPATCHIDLOCALPART = "HPCServer2008_Broker_DispatchIdNS";
    
    /**
     * @field MessageID LocalPart in Header
     **/
    final public static String MESSAGEIDLOCALPART = "MessageID";
 
    /**
     * @field The 5 trace level used to call .dll when using etw
     **/
    final public static int TRACE_CRITICAL  = 5;
    final public static int TRACE_ERROR     = 4;
    final public static int TRACE_WARNING   = 3;
    final public static int TRACE_INFO      = 2;
    final public static int TRACE_VERBOSE   = 1;
 
    /**
     * @field ETW trace format
     */
    final public static String ETWTraceFormat = "<E2ETraceEvent xmlns=\"http://schemas.microsoft.com/2004/06/E2ETraceEvent\">\n"
        + "<System xmlns=\"http://schemas.microsoft.com/2004/06/windows/eventlog/system\">\n"
        + "<EventID>0</EventID>\n" 
        + "<Type>3</Type>\n"
        +"<SubType Name=\"%s\">0</SubType>\n"
        + "<Level>%s</Level>\n"
        + "<TimeCreated SystemTime=\"%s\"/>"
        + "<Source Name=\"Microsoft.Hpc.javaServiceHosting\" />\n"
        + "<Correlation ActivityID=\"{00000000-0000-0000-0000-000000000000}\" />\n"
        + "<Execution ProcessName=\"HpcJavaServiceHost\" "
        + "ProcessID=\"-1\" ThreadID=\"0\" />"
        + "<Channel/>\n"
        + "<Computer>%s</Computer>\n"
        + "</System>\n"
        + "<ApplicationData>"  
        // CDATA sections are designed to take the trauma out of
        // escaping text that has lots of characters that need escaping
        // (like < and &)
        + "<![CDATA[%s]]>"
        + "</ApplicationData>\n" + "</E2ETraceEvent>\n";
}
