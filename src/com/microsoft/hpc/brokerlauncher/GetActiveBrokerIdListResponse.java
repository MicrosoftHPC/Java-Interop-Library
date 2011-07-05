
package com.microsoft.hpc.brokerlauncher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfint;


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
 *         &lt;element name="GetActiveBrokerIdListResult" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfint" minOccurs="0"/>
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
    "getActiveBrokerIdListResult"
})
@XmlRootElement(name = "GetActiveBrokerIdListResponse")
public class GetActiveBrokerIdListResponse {

    @XmlElementRef(name = "GetActiveBrokerIdListResult", namespace = "http://hpc.microsoft.com/brokerlauncher/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfint> getActiveBrokerIdListResult;

    /**
     * Gets the value of the getActiveBrokerIdListResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public JAXBElement<ArrayOfint> getGetActiveBrokerIdListResult() {
        return getActiveBrokerIdListResult;
    }

    /**
     * Sets the value of the getActiveBrokerIdListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     *     
     */
    public void setGetActiveBrokerIdListResult(JAXBElement<ArrayOfint> value) {
        this.getActiveBrokerIdListResult = ((JAXBElement<ArrayOfint> ) value);
    }

}
