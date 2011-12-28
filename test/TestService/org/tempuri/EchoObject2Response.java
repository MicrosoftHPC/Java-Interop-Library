
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.services.ClassObj;


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
 *         &lt;element name="EchoObject2Result" type="{http://schemas.datacontract.org/2004/07/services}ClassObj" minOccurs="0"/>
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
    "echoObject2Result"
})
@XmlRootElement(name = "EchoObject2Response")
public class EchoObject2Response {

    @XmlElementRef(name = "EchoObject2Result", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ClassObj> echoObject2Result;

    /**
     * Gets the value of the echoObject2Result property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ClassObj }{@code >}
     *     
     */
    public JAXBElement<ClassObj> getEchoObject2Result() {
        return echoObject2Result;
    }

    /**
     * Sets the value of the echoObject2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ClassObj }{@code >}
     *     
     */
    public void setEchoObject2Result(JAXBElement<ClassObj> value) {
        this.echoObject2Result = ((JAXBElement<ClassObj> ) value);
    }

}
