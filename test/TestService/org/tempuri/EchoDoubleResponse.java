
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
 *         &lt;element name="EchoDoubleResult" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
    "echoDoubleResult"
})
@XmlRootElement(name = "EchoDoubleResponse")
public class EchoDoubleResponse {

    @XmlElement(name = "EchoDoubleResult", namespace = "http://tempuri.org/")
    protected Double echoDoubleResult;

    /**
     * Gets the value of the echoDoubleResult property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEchoDoubleResult() {
        return echoDoubleResult;
    }

    /**
     * Sets the value of the echoDoubleResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEchoDoubleResult(Double value) {
        this.echoDoubleResult = value;
    }

}
