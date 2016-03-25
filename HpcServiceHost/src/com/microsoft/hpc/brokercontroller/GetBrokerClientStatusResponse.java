
package com.microsoft.hpc.brokercontroller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.hpc.BrokerClientStatus;


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
 *         &lt;element name="GetBrokerClientStatusResult" type="{http://hpc.microsoft.com}BrokerClientStatus" minOccurs="0"/>
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
    "getBrokerClientStatusResult"
})
@XmlRootElement(name = "GetBrokerClientStatusResponse")
public class GetBrokerClientStatusResponse {

    @XmlElement(name = "GetBrokerClientStatusResult")
    protected BrokerClientStatus getBrokerClientStatusResult;

    /**
     * Gets the value of the getBrokerClientStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link BrokerClientStatus }
     *     
     */
    public BrokerClientStatus getGetBrokerClientStatusResult() {
        return getBrokerClientStatusResult;
    }

    /**
     * Sets the value of the getBrokerClientStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrokerClientStatus }
     *     
     */
    public void setGetBrokerClientStatusResult(BrokerClientStatus value) {
        this.getBrokerClientStatusResult = value;
    }

}
