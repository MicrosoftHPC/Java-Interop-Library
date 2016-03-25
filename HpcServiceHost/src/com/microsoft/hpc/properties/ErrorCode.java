// ------------------------------------------------------------------------------
// <copyright file="ErrorCode.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Global Error Code
// </summary>
// ------------------------------------------------------------------------------

package com.microsoft.hpc.properties;

// Summary:
// Defines the possible HPC-defined error codes that HPC can set.
public final class ErrorCode
{

    // Summary:
    // Separator used to separate the insertion strings for the error message
    // text.
    // See Microsoft.Hpc.Scheduler.Properties.SchedulerException.Params.
    public static String ArgumentSeparator = "|||";
    public static int AuthFailureDisableCredentialReuse = 1;
    //
    // Summary:
    // The child job has finished running.
    public static int Execution_ChildJobFinished = -2147218986;
    public static int Execution_FailedByActivationFilter = -2147218966;
    public static int Execution_FailedToOpenStandardError = -2147218973;
    public static int Execution_FailedToOpenStandardInput = -2147218975;
    public static int Execution_FailedToOpenStandardOutput = -2147218974;
    //
    // Summary:
    // The user canceled the job while it was running.
    public static int Execution_JobCanceled = -2147218982;
    //
    // Summary:
    // The job was preempted by a higher priority job. For details, see the
    // Microsoft.Hpc.Scheduler.ISchedulerJob.CanPreempt
    // and the PreemptionType cluster parameter in the Remarks section of
    // Microsoft.Hpc.Scheduler.IScheduler.SetClusterParameter(System.String,System.String).
    public static int Execution_JobPreempted = -2147218978;
    //
    // Summary:
    // The job exceeded its run-time limit. See
    // Microsoft.Hpc.Scheduler.ISchedulerJob.Runtime
    // and Microsoft.Hpc.Scheduler.ISchedulerTask.Runtime.
    public static int Execution_JobRuntimeExpired = -2147218987;
    //
    // Summary:
    // An error occurred on the node.
    public static int Execution_NodeError = -2147218990;
    public static int Execution_NodePrepTaskFailure = -2147218968;
    //
    // Summary:
    // The job or task was scheduled to run on a node that is no longer
    // reachable.
    public static int Execution_NodeUnreachable = -2147218984;
    //
    // Summary:
    // The parent job of this child job has been canceled.
    public static int Execution_ParentJobCanceled = -2147218988;
    //
    // Summary:
    // The process has exited.
    public static int Execution_ProcessDead = -2147218983;
    //
    // Summary:
    // The job failed to start on one or more nodes or the nodes were not
    // reachable.
    public static int Execution_ResourceFailure = -2147218981;
    //
    // Summary:
    // The job failed to start on node {0}.
    public static int Execution_StartJobFailedOnNode = -2147218977;
    public static int Execution_TaskCanceledBeforeAssignment = -2147218971;
    public static int Execution_TaskCanceledDuringExecution = -2147218969;
    public static int Execution_TaskCanceledOnJobRequeue = -2147218970;
    //
    // Summary:
    // The scheduler was not able to execute the command specified in the task.
    public static int Execution_TaskExecutionFailure = -2147218979;
    //
    // Summary:
    // The task failed.
    public static int Execution_TaskFailure = -2147218980;
    public static int Execution_TaskNodeNotUsable = -2147218967;
    //
    // Summary:
    // The task exceeded its run-time limit. See
    // Microsoft.Hpc.Scheduler.ISchedulerTask.Runtime.
    public static int Execution_TaskRuntimeExpired = -2147218985;
    //
    // Summary:
    // The job to which the task belongs was canceled.
    public static int Execution_TasksJobCanceled = -2147218989;
    //
    // Summary:
    // The task was canceled while it was running because a job with higher
    // priority
    // preempted the task. If the task can be rerun, the HPC Job Scheduler
    // service
    // will attempt to automatically queue it again.
    public static int Execution_TasksPreempted = -2147218976;
    //
    // Summary:
    // An unknown error occurred.
    public static int Execution_UnknownError = -2147218991;
    public static int Execution_WorkingDirectoryNotFound = -2147218972;
    //
    // Summary:
    // The value is outside the allowable range of values.
    public static int Operation_ArgumentOutOfRange = -2147220987;
    //
    // Summary:
    // Authenticate failure.
    public static int Operation_AuthenticationFailure = -2147220980;
    public static int Operation_AzureDeploymentNotFound = -2147220691;
    //
    // Summary:
    // The user canceled the job or task.
    public static int Operation_CanceledByUser = -2147220991;
    public static int Operation_CancelMessageIsTooLong = -2147220781;
    public static int Operation_CannotConnectWithScheduler = -2147220785;
    //
    // Summary:
    // You cannot remove item {0} from the default job template.
    public static int Operation_CannotRemoveProfileItemFromDefaultTemplate = -2147220954;
    //
    // Summary:
    // You cannot change the name of the default job template.
    public static int Operation_CannotResetDefaultProfileName = -2147220948;
    //
    // Summary:
    // You cannot submit child job, instead you must submit the parent job.
    public static int Operation_ChildJobCannotBeSubmittedAlone = -2147220966;
    //
    // Summary:
    // Not able to register with the server.
    public static int Operation_CouldNotRegisterWithServer = -2147220932;
    //
    // Summary:
    // Cryptography error.
    public static int Operation_CryptographyError = -2147220979;
    //
    // Summary:
    // A database exception occurred while processing the request.
    public static int Operation_DatabaseException = -2147220988;
    //
    // Summary:
    // The scheduler was unable to create a new job because the database is
    // full.
    public static int Operation_DatabaseIsFull = -2147220926;
    //
    // Summary:
    // Duplicate application found in the software license item.
    public static int Operation_DuplicateLicenseFeature = -2147220946;
    public static int Operation_EmailCredentialMustBeAdmin = -2147220888;
    //
    // Summary:
    // The environment variable name was too long. The size must be smaller than
    // {0} characters.
    public static int Operation_EnvironmentVarNameTooLong = -2147220929;
    //
    // Summary:
    // The environment variable value was too long. The size must be smaller
    // than
    // {0} characters.
    public static int Operation_EnvironmentVarValueTooLong = -2147220928;
    public static int Operation_ExcludedNodeDoesNotExist = -2147220885;
    public static int Operation_ExcludedNodeListTooLong = -2147220782;
    public static int Operation_ExcludedRequiredNode = -2147220887;
    public static int Operation_ExcludedTooManyNodes = -2147220886;
    public static int Operation_ExpandedPriorityNotValidOnServer = -2147220792;
    public static int Operation_FeatureDeprecated = -2147220896;
    public static int Operation_FeatureUnimplemented = -2147220895;
    public static int Operation_HPCSidUnavailable = -2147220784;
    //
    // Summary:
    // The job template property {0} is not a legal job template property.
    public static int Operation_IllegalProfileProperty = -2147220969;
    public static int Operation_IllegalServiceConcludeAttempt = -2147220779;
    public static int Operation_IllegalStateForServiceConclude = -2147220778;
    public static int Operation_IllegalTaskAddedToServiceJob = -2147220909;
    //
    // Summary:
    // You cannot cancel the job in its current state.
    public static int Operation_InvalidCancelJobState = -2147220984;
    //
    // Summary:
    // You cannot cancel the task in its current state.
    public static int Operation_InvalidCancelTaskState = -2147220983;
    public static int Operation_InvalidEmailAddress = -2147220690;
    //
    // Summary:
    // The provided environment variable name is not valid.
    public static int Operation_InvalidEnvironmentVarName = -2147220931;
    //
    // Summary:
    // The provided environment variable value is not valid.
    public static int Operation_InvalidEnvironmentVarValue = -2147220930;
    //
    // Summary:
    // You cannot filter on property '{0}' for this type of object.
    public static int Operation_InvalidFilterProperty = -2147220925;
    //
    // Summary:
    // The job identifier does not identify an existing job in the scheduler.
    public static int Operation_InvalidJobId = -2147220986;
    public static int Operation_InvalidJobStateForNodeExclusion = -2147220884;
    //
    // Summary:
    // Invalid specification for '{0}'.
    public static int Operation_InvalidJobTemplateItemXml = -2147220927;
    //
    // Summary:
    // The node identifier does not identify and existing node in the cluster.
    public static int Operation_InvalidNodeId = -2147220978;
    //
    // Summary:
    // The requested operation is not valid.
    public static int Operation_InvalidOperation = -2147220977;
    //
    // Summary:
    // The job template identifier is not valid.
    public static int Operation_InvalidProfileId = -2147220933;
    public static int Operation_InvalidPropForTaskType = -2147220908;
    //
    // Summary:
    // The row enumeration identifier is not valid.
    public static int Operation_InvalidRowEnumId = -2147220935;
    //
    // Summary:
    // The task identifier does not identify and existing task in the scheduler.
    public static int Operation_InvalidTaskId = -2147220985;
    //
    // Summary:
    // The job already exists on the server.
    public static int Operation_JobAlreadyCreatedOnServer = -2147220941;
    public static int Operation_JobDeletionForbidden = -2147220912;
    public static int Operation_JobHoldInvalidState = -2147220790;
    public static int Operation_JobHoldUntilTooLong = -2147220783;
    public static int Operation_JobInvalidHoldUntil = -2147220789;
    //
    // Summary:
    // Job modification failed: {0}.
    public static int Operation_JobModifyFailed = -2147220960;
    //
    // Summary:
    // The job was not found on the server.
    public static int Operation_JobNotCreatedOnServer = -2147220942;
    public static int Operation_JobProgressOutOfRange = -2147220917;
    //
    // Summary:
    // Unable to revert the job changes.
    public static int Operation_JobRevertModifyFailed = -2147220959;
    public static int Operation_JobTemplateRequiredValueMissing = -2147220878;
    public static int Operation_JobTemplateValueInvalid = -2147220881;
    public static int Operation_JobTemplateValueTooLarge = -2147220879;
    public static int Operation_JobTemplateValueTooSmall = -2147220880;
    public static int Operation_JobVersionMismatch = -2147220894;
    //
    // Summary:
    // You must specify a job template name.
    public static int Operation_MustProvideProfileName = -2147220934;
    public static int Operation_NodeAlreadyExists = -2147220898;
    public static int Operation_NodeIsNotOnAzure = -2147220692;
    //
    // Summary:
    // The user does not have permission to use the job template.
    public static int Operation_NoPermissionToUseProfile = -2147220967;
    //
    // Summary:
    // No storage is currently defined for the property.
    public static int Operation_NoTableDefinedForProperty = -2147220944;
    //
    // Summary:
    // You do not have permission to change this job template.
    public static int Operation_NotAllowedToChangeProfiles = -2147220970;
    //
    // Summary:
    // The user is not allowed to create job templates.
    public static int Operation_NotAllowedToCreateProfiles = -2147220972;
    //
    // Summary:
    // The default job template cannot be deleted.
    public static int Operation_NotAllowedToDeleteDefaultProfile = -2147220968;
    //
    // Summary:
    // The user is not allowed to delete job templates.
    public static int Operation_NotAllowedToDeleteProfiles = -2147220971;
    //
    // Summary:
    // Cannot perform the operation because you are not connected to the server.
    public static int Operation_NotConnectedToServer = -2147220936;
    public static int Operation_ObjectInUse = -2147220882;
    //
    // Summary:
    // The job must be in the configuration state in order to submit the job.
    public static int Operation_OnlyConfigJobsCanBeSubmitted = -2147220990;
    //
    // Summary:
    // You can submit the task only if the task is in the
    // Microsoft.Hpc.Scheduler.Properties.TaskState.Configuring
    // state.
    public static int Operation_OnlyConfiguringTasksCanBeSubmitted = -2147220940;
    //
    // Summary:
    // You can reconfigure only canceled or failed jobs.
    public static int Operation_OnlyFailedCancelledJobsCanBeConfigured = -2147220982;
    public static int Operation_OnlyFailedCancelledTasksCanBeConfigured = -2147220916;
    //
    // Summary:
    // The user does not have permission to perform the operation.
    public static int Operation_PermissionDenied = -2147220981;
    //
    // Summary:
    // You cannot change HPC-defined environment variables.
    public static int Operation_PreservedEnvironmentVariables = -2147220938;
    public static int Operation_PriorityAndExPriCannotBeSetTogether = -2147220791;
    //
    // Summary:
    // The job template item that you are trying to set already exists.
    public static int Operation_ProfileItemAlreadyExists = -2147220975;
    //
    // Summary:
    // The default value for the job template item is not within the allowed
    // value
    // range for the item.
    public static int Operation_ProfileItemDefaultValueInvalid = -2147220952;
    //
    // Summary:
    // The default values specified for the job template item {0} must include
    // all
    // of the required values for that item. Ensure that the default values
    // include
    // all the values in the Required Values list, and then try again.
    public static int Operation_ProfileItemDefaultValueNotIncludeRequiredValue = -2147220953;
    //
    // Summary:
    // The job template item that you are trying to set does not exist.
    public static int Operation_ProfileItemDoesNotExist = -2147220976;
    //
    // Summary:
    // The minimum staticraint for the job template item is greater than its
    // maximum
    // staticraint.
    public static int Operation_ProfileItemMinGreaterThanMax = -2147220950;
    //
    // Summary:
    // You must provide a default value for the job template item.
    public static int Operation_ProfileItemMustProvideDefaultStringValue = -2147220951;
    //
    // Summary:
    // Invalid template: The template item {0}'s default value, {1}, is larger
    // than
    // the default value of the related template item, {2} (default value: {3}).
    // Correct this inconsistency and try again.
    public static int Operation_ProfileItemRangeInconsistent_LeftDefaultGreaterThanRightDefault = -2147220956;
    //
    // Summary:
    // Invalid template: The template item {0}'s maximum value, {1}, is larger
    // than
    // the maximum value of the related template item, {2} (maximum value: {3}).
    // Correct this inconsistency and try again.
    public static int Operation_ProfileItemRangeInconsistent_LeftMaxGreaterThanRightMax = -2147220957;
    //
    // Summary:
    // Invalid template: The template item {0}'s minimum value, {1}, is larger
    // than
    // the minimum value of the related template item, {2} (minimum value: {3}).
    // Correct this inconsistency and try again.
    public static int Operation_ProfileItemRangeInconsistent_LeftMinGreaterThanRightMin = -2147220958;
    //
    // Summary:
    // The value of the job template item cannot be less than zero.
    public static int Operation_ProfileItemRangeInconsistent_ValueLessThanZero = -2147220955;
    //
    // Summary:
    // The data type used to set or access the job template item does not match
    // the data type of the item.
    public static int Operation_ProfileItemTypeInconsistent = -2147220949;
    //
    // Summary:
    // A job template with the specified name already exists.
    public static int Operation_ProfileNameBeenUsed = -2147220964;
    //
    // Summary:
    // The job template does not exist in the scheduler.
    public static int Operation_ProfileNotFound = -2147220965;
    //
    // Summary:
    // The property is read-only and cannot be set.
    public static int Operation_PropertyIsstatic = -2147220989;
    //
    // Summary:
    // The property name already exists.
    public static int Operation_PropertyNameAlreadyExists = -2147220973;
    public static int Operation_PropertyNameCannotBeEmpty = -2147220786;
    public static int Operation_PropertyNotSupportedOnServerVersion = -2147220907;
    //
    // Summary:
    // The value is too large.
    public static int Operation_PropertyValueTooLargeForDatabase = -2147220939;
    public static int Operation_ReconnectTimeout = -2147220780;
    public static int Operation_SchedulerInCleanupMode = -2147220777;
    public static int Operation_SchedulerInRecoverMode = -2147220788;
    public static int Operation_SchedulerPrivilegeRequired = -2147220787;
    public static int Operation_ServerIsBusy = -2147220897;
    //
    // Summary:
    // You cannot change the property when the job is in the current state.
    public static int Operation_SetInvalidPropUnderCertainState = -2147220943;
    public static int Operation_TaskDeletionForbidden = -2147220911;
    //
    // Summary:
    // Failed to modify the task.
    public static int Operation_TaskModifyFailed = -2147220922;
    public static int Operation_TaskNotCreatedOnServer = -2147220914;
    public static int Operation_TaskOfTypeCannotBeReconfigured = -2147220915;
    public static int Operation_TaskTypeAddedInWrongJobState = -2147220910;
    public static int Operation_TaskTypeAndIsParametricIncompatible = -2147220905;
    public static int Operation_TaskTypeNotSupportedOnServer = -2147220924;
    public static int Operation_TooManyParametricSweepTasksPerJob = -2147220906;
    //
    // Summary:
    // You cannot change the property when the node is in the current state.
    public static int Operation_TryToChangePropertyInNodeState = -2147220945;
    //
    // Summary:
    // You cannot modify the job property when the job is in its current state.
    public static int Operation_TryToModifyInvalidProperty = -2147220962;
    //
    // Summary:
    // You cannot modify the job property when the job is in its current state.
    public static int Operation_TryToModifyInvalidStateJob = -2147220961;
    //
    // Summary:
    // The task cannot be modified in its current state.
    public static int Operation_TryToModifyInvalidStateTask = -2147220921;
    //
    // Summary:
    // If the job is a backfill job, you cannot modify the job's runtime value
    // when
    // the job is running.
    public static int Operation_TryToModifyRuntimeForRunningBackfillJob = -2147220963;
    //
    // Summary:
    // The exception was not expected.
    public static int Operation_UnexpectedException = -2147220892;
    //
    // Summary:
    // The specified node group does not exist.
    public static int Operation_UnknownNodeGroup = -2147220937;
    public static int Operation_WindowsTaskSchedulerExecFailure = -2147220890;
    public static int Operation_WindowsTaskSchedulerNotStarted = -2147220889;
    public static int Operation_WindowsTaskSchedulerTimeout = -2147220891;
    //
    // Summary:
    // The software license format is not valid. The valid format is
    // application:
    // numberoflicenses{,application:numberoflicenses.
    public static int Operation_WrongLicenseFormat = -2147220947;
    //
    // Summary:
    // The cluster does not contain a node that is capable of running a broker
    // job.
    public static int ResourceAssignement_FailedToFindBrokerNode = -2147219991;
    //
    // Summary:
    // Failed to run the activation filter specified in the
    // ActivationFilterProgram
    // cluster parameter.
    public static int ResourceAssignement_FailedToLaunchActivationFilter = -2147219990;
    //
    // Summary:
    // There are no nodes available to run the task.
    public static int ResourceAssignement_NodeUnavailable = -2147219989;
    public static int ServiceBroker_ExitCode_End = -2147214992;
    public static int ServiceBroker_ExitCode_Start = -2147215992;
    //
    // Summary:
    // The BackendBinding value specified in the service broker's configuration
    // file is not valid. Correct the binding and start a new session.
    public static int ServiceBroker_InvalidBackendBinding = -2147215986;
    //
    // Summary:
    // The configuration file for the service broker was not valid.
    public static int ServiceBroker_InvalidConfig = -2147215989;
    //
    // Summary:
    // The broker could not find the HPC environment information.
    public static int ServiceBroker_MissingHpcEnvironmentInfomation = -2147215991;
    //
    // Summary:
    // The service name or assembly name was not specified.
    public static int ServiceBroker_MissingServiceInformation = -2147215990;
    //
    // Summary:
    // The broker failed to open the service broker web service.
    public static int ServiceBroker_OpenBrokerServiceFailure = -2147215987;
    //
    // Summary:
    // The broker failed to open the service registration web service.
    public static int ServiceBroker_OpenRegistrationServiceFailure = -2147215988;
    //
    // Summary:
    // An unexpected exception occurred. Please see the broker execution log for
    // details.
    public static int ServiceBroker_UnexpectedError = -2147215972;
    //
    // Summary:
    // Not able to find the service assembly file name in the service
    // registration
    // file. Please ensure that the service registration file has the correct
    // format.
    public static int ServiceHost_AssemblyFileNameNullOrEmpty = -2147216980;
    //
    // Summary:
    // Failed to find the service assembly file. Please ensure that the assembly
    // has been deployed and registered on the node.
    public static int ServiceHost_AssemblyFileNotFound = -2147216991;
    //
    // Summary:
    // Failed to load the service assembly. Please ensure that the security
    // settings
    // for the assembly are correct and that the service assembly information at
    // HKLM\Microsoft\Hpc\ServiceRegistry\<ServiceName>\AssemblyPath) is
    // correct.
    public static int ServiceHost_AssemblyLoadingError = -2147216990;
    //
    // Summary:
    // The service registration file is not in the correct format. Please use
    // the
    // service registration XML schema to ensure that the service registration
    // file
    // is in the correct format.
    public static int ServiceHost_BadServiceRegistrationFileFormat = -2147216979;
    //
    // Summary:
    // Failed to find the service contract interface from the service assembly.
    // Please ensure that the contract information provided in the service
    // registration
    // is valid (see
    // HKLM\Microsoft\Hpc\ServiceRegistry\<ServiceName>\AssemblyPath
    // and Contract type).
    public static int ServiceHost_ContractDiscoverError = -2147216989;
    //
    // Summary:
    // The service registry is corrupt.
    public static int ServiceHost_CorruptServiceRegistry = -2147216983;
    public static int ServiceHost_ExitCode_End = -2147215992;
    public static int ServiceHost_ExitCode_Start = -2147216992;
    //
    // Summary:
    // Failed to register to the broker service.
    public static int ServiceHost_FailedToRegisterLB = -2147216984;
    public static int ServiceHost_IncorrectCommandLineParameter = -2147216978;
    //
    // Summary:
    // Failed to find the contract implemented by the service type.
    public static int ServiceHost_NoContractImplemented = -2147216986;
    public static int ServiceHost_PrintCommandHelp = -2147216976;
    public static int ServiceHost_ServiceConfigFileNameNotSpecified = -2147216977;
    //
    // Summary:
    // Failed to open the service host. Please ensure that the NetTcp port
    // sharing
    // service is running and that the user has permission to use port sharing
    // service,
    // and that the firewall settings are correct.
    public static int ServiceHost_ServiceHostFailedToOpen = -2147216985;
    //
    // Summary:
    // The service name is not specified.
    public static int ServiceHost_ServiceNameNotSpecified = -2147216982;
    //
    // Summary:
    // Cannot locate the service registration file in both
    // %CCP_HOME%ServiceRegistration
    // folder and user specified central registration folder (if any). Please
    // ensure
    // that the service has been successfully deployed.
    public static int ServiceHost_ServiceRegistrationFileNotFound = -2147216981;
    //
    // Summary:
    // Failed to find the service type from the service assembly. Please ensure
    // that the type information in the service registration is valid (see
    // HKLM\Microsoft\Hpc\ServiceRegistry\<ServiceName>\AssemblyPath
    // and ServiceType).
    public static int ServiceHost_ServiceTypeDiscoverError = -2147216988;
    //
    // Summary:
    // Failed to load the service type from the service assembly.
    public static int ServiceHost_ServiceTypeLoadingError = -2147216987;
    //
    // Summary:
    // An unexpected exception occurred. Please see the service host execution
    // log
    // for details.
    public static int ServiceHost_UnexpectedException = -2147216972;
    //
    // Summary:
    // The operation was a success.
    public static int Success = 0;
    //
    // Summary:
    // An unknown error occurred.
    public static int UnknowError = -2147214990;
    public static int UnknownError = -2147214990;
    public static int Validation_AdminJobCannotHaveTaskDependency = -2147219882;
    //
    // Summary:
    // A batch job cannot be a child job.
    public static int Validation_BatchJobMustNotBeChildJob = -2147219904;
    //
    // Summary:
    // Failed to compute the maximum resource requirement for the job.
    public static int Validation_CalcJobMaxError = -2147219921;
    //
    // Summary:
    // Failed to compute the minimum resource requirement for the job.
    public static int Validation_CalcJobMinError = -2147219922;
    //
    // Summary:
    // A child job cannot contain another child job.
    public static int Validation_ChildJobContainsChildJob = -2147219908;
    //
    // Summary:
    // The parent identifier included in the child job is different than its
    // parent's
    // actual identifier.
    public static int Validation_ChildJobIdNotPairWithParentJobId = -2147219909;
    //
    // Summary:
    // The child job must be a router job.
    public static int Validation_ChildJobMustBeRouterJob = -2147219910;
    //
    // Summary:
    // The child job is not valid.
    public static int Validation_ChildJobNotValid = -2147219912;
    //
    // Summary:
    // The cluster does not contain the required minimum number of resources.
    public static int Validation_ClusterSizeLessThanMin = -2147219956;
    public static int Validation_CombineSelectException = -2147219979;
    //
    // Summary:
    // The credentials specified for this job are not able to log onto the
    // cluster.
    public static int Validation_CredentialCheckFailed = -2147219989;
    //
    // Summary:
    // Failed to calculate task group level.
    public static int Validation_FailToCalculateTaskGroupLevel = -2147219930;
    //
    // Summary:
    // The contents of the Microsoft.Hpc.Scheduler.ISchedulerTask.DependsOn
    // property
    // is not valid.
    public static int Validation_InvalidDependsOn = -2147219929;
    public static int Validation_InvalidNodeCriteriaForJob = -2147219886;
    public static int Validation_InvalidPropForTaskType = -2147219894;
    //
    // Summary:
    // Job submission failed because the job submission filter application is
    // not
    // valid. Your cluster administrator should check that the submission filter
    // is an executable binary file (".exe" file) or Windows command script
    // (".cmd"
    // file).
    public static int Validation_InvalidSubmissionFilter = -2147219900;
    //
    // Summary:
    // A node that the job requests does not support the job's job type.
    public static int Validation_JobAskedNodeMustContainJobType = -2147219953;
    //
    // Summary:
    // The job is missing the user name or password.
    public static int Validation_JobMissUsernameOrPassword = -2147219988;
    //
    // Summary:
    // The list of requested nodes does not contain a valid node.
    public static int Validation_JobRequestedNodesContainZeroValidNode = -2147219967;
    //
    // Summary:
    // The job requires a node that is not available.
    public static int Validation_JobRequiredNodeNotAvailable = -2147219954;
    //
    // Summary:
    // The list of nodes that the task requires must be included in the list of
    // the nodes that the job requested.
    public static int Validation_JobRequiredNodeNotInJobAskedNodes = -2147219955;
    //
    // Summary:
    // The maximum number of resource units must be greater than zero.
    public static int Validation_MaxLessThanOne = -2147219933;
    //
    // Summary:
    // The maximum number of resource units cannot be less than zero.
    public static int Validation_MaxLessThanZero = -2147219959;
    //
    // Summary:
    // You must specify the maximum number of resource units that the job
    // requires
    // if the Microsoft.Hpc.Scheduler.ISchedulerJob.AutoCalculateMax property is
    // false.
    public static int Validation_MaxNotSpecifiedWhenAutoCalcMaxIsFalse = -2147219961;
    //
    // Summary:
    // You must specify a maximum resource value for the task.
    public static int Validation_MaxUndefined = -2147219947;
    //
    // Summary:
    // The minimum value must be less than the maximum value.
    public static int Validation_MinGreaterThanMax = -2147219958;
    //
    // Summary:
    // The minimum number of resource units must be greater than zero.
    public static int Validation_MinLessThanOne = -2147219934;
    //
    // Summary:
    // The minimum number of resource units cannot be less than zero.
    public static int Validation_MinLessThanZero = -2147219960;
    //
    // Summary:
    // You must specify the minimum number of resource units that the job
    // requires
    // if the Microsoft.Hpc.Scheduler.ISchedulerJob.AutoCalculateMin property is
    // false.
    public static int Validation_MinNotSpecifiedWhenAutoCalcMinIsFalse = -2147219962;
    //
    // Summary:
    // You must specify a minimum resource value for the task.
    public static int Validation_MinUndefined = -2147219948;
    //
    // Summary:
    // The task must specify a command to run.
    public static int Validation_MissCommandLine = -2147219952;
    public static int Validation_MultipleNodePrepTasksPerJob = -2147219897;
    public static int Validation_MultipleNodeReleaseTasksPerJob = -2147219896;
    //
    // Summary:
    // The computed resources for the job is less than the required number of
    // resources.
    public static int Validation_NodeListSizeLessThanMin = -2147219957;
    //
    // Summary:
    // The node does not exist in the cluster.
    public static int Validation_NodeNotExist = -2147219965;
    //
    // Summary:
    // The cluster does not contain a node that supports the specified resource
    // requirements for the job (for example, memory or core requirements).
    // Please
    // adjust your requirements and submit the job again.
    public static int Validation_NoNodeFulfillsTheSelectionCriteria = -2147219966;
    public static int Validation_OnlyNodePrepOrReleaseTasksInJob = -2147219895;
    //
    // Summary:
    // The parent job must be a service job.
    public static int Validation_ParentJobMustBeServiceJob = -2147219911;
    //
    // Summary:
    // The parent job is not valid.
    public static int Validation_ParentJobNotValid = -2147219907;
    //
    // Summary:
    // The job template does not exist.
    public static int Validation_ProfileNotExist = -2147219977;
    //
    // Summary:
    // The property value is not within the allowed range of values as specified
    // by the job template.
    public static int Validation_PropertyOutOfRange = -2147219978;
    //
    // Summary:
    // A property that requires a value as specified by the job template has not
    // been set.
    public static int Validation_RequiredPropertyNotSet = -2147219981;
    //
    // Summary:
    // A router job cannot be a child job.
    public static int Validation_RouterJobMustBeChildJob = -2147219906;
    //
    // Summary:
    // To reserve resources for a job (when the job does not contain tasks and
    // has
    // requested that it run until canceled), you must specify the maximum and
    // minimum
    // resource values for the job -you cannot request that the scheduler
    // compute
    // the maximum and minimum resource values.
    public static int Validation_RunUntilCanceledButAutoMinMaxSet = -2147219963;
    //
    // Summary:
    // To reserve resources for a job (when the job does not contain tasks and
    // has
    // requested that it run until canceled), you must specify the maximum and
    // minimum
    // resource values for the job.
    public static int Validation_RunUntilCanceledButMinMaxNotSpecified = -2147219964;
    //
    // Summary:
    // A service job cannot be a child job.
    public static int Validation_ServiceJobMustNotBeChildJob = -2147219905;
    public static int Validation_ServiceTaskIsNotAlone = -2147219887;
    //
    // Summary:
    // Job submission failed because the job submission filter application could
    // not be found. Your cluster administrator should check that the submission
    // filter is accessible from the head node of the cluster and the path to
    // the
    // submission filter is a fully-qualified (not relative) path name.
    public static int Validation_SubmissionFilterDoesNotExist = -2147219901;
    //
    // Summary:
    // The job failed to pass the job submission filter specified in the
    // SubmissionFilterProgram
    // cluster parameter.
    public static int Validation_SubmissionFilterFailed = -2147219902;
    public static int Validation_SubmissionFilterInvalidJobTerm = -2147219883;
    public static int Validation_SubmissionFilterTimeout = -2147219884;
    public static int Validation_TargetResourceCountLessThanMin = -2147219888;
    public static int Validation_TargetResourceCountMoreThanMax = -2147219889;
    //
    // Summary:
    // The task must specify the same resource unit as the job.
    public static int Validation_TaskAllocUnitNotSameWithJob = -2147219949;
    //
    // Summary:
    // The dependency list for multiple tasks creates a circular dependency.
    public static int Validation_TaskDependenciesContainCycle = -2147219931;
    //
    // Summary:
    // The minimum and maximum resource requirements cannot be computed for a
    // job
    // with exclusive access to the nodes. You must specify the minimum and
    // maximum
    // resource values and resubmit the job.
    public static int Validation_TaskExclusiveWhileJobAutoMinMaxEnabled = -2147219938;
    //
    // Summary:
    // The task can run exclusively on a node only if the job specifies that it
    // must run exclusively on the node.
    public static int Validation_TaskExclusiveWhileJobNot = -2147219943;
    //
    // Summary:
    // The task failed because the scheduler could not validate its parent job.
    // Correct the validation error on the parent job and resubmit the job.
    public static int Validation_TaskFailedOnJobValidationFault = -2147219936;
    //
    // Summary:
    // The task specifies a list of dependent tasks but the task does not
    // specify
    // a name value for itself; each dependent task and the task specifying the
    // dependency must include a name value.
    public static int Validation_TaskHasDependOnButNoName = -2147219928;
    //
    // Summary:
    // The task cannot specify required nodes when the job has requested that
    // the
    // scheduler compute the required resources.
    public static int Validation_TaskHasRequiredNodesWhileJobAutoMinMaxEnabled = -2147219937;
    //
    // Summary:
    // The increment value for a parametric task must be greater than or equal
    // to
    // 1.
    public static int Validation_TaskIncrementValueLessThanZero = -2147219899;
    //
    // Summary:
    // The start value for a parametric task cannot be greater than end value.
    public static int Validation_TaskInvalidParametricSweep = -2147219898;
    //
    // Summary:
    // The maximum resource value for the task must be less than that of the
    // job.
    public static int Validation_TaskMaxGreaterThanJobMax = -2147219945;
    //
    // Summary:
    // The minimum resource value for the task must be less than that of the
    // job.
    public static int Validation_TaskMinGreaterThanJobMin = -2147219946;
    //
    // Summary:
    // The minimum resource value for the task must be less than its maximum The
    // minimum resource value for the task must be less than that of the job
    // value.
    public static int Validation_TaskMinGreaterThanTaskMax = -2147219944;
    public static int Validation_TaskNodeReleaseDisabled = -2147219893;
    public static int Validation_TaskNodeReleaseExceedsMaxRunTime = -2147219892;
    //
    // Summary:
    // You have exceeded the number of times that a task can be queued again.
    // The
    // TaskRetryCount cluster parameter specifies the maximum number of times
    // that
    // a task can be queued again.
    public static int Validation_TaskRequeuedTooManyTimes = -2147219951;
    public static int Validation_TaskRequiredNodeNotAllocatedToRunningJob = -2147219885;
    //
    // Summary:
    // The job requires a node that is not available.
    public static int Validation_TaskRequiredNodeNotAvailable = -2147219940;
    //
    // Summary:
    // The list of nodes that the task requires must be included in the list of
    // the nodes that the job requested.
    public static int Validation_TaskRequiredNodeNotInJobAskedNodes = -2147219942;
    //
    // Summary:
    // The task specifies more required nodes than the job's specified maximum
    // resource
    // usage.
    public static int Validation_TaskRequiredNodeOutOfJobMaximumResource = -2147219939;
    //
    // Summary:
    // The run-time value for the task is greater than the length of time that
    // the
    // job is scheduled to remain running.
    public static int Validation_TaskRuntimeGreaterThanJob = -2147219941;
    public static int Validation_TaskTypeAddedInWrongJobState = -2147219891;
    //
    // Summary:
    // The task failed validation.
    public static int Validation_TaskValidationFailed = -2147219935;
    public static int Validation_TooManyParametricInstances = -2147219890;
    //
    // Summary:
    // You cannot queue the task again because the task is marked to run only
    // one
    // time.
    public static int Validation_TryToRequeueNonRerunnableTask = -2147219950;
    //
    // Summary:
    // The depends on list is ambiguous because multiple tasks specify the same
    // name.
    public static int Validation_TwoTasksWithSameNameDifferentGroup = -2147219932;
    //
    // Summary:
    // An unexpected exception occurred while validating the job.
    public static int Validation_UnexpectedExceptionWhenValidating = -2147219991;
    public static int Validation_Unknown = -2147219990;
    //
    // Summary:
    // An unexpected exception occurred while calling ETW.
    public static int ERROR_INVALIDFLAG         = -2147220001;
    public static int ERROR_NULLMSG             = -2147220002;
    public static int ERROR_INVALIDMESSAGEID    = -2147220003;
    public static int ERROR_INVALIDDISPATCHID   = -2147220004;
    public static int ERROR_DLLNOTEXIST         = -2147220005;
    public static int ERROR_REGISTRATIONFAILED  = -2147220006;
    public static int ERROR_WRITEEVENTFAILED    = -2147220007;

    // Summary:
    // Defines the category of errors into which the
    // Microsoft.Hpc.Scheduler.Properties.ErrorCode
    // codes are grouped.
    public enum Category
    {
        // Summary:
        // An error occurred while performing an operation (for example, the
        // user tried
        // to delete a job template but they do not have permissions to delete
        // templates).
        // This enumeration member represents a value of 0.
        OperationError,
        //
        // Summary:
        // The error occurred while validating the job or task before it ran.
        // This enumeration
        // member represents a value of 1.
        ValidationError,
        //
        // Summary:
        // The error occurred while assigning resources to the job or task. This
        // enumeration
        // member represents a value of 2.
        ResourceAssignmentError,
        //
        // Summary:
        // The error occurred while executing the job or task. This enumeration
        // member
        // represents a value of 3.
        ExecutionError,
        //
        // Summary:
        // Includes errors related to starting service or broker jobs. This
        // enumeration
        // member represents a value of 4.
        Other,
    }

}
