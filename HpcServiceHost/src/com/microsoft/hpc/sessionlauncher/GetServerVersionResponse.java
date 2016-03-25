
package com.microsoft.hpc.sessionlauncher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.system.Version;


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
 *         &lt;element name="GetServerVersionResult" type="{http://schemas.datacontract.org/2004/07/System}Version" minOccurs="0"/>
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
    "getServerVersionResult"
})
@XmlRootElement(name = "GetServerVersionResponse")
public class GetServerVersionResponse {

    @XmlElementRef(name = "GetServerVersionResult", namespace = "http://hpc.microsoft.com/sessionlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<Version> getServerVersionResult;

    /**
     * Gets the value of the getServerVersionResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Version }{@code >}
     *     
     */
    public JAXBElement<Version> getGetServerVersionResult() {
        return getServerVersionResult;
    }

    /**
     * Sets the value of the getServerVersionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Version }{@code >}
     *     
     */
    public void setGetServerVersionResult(JAXBElement<Version> value) {
        this.getServerVersionResult = ((JAXBElement<Version> ) value);
    }

}
