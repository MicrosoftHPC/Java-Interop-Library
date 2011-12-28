
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.services.StatisticInfo;


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
 *         &lt;element name="GenerateLoadResult" type="{http://schemas.datacontract.org/2004/07/services}StatisticInfo" minOccurs="0"/>
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
    "generateLoadResult"
})
@XmlRootElement(name = "GenerateLoadResponse")
public class GenerateLoadResponse {

    @XmlElementRef(name = "GenerateLoadResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<StatisticInfo> generateLoadResult;

    /**
     * Gets the value of the generateLoadResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StatisticInfo }{@code >}
     *     
     */
    public JAXBElement<StatisticInfo> getGenerateLoadResult() {
        return generateLoadResult;
    }

    /**
     * Sets the value of the generateLoadResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StatisticInfo }{@code >}
     *     
     */
    public void setGenerateLoadResult(JAXBElement<StatisticInfo> value) {
        this.generateLoadResult = ((JAXBElement<StatisticInfo> ) value);
    }

}
