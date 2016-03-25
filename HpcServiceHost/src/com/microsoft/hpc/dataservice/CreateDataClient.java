
package com.microsoft.hpc.dataservice;

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
 *         &lt;element name="dataClientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allowedUsers" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
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
    "dataClientId",
    "allowedUsers"
})
@XmlRootElement(name = "CreateDataClient")
public class CreateDataClient {

    @XmlElementRef(name = "dataClientId", namespace = "http://hpc.microsoft.com/dataservice/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> dataClientId;
    @XmlElementRef(name = "allowedUsers", namespace = "http://hpc.microsoft.com/dataservice/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> allowedUsers;

    /**
     * Gets the value of the dataClientId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDataClientId() {
        return dataClientId;
    }

    /**
     * Sets the value of the dataClientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDataClientId(JAXBElement<String> value) {
        this.dataClientId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the allowedUsers property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getAllowedUsers() {
        return allowedUsers;
    }

    /**
     * Sets the value of the allowedUsers property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setAllowedUsers(JAXBElement<ArrayOfstring> value) {
        this.allowedUsers = ((JAXBElement<ArrayOfstring> ) value);
    }

}
