
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
 *         &lt;element name="CreateResult" type="{http://hpc.microsoft.com/BrokerLauncher}BrokerInitializationResult" minOccurs="0"/>
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
    "createResult"
})
@XmlRootElement(name = "CreateResponse")
public class CreateResponse {

    @XmlElementRef(name = "CreateResult", namespace = "http://hpc.microsoft.com/brokerlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<BrokerInitializationResult> createResult;

    /**
     * Gets the value of the createResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BrokerInitializationResult }{@code >}
     *     
     */
    public JAXBElement<BrokerInitializationResult> getCreateResult() {
        return createResult;
    }

    /**
     * Sets the value of the createResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BrokerInitializationResult }{@code >}
     *     
     */
    public void setCreateResult(JAXBElement<BrokerInitializationResult> value) {
        this.createResult = ((JAXBElement<BrokerInitializationResult> ) value);
    }

}
