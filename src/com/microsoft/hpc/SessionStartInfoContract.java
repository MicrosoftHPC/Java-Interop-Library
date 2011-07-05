
package com.microsoft.hpc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfstringstring;
import org.datacontract.schemas._2004._07.system.Version;


/**
 * <p>Java class for SessionStartInfoContract complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SessionStartInfoContract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdminJobForHostInDiag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AllocationGrowLoadRatioThreshold" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AllocationShrinkLoadRatioThreshold" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CanPreempt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="ClientBrokerHeartbeatInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ClientBrokerHeartbeatRetryCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ClientConnectionTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ClientIdleTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DiagnosticBrokerNode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Environments" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfKeyValueOfstringstring" minOccurs="0"/>
 *         &lt;element name="ExtendedPriority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="JobTemplate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxMessageSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MaxUnits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MessagesThrottleStartThreshold" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MessagesThrottleStopThreshold" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MinUnits" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NodeGroupsStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PfxPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RequestedNodesStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResourceUnitType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Runtime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SavePassword" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Secure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ServiceJobName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceJobProject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceOperationTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ServiceVersion" type="{http://schemas.datacontract.org/2004/07/System}Version" minOccurs="0"/>
 *         &lt;element name="SessionIdleTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ShareSession" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TransportScheme" type="{http://schemas.datacontract.org/2004/07/Microsoft.Hpc.Scheduler.Session}TransportScheme" minOccurs="0"/>
 *         &lt;element name="UseSessionPool" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="UseInprocessBroker" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SessionStartInfoContract", propOrder = {
    "adminJobForHostInDiag",
    "allocationGrowLoadRatioThreshold",
    "allocationShrinkLoadRatioThreshold",
    "canPreempt",
    "certificate",
    "clientBrokerHeartbeatInterval",
    "clientBrokerHeartbeatRetryCount",
    "clientConnectionTimeout",
    "clientIdleTimeout",
    "diagnosticBrokerNode",
    "environments",
    "extendedPriority",
    "jobTemplate",
    "maxMessageSize",
    "maxUnits",
    "messagesThrottleStartThreshold",
    "messagesThrottleStopThreshold",
    "minUnits",
    "nodeGroupsStr",
    "password",
    "pfxPassword",
    "priority",
    "requestedNodesStr",
    "resourceUnitType",
    "runtime",
    "savePassword",
    "secure",
    "serviceJobName",
    "serviceJobProject",
    "serviceName",
    "serviceOperationTimeout",
    "serviceVersion",
    "sessionIdleTimeout",
    "shareSession",
    "transportScheme",
    "useSessionPool",
    "useInprocessBroker",
    "username"
})

public class SessionStartInfoContract {

    @XmlElement(name = "AdminJobForHostInDiag")
    protected Boolean adminJobForHostInDiag;
    @XmlElementRef(name = "AllocationGrowLoadRatioThreshold", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> allocationGrowLoadRatioThreshold;
    @XmlElementRef(name = "AllocationShrinkLoadRatioThreshold", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> allocationShrinkLoadRatioThreshold;
    @XmlElementRef(name = "CanPreempt", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> canPreempt;
    @XmlElementRef(name = "Certificate", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> certificate;
    @XmlElementRef(name = "ClientBrokerHeartbeatInterval", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> clientBrokerHeartbeatInterval;
    @XmlElementRef(name = "ClientBrokerHeartbeatRetryCount", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> clientBrokerHeartbeatRetryCount;
    @XmlElementRef(name = "ClientConnectionTimeout", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> clientConnectionTimeout;
    @XmlElementRef(name = "ClientIdleTimeout", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> clientIdleTimeout;
    @XmlElementRef(name = "DiagnosticBrokerNode", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> diagnosticBrokerNode;
    @XmlElementRef(name = "Environments", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfKeyValueOfstringstring> environments;
    @XmlElementRef(name = "ExtendedPriority", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> extendedPriority;
    @XmlElementRef(name = "JobTemplate", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jobTemplate;
    @XmlElementRef(name = "MaxMessageSize", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> maxMessageSize;
    @XmlElementRef(name = "MaxUnits", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> maxUnits;
    @XmlElementRef(name = "MessagesThrottleStartThreshold", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> messagesThrottleStartThreshold;
    @XmlElementRef(name = "MessagesThrottleStopThreshold", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> messagesThrottleStopThreshold;
    @XmlElementRef(name = "MinUnits", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> minUnits;
    @XmlElementRef(name = "NodeGroupsStr", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nodeGroupsStr;
    @XmlElementRef(name = "Password", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> password;
    @XmlElementRef(name = "PfxPassword", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pfxPassword;
    @XmlElementRef(name = "Priority", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> priority;
    @XmlElementRef(name = "RequestedNodesStr", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> requestedNodesStr;
    @XmlElementRef(name = "ResourceUnitType", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> resourceUnitType;
    @XmlElement(name = "Runtime")
    protected Integer runtime;
    @XmlElementRef(name = "SavePassword", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> savePassword;
    @XmlElement(name = "Secure")
    protected Boolean secure;
    @XmlElementRef(name = "ServiceJobName", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> serviceJobName;
    @XmlElementRef(name = "ServiceJobProject", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> serviceJobProject;
    @XmlElementRef(name = "ServiceName", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> serviceName;
    @XmlElementRef(name = "ServiceOperationTimeout", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> serviceOperationTimeout;
    @XmlElementRef(name = "ServiceVersion", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Version> serviceVersion;
    @XmlElementRef(name = "SessionIdleTimeout", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> sessionIdleTimeout;
    @XmlElement(name = "ShareSession")
    protected Boolean shareSession;
    @XmlList
    @XmlElement(name = "TransportScheme")
    protected List<String> transportScheme;
    @XmlElement(name = "UseSessionPool")
    protected Boolean useSessionPool;
    @XmlElement(name = "UseInprocessBroker")
    protected Boolean useInprocessBroker;
    @XmlElementRef(name = "Username", namespace = "http://hpc.microsoft.com/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> username;

    /**
     * Gets the value of the adminJobForHostInDiag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAdminJobForHostInDiag() {
        return adminJobForHostInDiag;
    }

    /**
     * Sets the value of the adminJobForHostInDiag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAdminJobForHostInDiag(Boolean value) {
        this.adminJobForHostInDiag = value;
    }

    /**
     * Gets the value of the allocationGrowLoadRatioThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getAllocationGrowLoadRatioThreshold() {
        return allocationGrowLoadRatioThreshold;
    }

    /**
     * Sets the value of the allocationGrowLoadRatioThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setAllocationGrowLoadRatioThreshold(JAXBElement<Integer> value) {
        this.allocationGrowLoadRatioThreshold = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the allocationShrinkLoadRatioThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getAllocationShrinkLoadRatioThreshold() {
        return allocationShrinkLoadRatioThreshold;
    }

    /**
     * Sets the value of the allocationShrinkLoadRatioThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setAllocationShrinkLoadRatioThreshold(JAXBElement<Integer> value) {
        this.allocationShrinkLoadRatioThreshold = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the canPreempt property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getCanPreempt() {
        return canPreempt;
    }

    /**
     * Sets the value of the canPreempt property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setCanPreempt(JAXBElement<Boolean> value) {
        this.canPreempt = ((JAXBElement<Boolean> ) value);
    }

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setCertificate(JAXBElement<byte[]> value) {
        this.certificate = ((JAXBElement<byte[]> ) value);
    }

    /**
     * Gets the value of the clientBrokerHeartbeatInterval property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getClientBrokerHeartbeatInterval() {
        return clientBrokerHeartbeatInterval;
    }

    /**
     * Sets the value of the clientBrokerHeartbeatInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setClientBrokerHeartbeatInterval(JAXBElement<Integer> value) {
        this.clientBrokerHeartbeatInterval = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the clientBrokerHeartbeatRetryCount property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getClientBrokerHeartbeatRetryCount() {
        return clientBrokerHeartbeatRetryCount;
    }

    /**
     * Sets the value of the clientBrokerHeartbeatRetryCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setClientBrokerHeartbeatRetryCount(JAXBElement<Integer> value) {
        this.clientBrokerHeartbeatRetryCount = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the clientConnectionTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getClientConnectionTimeout() {
        return clientConnectionTimeout;
    }

    /**
     * Sets the value of the clientConnectionTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setClientConnectionTimeout(JAXBElement<Integer> value) {
        this.clientConnectionTimeout = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the clientIdleTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getClientIdleTimeout() {
        return clientIdleTimeout;
    }

    /**
     * Sets the value of the clientIdleTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setClientIdleTimeout(JAXBElement<Integer> value) {
        this.clientIdleTimeout = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the diagnosticBrokerNode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDiagnosticBrokerNode() {
        return diagnosticBrokerNode;
    }

    /**
     * Sets the value of the diagnosticBrokerNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDiagnosticBrokerNode(JAXBElement<String> value) {
        this.diagnosticBrokerNode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the environments property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfstringstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfKeyValueOfstringstring> getEnvironments() {
        return environments;
    }

    /**
     * Sets the value of the environments property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfstringstring }{@code >}
     *     
     */
    public void setEnvironments(JAXBElement<ArrayOfKeyValueOfstringstring> value) {
        this.environments = ((JAXBElement<ArrayOfKeyValueOfstringstring> ) value);
    }

    /**
     * Gets the value of the extendedPriority property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getExtendedPriority() {
        return extendedPriority;
    }

    /**
     * Sets the value of the extendedPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setExtendedPriority(JAXBElement<Integer> value) {
        this.extendedPriority = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the jobTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJobTemplate() {
        return jobTemplate;
    }

    /**
     * Sets the value of the jobTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJobTemplate(JAXBElement<String> value) {
        this.jobTemplate = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the maxMessageSize property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getMaxMessageSize() {
        return maxMessageSize;
    }

    /**
     * Sets the value of the maxMessageSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setMaxMessageSize(JAXBElement<Integer> value) {
        this.maxMessageSize = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the maxUnits property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getMaxUnits() {
        return maxUnits;
    }

    /**
     * Sets the value of the maxUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setMaxUnits(JAXBElement<Integer> value) {
        this.maxUnits = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the messagesThrottleStartThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getMessagesThrottleStartThreshold() {
        return messagesThrottleStartThreshold;
    }

    /**
     * Sets the value of the messagesThrottleStartThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setMessagesThrottleStartThreshold(JAXBElement<Integer> value) {
        this.messagesThrottleStartThreshold = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the messagesThrottleStopThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getMessagesThrottleStopThreshold() {
        return messagesThrottleStopThreshold;
    }

    /**
     * Sets the value of the messagesThrottleStopThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setMessagesThrottleStopThreshold(JAXBElement<Integer> value) {
        this.messagesThrottleStopThreshold = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the minUnits property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getMinUnits() {
        return minUnits;
    }

    /**
     * Sets the value of the minUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setMinUnits(JAXBElement<Integer> value) {
        this.minUnits = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the nodeGroupsStr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNodeGroupsStr() {
        return nodeGroupsStr;
    }

    /**
     * Sets the value of the nodeGroupsStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNodeGroupsStr(JAXBElement<String> value) {
        this.nodeGroupsStr = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPassword(JAXBElement<String> value) {
        this.password = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the pfxPassword property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPfxPassword() {
        return pfxPassword;
    }

    /**
     * Sets the value of the pfxPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPfxPassword(JAXBElement<String> value) {
        this.pfxPassword = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setPriority(JAXBElement<Integer> value) {
        this.priority = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the requestedNodesStr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRequestedNodesStr() {
        return requestedNodesStr;
    }

    /**
     * Sets the value of the requestedNodesStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRequestedNodesStr(JAXBElement<String> value) {
        this.requestedNodesStr = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the resourceUnitType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getResourceUnitType() {
        return resourceUnitType;
    }

    /**
     * Sets the value of the resourceUnitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setResourceUnitType(JAXBElement<Integer> value) {
        this.resourceUnitType = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the runtime property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * Sets the value of the runtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRuntime(Integer value) {
        this.runtime = value;
    }

    /**
     * Gets the value of the savePassword property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getSavePassword() {
        return savePassword;
    }

    /**
     * Sets the value of the savePassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setSavePassword(JAXBElement<Boolean> value) {
        this.savePassword = ((JAXBElement<Boolean> ) value);
    }

    /**
     * Gets the value of the secure property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSecure() {
        return secure;
    }

    /**
     * Sets the value of the secure property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSecure(Boolean value) {
        this.secure = value;
    }

    /**
     * Gets the value of the serviceJobName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceJobName() {
        return serviceJobName;
    }

    /**
     * Sets the value of the serviceJobName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceJobName(JAXBElement<String> value) {
        this.serviceJobName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the serviceJobProject property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceJobProject() {
        return serviceJobProject;
    }

    /**
     * Sets the value of the serviceJobProject property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceJobProject(JAXBElement<String> value) {
        this.serviceJobProject = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceName(JAXBElement<String> value) {
        this.serviceName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the serviceOperationTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getServiceOperationTimeout() {
        return serviceOperationTimeout;
    }

    /**
     * Sets the value of the serviceOperationTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setServiceOperationTimeout(JAXBElement<Integer> value) {
        this.serviceOperationTimeout = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the serviceVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Version }{@code >}
     *     
     */
    public JAXBElement<Version> getServiceVersion() {
        return serviceVersion;
    }

    /**
     * Sets the value of the serviceVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Version }{@code >}
     *     
     */
    public void setServiceVersion(JAXBElement<Version> value) {
        this.serviceVersion = ((JAXBElement<Version> ) value);
    }

    /**
     * Gets the value of the sessionIdleTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getSessionIdleTimeout() {
        return sessionIdleTimeout;
    }

    /**
     * Sets the value of the sessionIdleTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setSessionIdleTimeout(JAXBElement<Integer> value) {
        this.sessionIdleTimeout = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the shareSession property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShareSession() {
        return shareSession;
    }

    /**
     * Sets the value of the shareSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShareSession(Boolean value) {
        this.shareSession = value;
    }

    /**
     * Gets the value of the transportScheme property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transportScheme property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransportScheme().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTransportScheme() {
        if (transportScheme == null) {
            transportScheme = new ArrayList<String>();
        }
        return this.transportScheme;
    }

    /**
     * Gets the value of the useInprocessBroker property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseInprocessBroker() {
        return useInprocessBroker;
    }

    /**
     * Sets the value of the useInprocessBroker property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseInprocessBroker(Boolean value) {
        this.useInprocessBroker = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsername(JAXBElement<String> value) {
        this.username = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the useSessionPool property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseSessionPool() {
        return useSessionPool;
    }

    /**
     * Sets the value of the useSessionPool property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseSessionPool(Boolean value) {
        this.useSessionPool = value;
    }
}
