
package com.microsoft.hpc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfstringstring;
import org.datacontract.schemas._2004._07.system.Version;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SessionStartInfoContract_QNAME = new QName("http://hpc.microsoft.com/", "SessionStartInfoContract");
    private final static QName _SessionStartInfoContractServiceJobProject_QNAME = new QName("http://hpc.microsoft.com/", "ServiceJobProject");
    private final static QName _SessionStartInfoContractCertificate_QNAME = new QName("http://hpc.microsoft.com/", "Certificate");
    private final static QName _SessionStartInfoContractAllocationGrowLoadRatioThreshold_QNAME = new QName("http://hpc.microsoft.com/", "AllocationGrowLoadRatioThreshold");
    private final static QName _SessionStartInfoContractMaxMessageSize_QNAME = new QName("http://hpc.microsoft.com/", "MaxMessageSize");
    private final static QName _SessionStartInfoContractClientBrokerHeartbeatInterval_QNAME = new QName("http://hpc.microsoft.com/", "ClientBrokerHeartbeatInterval");
    private final static QName _SessionStartInfoContractClientConnectionTimeout_QNAME = new QName("http://hpc.microsoft.com/", "ClientConnectionTimeout");
    private final static QName _SessionStartInfoContractClientBrokerHeartbeatRetryCount_QNAME = new QName("http://hpc.microsoft.com/", "ClientBrokerHeartbeatRetryCount");
    private final static QName _SessionStartInfoContractNodeGroupsStr_QNAME = new QName("http://hpc.microsoft.com/", "NodeGroupsStr");
    private final static QName _SessionStartInfoContractUsername_QNAME = new QName("http://hpc.microsoft.com/", "Username");
    private final static QName _SessionStartInfoContractRequestedNodesStr_QNAME = new QName("http://hpc.microsoft.com/", "RequestedNodesStr");
    private final static QName _SessionStartInfoContractPriority_QNAME = new QName("http://hpc.microsoft.com/", "Priority");
    private final static QName _SessionStartInfoContractCanPreempt_QNAME = new QName("http://hpc.microsoft.com/", "CanPreempt");
    private final static QName _SessionStartInfoContractMessagesThrottleStopThreshold_QNAME = new QName("http://hpc.microsoft.com/", "MessagesThrottleStopThreshold");
    private final static QName _SessionStartInfoContractServiceVersion_QNAME = new QName("http://hpc.microsoft.com/", "ServiceVersion");
    private final static QName _SessionStartInfoContractPfxPassword_QNAME = new QName("http://hpc.microsoft.com/", "PfxPassword");
    private final static QName _SessionStartInfoContractAllocationShrinkLoadRatioThreshold_QNAME = new QName("http://hpc.microsoft.com/", "AllocationShrinkLoadRatioThreshold");
    private final static QName _SessionStartInfoContractSessionIdleTimeout_QNAME = new QName("http://hpc.microsoft.com/", "SessionIdleTimeout");
    private final static QName _SessionStartInfoContractExtendedPriority_QNAME = new QName("http://hpc.microsoft.com/", "ExtendedPriority");
    private final static QName _SessionStartInfoContractServiceOperationTimeout_QNAME = new QName("http://hpc.microsoft.com/", "ServiceOperationTimeout");
    private final static QName _SessionStartInfoContractMaxUnits_QNAME = new QName("http://hpc.microsoft.com/", "MaxUnits");
    private final static QName _SessionStartInfoContractMessagesThrottleStartThreshold_QNAME = new QName("http://hpc.microsoft.com/", "MessagesThrottleStartThreshold");
    private final static QName _SessionStartInfoContractServiceJobName_QNAME = new QName("http://hpc.microsoft.com/", "ServiceJobName");
    private final static QName _SessionStartInfoContractServiceName_QNAME = new QName("http://hpc.microsoft.com/", "ServiceName");
    private final static QName _SessionStartInfoContractSavePassword_QNAME = new QName("http://hpc.microsoft.com/", "SavePassword");
    private final static QName _SessionStartInfoContractJobTemplate_QNAME = new QName("http://hpc.microsoft.com/", "JobTemplate");
    private final static QName _SessionStartInfoContractMinUnits_QNAME = new QName("http://hpc.microsoft.com/", "MinUnits");
    private final static QName _SessionStartInfoContractDiagnosticBrokerNode_QNAME = new QName("http://hpc.microsoft.com/", "DiagnosticBrokerNode");
    private final static QName _SessionStartInfoContractEnvironments_QNAME = new QName("http://hpc.microsoft.com/", "Environments");
    private final static QName _SessionStartInfoContractClientIdleTimeout_QNAME = new QName("http://hpc.microsoft.com/", "ClientIdleTimeout");
    private final static QName _SessionStartInfoContractPassword_QNAME = new QName("http://hpc.microsoft.com/", "Password");
    private final static QName _SessionStartInfoContractResourceUnitType_QNAME = new QName("http://hpc.microsoft.com/", "ResourceUnitType");
    private final static QName _BrokerClientStatus_QNAME = new QName("http://hpc.microsoft.com", "BrokerClientStatus");
    
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SessionStartInfoContract }
     * 
     */
    public SessionStartInfoContract createSessionStartInfoContract() {
        return new SessionStartInfoContract();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionStartInfoContract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "SessionStartInfoContract")
    public JAXBElement<SessionStartInfoContract> createSessionStartInfoContract(SessionStartInfoContract value) {
        return new JAXBElement<SessionStartInfoContract>(_SessionStartInfoContract_QNAME, SessionStartInfoContract.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ServiceJobProject", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractServiceJobProject(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractServiceJobProject_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "Certificate", scope = SessionStartInfoContract.class)
    public JAXBElement<byte[]> createSessionStartInfoContractCertificate(byte[] value) {
        return new JAXBElement<byte[]>(_SessionStartInfoContractCertificate_QNAME, byte[].class, SessionStartInfoContract.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "AllocationGrowLoadRatioThreshold", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractAllocationGrowLoadRatioThreshold(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractAllocationGrowLoadRatioThreshold_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "MaxMessageSize", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractMaxMessageSize(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractMaxMessageSize_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ClientBrokerHeartbeatInterval", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractClientBrokerHeartbeatInterval(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractClientBrokerHeartbeatInterval_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ClientConnectionTimeout", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractClientConnectionTimeout(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractClientConnectionTimeout_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ClientBrokerHeartbeatRetryCount", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractClientBrokerHeartbeatRetryCount(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractClientBrokerHeartbeatRetryCount_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "NodeGroupsStr", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractNodeGroupsStr(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractNodeGroupsStr_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "Username", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractUsername(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractUsername_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "RequestedNodesStr", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractRequestedNodesStr(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractRequestedNodesStr_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "Priority", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractPriority(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractPriority_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "CanPreempt", scope = SessionStartInfoContract.class)
    public JAXBElement<Boolean> createSessionStartInfoContractCanPreempt(Boolean value) {
        return new JAXBElement<Boolean>(_SessionStartInfoContractCanPreempt_QNAME, Boolean.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "MessagesThrottleStopThreshold", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractMessagesThrottleStopThreshold(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractMessagesThrottleStopThreshold_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Version }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ServiceVersion", scope = SessionStartInfoContract.class)
    public JAXBElement<Version> createSessionStartInfoContractServiceVersion(Version value) {
        return new JAXBElement<Version>(_SessionStartInfoContractServiceVersion_QNAME, Version.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "PfxPassword", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractPfxPassword(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractPfxPassword_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "AllocationShrinkLoadRatioThreshold", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractAllocationShrinkLoadRatioThreshold(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractAllocationShrinkLoadRatioThreshold_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "SessionIdleTimeout", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractSessionIdleTimeout(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractSessionIdleTimeout_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ExtendedPriority", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractExtendedPriority(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractExtendedPriority_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ServiceOperationTimeout", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractServiceOperationTimeout(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractServiceOperationTimeout_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "MaxUnits", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractMaxUnits(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractMaxUnits_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "MessagesThrottleStartThreshold", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractMessagesThrottleStartThreshold(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractMessagesThrottleStartThreshold_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ServiceJobName", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractServiceJobName(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractServiceJobName_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ServiceName", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractServiceName(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractServiceName_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "SavePassword", scope = SessionStartInfoContract.class)
    public JAXBElement<Boolean> createSessionStartInfoContractSavePassword(Boolean value) {
        return new JAXBElement<Boolean>(_SessionStartInfoContractSavePassword_QNAME, Boolean.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "JobTemplate", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractJobTemplate(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractJobTemplate_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "MinUnits", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractMinUnits(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractMinUnits_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "DiagnosticBrokerNode", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractDiagnosticBrokerNode(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractDiagnosticBrokerNode_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfstringstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "Environments", scope = SessionStartInfoContract.class)
    public JAXBElement<ArrayOfKeyValueOfstringstring> createSessionStartInfoContractEnvironments(ArrayOfKeyValueOfstringstring value) {
        return new JAXBElement<ArrayOfKeyValueOfstringstring>(_SessionStartInfoContractEnvironments_QNAME, ArrayOfKeyValueOfstringstring.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ClientIdleTimeout", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractClientIdleTimeout(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractClientIdleTimeout_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "Password", scope = SessionStartInfoContract.class)
    public JAXBElement<String> createSessionStartInfoContractPassword(String value) {
        return new JAXBElement<String>(_SessionStartInfoContractPassword_QNAME, String.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/", name = "ResourceUnitType", scope = SessionStartInfoContract.class)
    public JAXBElement<Integer> createSessionStartInfoContractResourceUnitType(Integer value) {
        return new JAXBElement<Integer>(_SessionStartInfoContractResourceUnitType_QNAME, Integer.class, SessionStartInfoContract.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrokerClientStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "BrokerClientStatus")
    public JAXBElement<BrokerClientStatus> createBrokerClientStatus(BrokerClientStatus value) {
        return new JAXBElement<BrokerClientStatus>(_BrokerClientStatus_QNAME, BrokerClientStatus.class, null, value);
    }
}
