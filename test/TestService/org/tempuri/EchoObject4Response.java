
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
 *         &lt;element name="EchoObject4Result" type="{http://schemas.datacontract.org/2004/07/services}ClassObj" minOccurs="0"/>
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
    "echoObject4Result"
})
@XmlRootElement(name = "EchoObject4Response")
public class EchoObject4Response {

    @XmlElementRef(name = "EchoObject4Result", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ClassObj> echoObject4Result;

    /**
     * Gets the value of the echoObject4Result property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ClassObj }{@code >}
     *     
     */
    public JAXBElement<ClassObj> getEchoObject4Result() {
        return echoObject4Result;
    }

    /**
     * Sets the value of the echoObject4Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ClassObj }{@code >}
     *     
     */
    public void setEchoObject4Result(JAXBElement<ClassObj> value) {
        this.echoObject4Result = ((JAXBElement<ClassObj> ) value);
    }

}
