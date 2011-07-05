
package com.microsoft.hpc.sessionlauncher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.system.ArrayOfVersion;


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
 *         &lt;element name="GetServiceVersionsResult" type="{http://schemas.datacontract.org/2004/07/System}ArrayOfVersion" minOccurs="0"/>
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
    "getServiceVersionsResult"
})
@XmlRootElement(name = "GetServiceVersionsResponse")
public class GetServiceVersionsResponse {

    @XmlElementRef(name = "GetServiceVersionsResult", namespace = "http://hpc.microsoft.com/sessionlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfVersion> getServiceVersionsResult;

    /**
     * Gets the value of the getServiceVersionsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfVersion }{@code >}
     *     
     */
    public JAXBElement<ArrayOfVersion> getGetServiceVersionsResult() {
        return getServiceVersionsResult;
    }

    /**
     * Sets the value of the getServiceVersionsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfVersion }{@code >}
     *     
     */
    public void setGetServiceVersionsResult(JAXBElement<ArrayOfVersion> value) {
        this.getServiceVersionsResult = ((JAXBElement<ArrayOfVersion> ) value);
    }

}
