
package com.microsoft.hpc.genericservice;

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
 *         &lt;element name="GenericOperationResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "genericOperationResult"
})
@XmlRootElement(name = "GenericOperationResponse")
public class GenericOperationResponse {

    @XmlElementRef(name = "GenericOperationResult", namespace = "http://hpc.microsoft.com/GenericService", type = JAXBElement.class, required = false)
    protected JAXBElement<String> genericOperationResult;

    /**
     * Gets the value of the genericOperationResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGenericOperationResult() {
        return genericOperationResult;
    }

    /**
     * Sets the value of the genericOperationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGenericOperationResult(JAXBElement<String> value) {
        this.genericOperationResult = ((JAXBElement<String> ) value);
    }

}
