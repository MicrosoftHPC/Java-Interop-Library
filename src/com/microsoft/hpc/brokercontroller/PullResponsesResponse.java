
package com.microsoft.hpc.brokercontroller;

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
 *         &lt;element name="PullResponsesResult" type="{http://hpc.microsoft.com/brokercontroller/}BrokerResponseMessages" minOccurs="0"/>
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
    "pullResponsesResult"
})
@XmlRootElement(name = "PullResponsesResponse")
public class PullResponsesResponse {

    @XmlElement(name = "PullResponsesResult")
    protected BrokerResponseMessages pullResponsesResult;

    /**
     * Gets the value of the pullResponsesResult property.
     * 
     * @return
     *     possible object is
     *     {@link BrokerResponseMessages }
     *     
     */
    public BrokerResponseMessages getPullResponsesResult() {
        return pullResponsesResult;
    }

    /**
     * Sets the value of the pullResponsesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrokerResponseMessages }
     *     
     */
    public void setPullResponsesResult(BrokerResponseMessages value) {
        this.pullResponsesResult = value;
    }

}
