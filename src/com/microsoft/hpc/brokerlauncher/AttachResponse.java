
package com.microsoft.hpc.brokerlauncher;

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
 *         &lt;element name="AttachResult" type="{http://hpc.microsoft.com/BrokerLauncher}BrokerInitializationResult" minOccurs="0"/>
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
    "attachResult"
})
@XmlRootElement(name = "AttachResponse")
public class AttachResponse {

    @XmlElementRef(name = "AttachResult", namespace = "http://hpc.microsoft.com/brokerlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<BrokerInitializationResult> attachResult;

    /**
     * Gets the value of the attachResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BrokerInitializationResult }{@code >}
     *     
     */
    public JAXBElement<BrokerInitializationResult> getAttachResult() {
        return attachResult;
    }

    /**
     * Sets the value of the attachResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BrokerInitializationResult }{@code >}
     *     
     */
    public void setAttachResult(JAXBElement<BrokerInitializationResult> value) {
        this.attachResult = ((JAXBElement<BrokerInitializationResult> ) value);
    }

}
