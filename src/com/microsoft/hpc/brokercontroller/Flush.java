
package com.microsoft.hpc.brokercontroller;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="clientid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeoutThrottlingMs" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="timeoutFlushMs" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "count",
    "clientid",
    "timeoutThrottlingMs",
    "timeoutFlushMs"
})
@XmlRootElement(name = "Flush")
public class Flush {

    protected Integer count;
    @XmlElementRef(name = "clientid", namespace = "http://hpc.microsoft.com/brokercontroller/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> clientid;
    protected Integer timeoutThrottlingMs;
    protected Integer timeoutFlushMs;

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCount(Integer value) {
        this.count = value;
    }

    /**
     * Gets the value of the clientid property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClientid() {
        return clientid;
    }

    /**
     * Sets the value of the clientid property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClientid(JAXBElement<String> value) {
        this.clientid = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the timeoutThrottlingMs property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTimeoutThrottlingMs() {
        return timeoutThrottlingMs;
    }

    /**
     * Sets the value of the timeoutThrottlingMs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTimeoutThrottlingMs(Integer value) {
        this.timeoutThrottlingMs = value;
    }

    /**
     * Gets the value of the timeoutFlushMs property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTimeoutFlushMs() {
        return timeoutFlushMs;
    }

    /**
     * Sets the value of the timeoutFlushMs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTimeoutFlushMs(Integer value) {
        this.timeoutFlushMs = value;
    }

}
