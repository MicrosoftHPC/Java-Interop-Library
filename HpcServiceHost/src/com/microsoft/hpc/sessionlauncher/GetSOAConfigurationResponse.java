
package com.microsoft.hpc.sessionlauncher;

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
 *         &lt;element name="GetSOAConfigurationResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getSOAConfigurationResult"
})
@XmlRootElement(name = "GetSOAConfigurationResponse")
public class GetSOAConfigurationResponse {

    @XmlElementRef(name = "GetSOAConfigurationResult", namespace = "http://hpc.microsoft.com/sessionlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> getSOAConfigurationResult;

    /**
     * Gets the value of the getSOAConfigurationResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGetSOAConfigurationResult() {
        return getSOAConfigurationResult;
    }

    /**
     * Sets the value of the getSOAConfigurationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGetSOAConfigurationResult(JAXBElement<String> value) {
        this.getSOAConfigurationResult = ((JAXBElement<String> ) value);
    }

}
