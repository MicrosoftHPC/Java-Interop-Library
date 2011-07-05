
package com.microsoft.hpc.sessionlauncher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


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
 *         &lt;element name="AllocateDurableResult" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="sessionid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="serviceVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "allocateDurableResult",
    "sessionid",
    "serviceVersion"
})
@XmlRootElement(name = "AllocateDurableResponse")
public class AllocateDurableResponse {

    @XmlElementRef(name = "AllocateDurableResult", namespace = "http://hpc.microsoft.com/sessionlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> allocateDurableResult;
    protected Integer sessionid;
    @XmlElementRef(name = "serviceVersion", namespace = "http://hpc.microsoft.com/sessionlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> serviceVersion;

    /**
     * Gets the value of the allocateDurableResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getAllocateDurableResult() {
        return allocateDurableResult;
    }

    /**
     * Sets the value of the allocateDurableResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setAllocateDurableResult(JAXBElement<ArrayOfstring> value) {
        this.allocateDurableResult = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the sessionid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSessionid() {
        return sessionid;
    }

    /**
     * Sets the value of the sessionid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSessionid(Integer value) {
        this.sessionid = value;
    }

    /**
     * Gets the value of the serviceVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceVersion() {
        return serviceVersion;
    }

    /**
     * Sets the value of the serviceVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceVersion(JAXBElement<String> value) {
        this.serviceVersion = ((JAXBElement<String> ) value);
    }

}
