
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="inp" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
    "inp"
})
@XmlRootElement(name = "EchoDouble")
public class EchoDouble {

    @XmlElement(name = "inp", namespace = "http://tempuri.org/")
    protected Double inp;

    /**
     * Gets the value of the inp property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getInp() {
        return inp;
    }

    /**
     * Sets the value of the inp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setInp(Double value) {
        this.inp = value;
    }

}
