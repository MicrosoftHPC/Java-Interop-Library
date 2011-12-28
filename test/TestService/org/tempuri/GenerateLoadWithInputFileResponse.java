
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
 *         &lt;element name="GenerateLoadWithInputFileResult" type="{http://schemas.datacontract.org/2004/07/services}StatisticInfo" minOccurs="0"/>
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
    "generateLoadWithInputFileResult"
})
@XmlRootElement(name = "GenerateLoadWithInputFileResponse")
public class GenerateLoadWithInputFileResponse {

    @XmlElementRef(name = "GenerateLoadWithInputFileResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<StatisticInfo> generateLoadWithInputFileResult;

    /**
     * Gets the value of the generateLoadWithInputFileResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StatisticInfo }{@code >}
     *     
     */
    public JAXBElement<StatisticInfo> getGenerateLoadWithInputFileResult() {
        return generateLoadWithInputFileResult;
    }

    /**
     * Sets the value of the generateLoadWithInputFileResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StatisticInfo }{@code >}
     *     
     */
    public void setGenerateLoadWithInputFileResult(JAXBElement<StatisticInfo> value) {
        this.generateLoadWithInputFileResult = ((JAXBElement<StatisticInfo> ) value);
    }

}
