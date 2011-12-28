
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.services.ComputerInfo;


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
 *         &lt;element name="EchoWithParamResult" type="{http://schemas.datacontract.org/2004/07/services}ComputerInfo" minOccurs="0"/>
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
    "echoWithParamResult"
})
@XmlRootElement(name = "EchoWithParamResponse")
public class EchoWithParamResponse {

    @XmlElementRef(name = "EchoWithParamResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ComputerInfo> echoWithParamResult;

    /**
     * Gets the value of the echoWithParamResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}
     *     
     */
    public JAXBElement<ComputerInfo> getEchoWithParamResult() {
        return echoWithParamResult;
    }

    /**
     * Sets the value of the echoWithParamResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}
     *     
     */
    public void setEchoWithParamResult(JAXBElement<ComputerInfo> value) {
        this.echoWithParamResult = ((JAXBElement<ComputerInfo> ) value);
    }

}
