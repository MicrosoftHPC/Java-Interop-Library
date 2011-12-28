
package com.microsoft.hpc.brokerlauncher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


/**
 * <p>Java class for BrokerInitializationResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BrokerInitializationResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrokerEpr" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="BrokerUniqueId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClientBrokerHeartbeatInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ClientBrokerHeartbeatRetryCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ControllerEpr" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="MaxMessageSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ResponseEpr" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="ServiceOperationTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrokerInitializationResult", namespace = "http://hpc.microsoft.com/BrokerLauncher", propOrder = {
    "brokerEpr",
    "brokerUniqueId",
    "clientBrokerHeartbeatInterval",
    "clientBrokerHeartbeatRetryCount",
    "controllerEpr",
    "maxMessageSize",
    "responseEpr",
    "serviceOperationTimeout"
})
public class BrokerInitializationResult {

    @XmlElementRef(name = "BrokerEpr", namespace = "http://hpc.microsoft.com/BrokerLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> brokerEpr;
    @XmlElementRef(name = "BrokerUniqueId", namespace = "http://hpc.microsoft.com/BrokerLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<String> brokerUniqueId;
    @XmlElement(name = "ClientBrokerHeartbeatInterval")
    protected Integer clientBrokerHeartbeatInterval;
    @XmlElement(name = "ClientBrokerHeartbeatRetryCount")
    protected Integer clientBrokerHeartbeatRetryCount;
    @XmlElementRef(name = "ControllerEpr", namespace = "http://hpc.microsoft.com/BrokerLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> controllerEpr;
    @XmlElement(name = "MaxMessageSize")
    protected Long maxMessageSize;
    @XmlElementRef(name = "ResponseEpr", namespace = "http://hpc.microsoft.com/BrokerLauncher", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> responseEpr;
    @XmlElement(name = "ServiceOperationTimeout")
    protected Integer serviceOperationTimeout;

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
     * Gets the value of the brokerUniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBrokerUniqueId() {
        return brokerUniqueId;
    }

    /**
     * Sets the value of the brokerUniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBrokerUniqueId(JAXBElement<String> value) {
        this.brokerUniqueId = ((JAXBElement<String> ) value);
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
     * Gets the value of the maxMessageSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxMessageSize() {
        return maxMessageSize;
    }

    /**
     * Sets the value of the maxMessageSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxMessageSize(Long value) {
        this.maxMessageSize = value;
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

}
