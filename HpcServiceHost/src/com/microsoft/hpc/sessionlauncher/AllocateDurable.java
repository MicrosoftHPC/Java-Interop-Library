
package com.microsoft.hpc.sessionlauncher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.hpc.SessionStartInfoContract;


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
 *         &lt;element name="info" type="{http://hpc.microsoft.com/}SessionStartInfoContract" minOccurs="0"/>
 *         &lt;element name="endpointPrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "info",
    "endpointPrefix"
})
@XmlRootElement(name = "AllocateDurable")
public class AllocateDurable {

    @XmlElementRef(name = "info", namespace = "http://hpc.microsoft.com/sessionlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<SessionStartInfoContract> info;
    @XmlElementRef(name = "endpointPrefix", namespace = "http://hpc.microsoft.com/sessionlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> endpointPrefix;

    /**
     * Gets the value of the info property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SessionStartInfoContract }{@code >}
     *     
     */
    public JAXBElement<SessionStartInfoContract> getInfo() {
        return info;
    }

    /**
     * Sets the value of the info property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SessionStartInfoContract }{@code >}
     *     
     */
    public void setInfo(JAXBElement<SessionStartInfoContract> value) {
        this.info = ((JAXBElement<SessionStartInfoContract> ) value);
    }

    /**
     * Gets the value of the endpointPrefix property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEndpointPrefix() {
        return endpointPrefix;
    }

    /**
     * Sets the value of the endpointPrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEndpointPrefix(JAXBElement<String> value) {
        this.endpointPrefix = ((JAXBElement<String> ) value);
    }

}
