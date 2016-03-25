//------------------------------------------------------------------------------
// <copyright file="SessionStartInfo.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      SessionStart Info class
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
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.microsoft.hpc.SessionStartInfoContract;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfstringstring;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfstringstring.KeyValueOfstringstring;
import static java.lang.invoke.MethodHandles.constant;

/**
 * This class includes the parameters for modifying
 * <code>SessionStartInfo</code> for a session.
 *
 * <p>
 * Subclass of <code>com.microsoft.hpc.SessionStartInfo</code> designed to be a
 * simplified version that does not use ObjectFactories for the JAXBElements
 * needed for Java SOA.
 * </p>
 *
 * the use of this class is required as it contains the headnode variable, while
 * the <code>SessionStartInfo</code> in
 * <code>com.microsoft.hpc.SessionStartInfo</code> does not and should not be
 * used (ie. <code>import com.microsoft.hpc.SessionStartInfo;</code> should
 * <b><i>NEVER</i></b> be called explicitly).</p>
 *
 * @see com.microsoft.hpc.SessionStartInfo
 * @see com.microsoft.hpc.ObjectFactory
 * @see JAXBElement
 */
public class SessionStartInfo {

    private static com.microsoft.hpc.ObjectFactory factory = new com.microsoft.hpc.ObjectFactory();
    private com.microsoft.hpc.SessionStartInfoContract contract = factory
            .createSessionStartInfoContract();

    private String headnode;
    private String username;
    private String password;

    /**
     * default constructor to set the default values
     */
    SessionStartInfo() {
        contract.setRuntime(-1);
        contract.setShareSession(false);
        contract.setSecure(true);
        contract.getTransportScheme().add("Http");

        org.datacontract.schemas._2004._07.system.Version ver = new org.datacontract.schemas._2004._07.system.Version();
        ver.setMajor(SessionBase.ClientVersion.getMajor());
        ver.setMinor(SessionBase.ClientVersion.getMinor());
        ver.setBuild(SessionBase.ClientVersion.getBuild());
        ver.setRevision(SessionBase.ClientVersion.getRevision());
        contract.setClientVersion(factory
                .createSessionStartInfoContractClientVersion(ver));
    }

    /**
     * Initializes an instance of the SessionStartInfo class with a parameter
     * for headnode, service, username, and password.
     *
     * <p>
     * SessionStartInfo is initialized with the following default values:
     * </p>
     * <p>
     * TransportScheme = [Http];
     * </p>
     * <p>
     * Runtime = 3600; (seconds, == 1 hr)
     * </p>
     * <p>
     * ShareSession = false;
     * </p>
     *
     * @param headnode headnode you wish to connect to
     * @param service service name you wish to use
     * @param username username
     * @param password password
     */
    public SessionStartInfo(String headnode, String service, String username,
            String password) {
        this();

        this.setHeadnode(headnode);

        // specify the default value.
        contract.setServiceName(factory
                .createSessionStartInfoContractServiceName(service));

        Utility.throwIfNullOrEmpty(username, "username");
        Utility.throwIfNull(password, "password");

        this.username = username;
        this.password = password;

        setRunAsUsername(this.username);
        setRunAsPassword(this.password);
    }

    /**
     * Initializes an instance of the SessionStartInfo class with a parameter
     * for headnode, service
     *
     * <p>
     * SessionStartInfo is initialized with the following default values:
     * </p>
     * <p>
     * TransportScheme = [Http];
     * </p>
     * <p>
     * Runtime = 3600; (seconds, == 1 hr)
     * </p>
     * <p>
     * ShareSession = false;
     * </p>
     * <p>
     * Username = HpcJava.user
     * </p>
     * <p>
     * Password = HpcJava.pass
     * </p>
     *
     * @param headnode headnode you wish to connect to
     * @param service service name you wish to use
     * @see HpcJava#setUsernamePassword(String, String)
     */
    public SessionStartInfo(String headnode, String service) {
        this(headnode, service, HpcJava.getUsername(), HpcJava.getPassword());
    }

    /**
     * Initializes an instance of the SessionStartInfo class with a parameter
     * for headnode, service
     *
     * @param headnode
     * @param service
     * @param serviceVersion
     */
    public SessionStartInfo(String headnode, String service,
            Version serviceVersion) {
        this(headnode, service, serviceVersion, HpcJava.getUsername(), HpcJava
                .getPassword());
    }

    /**
     * Initializes an instance of the SessionStartInfo class with a parameter
     * for headnode, service
     *
     * <p>
     * SessionStartInfo is initialized with the following default values:
     * </p>
     * <p>
     * TransportScheme = [Http];
     * </p>
     * <p>
     * Runtime = 3600; (seconds, == 1 hr)
     * </p>
     * <p>
     * ShareSession = false;
     * </p>
     * <p>
     * Username = HpcJava.user
     * </p>
     * <p>
     * Password = HpcJava.pass
     * </p>
     *
     * @param headnode headnode you wish to connect to
     * @param service service name you wish to use
     * @param serviceVersion the service version you wish to use
     * @see HpcJava#setUsernamePassword(String, String)
     */
    public SessionStartInfo(String headnode, String service,
            Version serviceVersion, String username, String password) {
        this();

        this.setHeadnode(headnode);

        contract.setServiceName(factory
                .createSessionStartInfoContractServiceName(service));

        org.datacontract.schemas._2004._07.system.Version ver = new org.datacontract.schemas._2004._07.system.Version();

        ver.setMajor(serviceVersion.getMajor());
        ver.setMinor(serviceVersion.getMinor());
        ver.setBuild(serviceVersion.getBuild());
        ver.setRevision(serviceVersion.getRevision());

        contract.setServiceVersion(factory
                .createSessionStartInfoContractServiceVersion(ver));

        Utility.throwIfNullOrEmpty(username, "username");
        Utility.throwIfNull(password, "password");

        this.username = username;
        this.password = password;
        setRunAsUsername(this.username);
        setRunAsPassword(this.password);
    }

    /**
     * Gets the headnode value
     *
     * @return headnode value
     */
    public String getHeadnode() {
        return headnode;
    }

    /**
     * Gets the Job Template
     *
     * @return JobTemplate value
     */
    public String getJobTemplate() {
        if (contract.getJobTemplate() == null) {
            return "Default";
        }
        return contract.getJobTemplate().getValue();
    }

    /**
     * Gets the number of maximum units (cores) to allocate
     *
     * @return maximumUnits value
     */
    public Integer getMaxUnits() {
        if (contract.getMaxUnits() == null) {
            return 0;
        }
        return contract.getMaxUnits().getValue();
    }

    /**
     * Gets the number of minimum units (cores) to allocate
     *
     * @return minUnits value
     */
    public Integer getMinUnits() {
        if (contract.getMinUnits() == null) {
            return 0;
        }
        return contract.getMinUnits().getValue();
    }

    /**
     * Gets the node group string
     *
     * @return NodeGroupsStr value
     */
    public String getNodeGroups() {
        if (contract.getNodeGroupsStr() == null) {
            return null;
        }
        return contract.getNodeGroupsStr().getValue();
    }

    /**
     * Gets the plain text password value to be passed over SSL
     *
     * @return plain text password value
     */
    String getPassword() {
        return this.password;
    }

    /**
     * Gets the priority of the job
     *
     * @return priority value
     */
    public Integer getPriority() {
        if (contract.getPriority() == null) {
            return 0;
        }
        return contract.getPriority().getValue();
    }

    /**
     * Gets the session priority of the job
     *
     * @return priority value
     */
    public Integer getSessionPriority() {
        if (contract.getExtendedPriority() == null) {
            return 0;
        }
        return contract.getExtendedPriority().getValue();
    }

    /**
     * gets the requested nodes string
     *
     * @return RequestedNodesStr value
     */
    public String getRequestedNodesStr() {
        if (contract.getRequestedNodesStr() == null) {
            return null;
        }
        return contract.getRequestedNodesStr().getValue();
    }

    /**
     * Gets the maximum runtime length in seconds
     *
     * @return Runtime value in seconds
     */
    public Integer getRuntime() {
        int time = contract.getRuntime();
        return time == -1 ? 0 : time;
    }

    /**
     * Gets the Service Job Name (name of the job is different from service)
     *
     * @return ServiceJobName value
     */
    public String getServiceJobName() {
        if (contract.getServiceJobName() == null) {
            return null;
        }
        return contract.getServiceJobName().getValue();
    }

    /**
     * Gets the Service name
     *
     * @return ServiceName value
     */
    public String getServiceName() {
        return contract.getServiceName().getValue();
    }

    /**
     * Gets the transport Scheme
     *
     * @return a list of TranportSchemes
     */
    public List<String> getTransportScheme() {
        return contract.getTransportScheme();
    }

    public void setTransportScheme(String transportScheme) {
        contract.getTransportScheme().clear();
        contract.getTransportScheme().add(transportScheme);
    }

    /**
     * Gets the username
     *
     * @return username value
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the headnode to connect to
     *
     * @param headnode headnode to connect
     */
    void setHeadnode(String headnode) {
        this.headnode = headnode;
    }

    /**
     * Sets the job template
     *
     * @param template Job template to use
     */
    public void setJobTemplate(String template) {
        contract.setJobTemplate(factory
                .createSessionStartInfoContractJobTemplate(template));
    }

    /**
     * Sets the maximum units (cores) to allocate
     *
     * @param maximumUnits minimum number of cores to allocate
     */
    public void setMaxUnits(Integer maximumUnits) {
        contract.setMaxUnits(factory
                .createSessionStartInfoContractMaxUnits(maximumUnits));
    }

    /**
     * Sets the minimum units (cores) to allocate
     *
     * @param MinUnits minimum number of cores to allocate
     */
    public void setMinUnits(Integer minUnits) {
        contract.setMinUnits(factory
                .createSessionStartInfoContractMinUnits(minUnits));
    }

    /**
     * Sets the node groups to use
     *
     * @param nodeGroups node groups string
     */
    public void setNodeGroupsStr(String nodeGroups) {
        contract.setNodeGroupsStr(factory
                .createSessionStartInfoContractNodeGroupsStr(nodeGroups));
    }

    /**
     * Sets the password in plain text to be sent over SSL
     *
     * @param password password in plain text
     */
    public void setRunAsPassword(String password) {
        contract.setPassword(factory
                .createSessionStartInfoContractPassword(password));
    }

    /**
     * Sets the priority of the job
     *
     * @param priority Priority value
     */
    public void setPriority(Integer priority) {
        contract.setPriority(factory
                .createSessionStartInfoContractPriority(priority));
    }

    /**
     * Sets the session priority of the job
     *
     * @param priority Priority value
     */
    public void setSessionPriority(Integer priority) {
        contract.setExtendedPriority(factory
                .createSessionStartInfoContractExtendedPriority(priority));
    }

    /**
     * Sets the project value
     *
     * @param project Project value
     */
    public void setServiceJobProject(String project) {
        contract.setServiceJobProject(factory
                .createSessionStartInfoContractServiceJobProject(project));
    }

    /**
     * Get service job project property
     *
     * @return
     */
    public String getServiceJobProject() {
        if (contract.getServiceJobProject() != null) {
            return contract.getServiceJobProject().getValue();
        } else {
            return null;
        }
    }

    /**
     * Sets the requested nodes string
     *
     * @param nodes requestedNodes string
     */
    public void setRequestedNodesStr(String nodes) {
        contract.setRequestedNodesStr(factory
                .createSessionStartInfoContractRequestedNodesStr(nodes));
    }

    /**
     * Sets the resourceUnit type
     *
     * @param unit JobUnitType enum
     */
    public void setResourceUnitType(SessionUnitType unit) {
        contract.setResourceUnitType(factory
                .createSessionStartInfoContractResourceUnitType(unit.ordinal()));
    }

    /**
     * Sets the job runtime in seconds
     *
     * @param runtime in seconds
     */
    public void setRuntime(Integer runtime) {
        if (runtime == 0) {
            contract.setRuntime(-1);
        }
        contract.setRuntime(runtime);
    }

    /**
     * Sets the jobname
     *
     * @param jobname name of the job
     */
    public void setServiceJobName(String jobname) {
        contract.setServiceJobName(factory
                .createSessionStartInfoContractServiceJobName(jobname));
    }

    /**
     * Sets the service name to use
     *
     * @param servicename name of the service
     */
    public void setServiceName(String servicename) {
        contract.setServiceName(factory
                .createSessionStartInfoContractServiceName(servicename));
    }

    /**
     * Sets the username to Run the job
     *
     * @param username username
     */
    public void setRunAsUsername(String username) {
        contract.setUsername(factory
                .createSessionStartInfoContractUsername(username));
    }

    /**
     * Get the runas user name. if it doesn't set before, the username will be
     * returned.
     *
     * @return
     */
    public String getRunAsUsername() {
        return contract.getUsername().getValue();
    }

    /**
     * Returns the allocation grow load ratio threshold
     *
     * @return <code>AllocationGrowLoadRatioThreshold</code> value
     * @see #allocationShrinkLoadRatioThreshold()
     */
    public int getAllocationGrowLoadRatioThreshold() {
        if (contract.getAllocationGrowLoadRatioThreshold() == null) {
            return 0;
        }
        return contract.getAllocationGrowLoadRatioThreshold().getValue();
    }

    /**
     * Returns the allocation shrink load ratio threshold
     *
     * @return <code>AllocationShrinkLoadRatioThreshold</code> value
     */
    public int getAllocationShrinkLoadRatioThreshold() {
        if (contract.getAllocationShrinkLoadRatioThreshold() == null) {
            return 0;
        }
        return contract.getAllocationShrinkLoadRatioThreshold().getValue();
    }

    /**
     * Returns the client idle timeout
     *
     * @return <code>ClientIdleTimeout</code> value
     */
    public int clientIdleTimeout() {
        if (contract.getClientIdleTimeout() == null) {
            return 0;
        }
        return contract.getClientIdleTimeout().getValue();
    }

    /**
     * Returns messages throttle start threshold
     *
     * @return <code>MessagesThrottleStartThreshold</code> value
     */
    public int messagesThrottleStartThreshold() {
        if (contract.getMessagesThrottleStartThreshold() == null) {
            return 0;
        }
        return contract.getMessagesThrottleStartThreshold().getValue();
    }

    /**
     * Returns the messages throttle stop threshold
     *
     * @return <code>MessagesThrottleStopThreshold</code> value
     */
    public int messagesThrottleStopThreshold() {
        if (contract.getMessagesThrottleStopThreshold() == null) {
            return 0;
        }
        return contract.getMessagesThrottleStopThreshold().getValue();
    }

    /**
     * Returns the session idle timeout
     *
     * @return <code>SessionIdleTimeout</code> value
     */
    public int sessionIdleTimeout() {
        if (contract.getSessionIdleTimeout() == null) {
            return 0;
        }
        return contract.getSessionIdleTimeout().getValue();
    }

    /**
     * Sets the allocation grow load ratio threshold
     *
     * @param value This value must be greater than or equal to
     * <code>AllocationShrinkLoadRatioThreshold</code>
     * @see #allocationShrinkLoadRatioThreshold()
     */
    public void setAllocationGrowLoadRatioThreshold(int value) {
        contract.setAllocationGrowLoadRatioThreshold(factory
                .createSessionStartInfoContractAllocationGrowLoadRatioThreshold(value));
    }

    /**
     * Sets the allocation shrink load ratio threshold
     *
     * @param value This value must be less than or equal to
     * <code>AllocationGrowLoadRatioThreshold</code>
     * @see #allocationGrowLoadRatioThreshold()
     */
    public void setAllocationShrinkLoadRatioThreshold(int value) {
        contract.setAllocationShrinkLoadRatioThreshold(factory
                .createSessionStartInfoContractAllocationShrinkLoadRatioThreshold(value));
    }

    /**
     * Sets the client idle timeout
     *
     * @param value The value must be greater than zero
     */
    public void setClientIdleTimeout(int value) {
        contract.setClientIdleTimeout(factory
                .createSessionStartInfoContractClientIdleTimeout(value));
    }

    /**
     * Sets the messages throttle start threshold
     *
     * @param value The value must be less than or greater than
     * <code>MessagesThrottleStopThreshold</code>
     * @see #messagesThrottleStopThreshold()
     */
    public void setMessagesThrottleStartThreshold(int value) {
        contract.setMessagesThrottleStartThreshold(factory
                .createSessionStartInfoContractMessagesThrottleStartThreshold(value));
    }

    /**
     * Sets the messages throttle stop threshold
     *
     * @param value The value must be greater than or less than
     * <code>MessagesThrottleStartThreshold</code>
     * @see #messagesThrottleStartThreshold()
     */
    public void setMessagesThrottleStopThreshold(int value) {
        contract.setMessagesThrottleStopThreshold(factory
                .createSessionStartInfoContractMessagesThrottleStopThreshold(value));
    }

    /**
     * Sets the session idle timeout
     *
     * @param value This value must greater than zero
     */
    public void setSessionIdleTimeout(int value) {
        contract.setSessionIdleTimeout(factory
                .createSessionStartInfoContractSessionIdleTimeout(value));
    }

    /**
     * Convert to CXF sessionStartInfo. This method is internal use.
     *
     * @return
     */
    public SessionStartInfoContract GetContractInfo() {
        return this.contract;
    }

    public void Validate() {
        if (contract.getServiceName() == null) {
            throw new IllegalArgumentException(SR.v("ServiceNameCantBeNull"));
        }
        if (contract.getClientIdleTimeout() != null
                && contract.getClientIdleTimeout().getValue() <= 0) {
            throw new IllegalArgumentException(
                    SR.v("ClientIdleTimeoutNotNegative"));
        }
        if (contract.getSessionIdleTimeout() != null
                && contract.getSessionIdleTimeout().getValue() < 0) {
            throw new IllegalArgumentException(
                    SR.v("SessionIdleTimeoutNotNegative"));
        }
        if (contract.getMessagesThrottleStopThreshold() != null
                && contract.getMessagesThrottleStopThreshold().getValue() < 0) {
            throw new IllegalArgumentException(
                    SR.v("MessageThrottleStopThresholdPositive"));
        }
        if (contract.getMessagesThrottleStopThreshold() != null
                && (contract.getMessagesThrottleStartThreshold() == null || contract
                .getMessagesThrottleStartThreshold().getValue() <= contract
                .getMessagesThrottleStopThreshold().getValue())) {
            throw new IllegalArgumentException(
                    SR.v("MessageThrottleStartGreaterStop"));
        }
        if (contract.getMessagesThrottleStartThreshold() != null
                && contract.getMessagesThrottleStartThreshold().getValue() < 0) {
            throw new IllegalArgumentException(
                    SR.v("MessageThrottleStartGreaterStop"));
        }
        if (contract.getAllocationShrinkLoadRatioThreshold() != null
                && contract.getAllocationShrinkLoadRatioThreshold().getValue() < 0) {
            throw new IllegalArgumentException(
                    SR.v("AllocationShrinkLoadRatioThresholdNonNegative"));
        }
        if (contract.getAllocationShrinkLoadRatioThreshold() != null
                && (contract.getAllocationGrowLoadRatioThreshold() == null || contract
                .getAllocationGrowLoadRatioThreshold().getValue() <= contract
                .getAllocationShrinkLoadRatioThreshold().getValue())) {
            throw new IllegalArgumentException(
                    SR.v("LoadRatioGrowGreaterThanShrink"));
        }
        if (this.isUseSessionPool() && !this.isShareSession()) {
            throw new IllegalArgumentException(SR.v("UnsharedSession_NotSupportSessionPool"));
        }
    }

    /**
     * Set the service version
     *
     * @param serviceVersion
     */
    public void setServiceVersion(Version serviceVersion) {
        if (serviceVersion == null) {
            contract.setServiceVersion(null);
            return;
        }

        org.datacontract.schemas._2004._07.system.Version ver = new org.datacontract.schemas._2004._07.system.Version();

        ver.setMajor(serviceVersion.getMajor());
        ver.setMinor(serviceVersion.getMinor());
        ver.setBuild(serviceVersion.getBuild());
        ver.setRevision(serviceVersion.getRevision());

        contract.setServiceVersion(factory
                .createSessionStartInfoContractServiceVersion(ver));

    }

    /*
     * Get the service version. If not set, return null.
     */
    public Version getServiceVersion() {
        JAXBElement<org.datacontract.schemas._2004._07.system.Version> ver = contract
                .getServiceVersion();

        if (ver != null) {
            org.datacontract.schemas._2004._07.system.Version version = ver
                    .getValue();
            return new Version(version.getMajor(), version.getMinor(),
                    version.getRevision(), version.getBuild());
        }

        return null;
    }

    /**
     * check if the session is secured
     *
     * @return
     */
    public Boolean isSecure() {
        return contract.isSecure();
    }

    /**
     * Check if the session is shared
     *
     * @return
     */
    public Boolean isShareSession() {
        if (contract.isShareSession() == null) {
            return false;
        } else {
            return contract.isShareSession();
        }
    }

    /**
     * set if the session is secure
     *
     * @param value
     */
    public void setSecure(Boolean value) {
        contract.setSecure(value);
    }

    /**
     * set if the session is shared
     *
     * @param value
     */
    public void setShareSession(Boolean value) {
        contract.setShareSession(value);
    }

    /**
     * get the resource unit type
     *
     * @return
     */
    public SessionUnitType getResourceUnitType() {
        if (contract.getResourceUnitType() != null) {

            switch (contract.getResourceUnitType().getValue()) {
                case 0:
                    return SessionUnitType.Core;
                case 1:
                    return SessionUnitType.Socket;
                case 2:
                    return SessionUnitType.Node;
            }
        }
        return SessionUnitType.Core;
    }

    /**
     * Set if the service job is preemptable
     *
     * @param value
     */
    public void setPreemptive(boolean value) {
        contract.setCanPreempt(factory
                .createSessionStartInfoContractCanPreempt(value));
    }

    /**
     * if the service job is preemptable
     *
     * @return
     */
    public boolean isPreemptive() {
        if (contract.getCanPreempt() == null) {
            return false;
        }
        return contract.getCanPreempt().getValue();
    }

    /**
     * Add one job environment variable
     *
     * @param name
     * @param value
     */
    public void addEnvironment(String name, String value) {
        ArrayOfKeyValueOfstringstring envs;
        if (contract.getEnvironments() == null) {
            envs = new ArrayOfKeyValueOfstringstring();
        } else {
            envs = contract.getEnvironments().getValue();
        }
        KeyValueOfstringstring v = new KeyValueOfstringstring();
        v.setKey(name);
        v.setValue(value);
        envs.getKeyValueOfstringstring().add(v);
        contract.setEnvironments(factory
                .createSessionStartInfoContractEnvironments(envs));
    }

    /**
     * Get the list of environments
     *
     * @return
     */
    public HashMap<String, String> getEnvironments() {
        HashMap<String, String> result = new HashMap<String, String>();
        ArrayOfKeyValueOfstringstring list;
        if (contract.getEnvironments() != null) {
            list = contract.getEnvironments().getValue();
            for (KeyValueOfstringstring s : list.getKeyValueOfstringstring()) {
                result.put(s.getKey(), s.getValue());
            }
        }

        return result;
    }

    /**
     * if the session uses session pool
     *
     * @return
     */
    public Boolean isUseSessionPool() {
        if (contract.isUseSessionPool() == null) {
            return false;
        } else {
            return contract.isUseSessionPool();
        }
    }

    /**
     * Set if the session uses session pool
     *
     * @param value
     */
    public void setUseSessionPool(Boolean value) {
        contract.setUseSessionPool(value);
    }
    
    /**
     * Set common data client id
     * 
     * @param dataClientId 
     */
    public void setCommonDataClientId(String dataClientId){
        addEnvironment(Constant.CcpCommonDataClientIdEnvVar, dataClientId);
    }
}
