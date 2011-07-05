
package com.microsoft.hpc.sessionlauncher;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;
import org.datacontract.schemas._2004._07.system.Version;


/**
 * <p>Java class for SessionInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SessionInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrokerEpr" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="BrokerLauncherEpr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClientBrokerHeartbeatInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ClientBrokerHeartbeatRetryCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ControllerEpr" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="Durable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="JobState" type="{http://schemas.datacontract.org/2004/07/Microsoft.Hpc.Scheduler.Properties}JobState" minOccurs="0"/>
 *         &lt;element name="ResponseEpr" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="Secure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ServerVersion" type="{http://schemas.datacontract.org/2004/07/System}Version" minOccurs="0"/>
 *         &lt;element name="ServiceOperationTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ServiceVersion" type="{http://schemas.datacontract.org/2004/07/System}Version" minOccurs="0"/>
 *         &lt;element name="TransportScheme" type="{http://schemas.datacontract.org/2004/07/Microsoft.Hpc.Scheduler.Session}TransportScheme" minOccurs="0"/>
 *         &lt;element name="UseInprocessBroker" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SessionInfo", namespace = "http://hpc.microsoft.com/SessionLauncher", propOrder = {
    "brokerEpr",
    "brokerLauncherEpr",
    "clientBrokerHeartbeatInterval",
    "clientBrokerHeartbeatRetryCount",
    "controllerEpr",
    "durable",
    "id",
    "jobState",
    "responseEpr",
    "secure",
    "serverVersion",
    "serviceOperationTimeout",
    "serviceVersion",
    "transportScheme",
    "useInprocessBroker"
})
public class SessionInfo {

    @XmlElementRef(name = "BrokerEpr", namespace = "http://hpc.microsoft.com/SessionLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> brokerEpr;
    @XmlElementRef(name = "BrokerLauncherEpr", namespace = "http://hpc.microsoft.com/SessionLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<String> brokerLauncherEpr;
    @XmlElement(name = "ClientBrokerHeartbeatInterval")
    protected Integer clientBrokerHeartbeatInterval;
    @XmlElement(name = "ClientBrokerHeartbeatRetryCount")
    protected Integer clientBrokerHeartbeatRetryCount;
    @XmlElementRef(name = "ControllerEpr", namespace = "http://hpc.microsoft.com/SessionLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> controllerEpr;
    @XmlElement(name = "Durable")
    protected Boolean durable;
    @XmlElement(name = "Id")
    protected Integer id;
    @XmlList
    @XmlElement(name = "JobState")
    protected List<String> jobState;
    @XmlElementRef(name = "ResponseEpr", namespace = "http://hpc.microsoft.com/SessionLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> responseEpr;
    @XmlElement(name = "Secure")
    protected Boolean secure;
    @XmlElementRef(name = "ServerVersion", namespace = "http://hpc.microsoft.com/SessionLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<Version> serverVersion;
    @XmlElement(name = "ServiceOperationTimeout")
    protected Integer serviceOperationTimeout;
    @XmlElementRef(name = "ServiceVersion", namespace = "http://hpc.microsoft.com/SessionLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<Version> serviceVersion;
    @XmlList
    @XmlElement(name = "TransportScheme")
    protected List<String> transportScheme;
    @XmlElement(name = "UseInprocessBroker")
    protected Boolean useInprocessBroker;

    /**
     * Gets the value of the brokerEpr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getBrokerEpr() {
        return brokerEpr;
    }

    /**
     * Sets the value of the brokerEpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setBrokerEpr(JAXBElement<ArrayOfstring> value) {
        this.brokerEpr = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the brokerLauncherEpr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBrokerLauncherEpr() {
        return brokerLauncherEpr;
    }

    /**
     * Sets the value of the brokerLauncherEpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBrokerLauncherEpr(JAXBElement<String> value) {
        this.brokerLauncherEpr = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the clientBrokerHeartbeatInterval property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getClientBrokerHeartbeatInterval() {
        return clientBrokerHeartbeatInterval;
    }

    /**
     * Sets the value of the clientBrokerHeartbeatInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setClientBrokerHeartbeatInterval(Integer value) {
        this.clientBrokerHeartbeatInterval = value;
    }

    /**
     * Gets the value of the clientBrokerHeartbeatRetryCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getClientBrokerHeartbeatRetryCount() {
        return clientBrokerHeartbeatRetryCount;
    }

    /**
     * Sets the value of the clientBrokerHeartbeatRetryCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setClientBrokerHeartbeatRetryCount(Integer value) {
        this.clientBrokerHeartbeatRetryCount = value;
    }

    /**
     * Gets the value of the controllerEpr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getControllerEpr() {
        return controllerEpr;
    }

    /**
     * Sets the value of the controllerEpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setControllerEpr(JAXBElement<ArrayOfstring> value) {
        this.controllerEpr = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the durable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDurable() {
        return durable;
    }

    /**
     * Sets the value of the durable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDurable(Boolean value) {
        this.durable = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the jobState property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jobState property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJobState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getJobState() {
        if (jobState == null) {
            jobState = new ArrayList<String>();
        }
        return this.jobState;
    }

    /**
     * Gets the value of the responseEpr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getResponseEpr() {
        return responseEpr;
    }

    /**
     * Sets the value of the responseEpr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setResponseEpr(JAXBElement<ArrayOfstring> value) {
        this.responseEpr = ((JAXBElement<ArrayOfstring> ) value);
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
     * Gets the value of the serverVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Version }{@code >}
     *     
     */
    public JAXBElement<Version> getServerVersion() {
        return serverVersion;
    }

    /**
     * Sets the value of the serverVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Version }{@code >}
     *     
     */
    public void setServerVersion(JAXBElement<Version> value) {
        this.serverVersion = ((JAXBElement<Version> ) value);
    }

    /**
     * Gets the value of the serviceOperationTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getServiceOperationTimeout() {
        return serviceOperationTimeout;
    }

    /**
     * Sets the value of the serviceOperationTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setServiceOperationTimeout(Integer value) {
        this.serviceOperationTimeout = value;
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

}
