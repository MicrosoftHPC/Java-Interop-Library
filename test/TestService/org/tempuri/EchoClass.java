
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.services.ClassFoo;


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
 *         &lt;element name="cls" type="{http://schemas.datacontract.org/2004/07/services}ClassFoo" minOccurs="0"/>
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
    "cls"
})
@XmlRootElement(name = "EchoClass")
public class EchoClass {

    @XmlElementRef(name = "cls", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ClassFoo> cls;

    /**
     * Gets the value of the cls property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ClassFoo }{@code >}
     *     
     */
    public JAXBElement<ClassFoo> getCls() {
        return cls;
    }

    /**
     * Sets the value of the cls property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ClassFoo }{@code >}
     *     
     */
    public void setCls(JAXBElement<ClassFoo> value) {
        this.cls = ((JAXBElement<ClassFoo> ) value);
    }

}
