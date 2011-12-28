
package org.datacontract.schemas._2004._07.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sub complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sub">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sub_e" type="{http://schemas.datacontract.org/2004/07/services}TestEnum" minOccurs="0"/>
 *         &lt;element name="sub_f" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="sub_i" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sub_s" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sub", propOrder = {
    "subE",
    "subF",
    "subI",
    "subS"
})
public class Sub {

    @XmlElement(name = "sub_e", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected TestEnum subE;
    @XmlElement(name = "sub_f", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Float subF;
    @XmlElement(name = "sub_i", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Integer subI;
    @XmlElementRef(name = "sub_s", namespace = "http://schemas.datacontract.org/2004/07/services", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subS;

    /**
     * Gets the value of the subE property.
     * 
     * @return
     *     possible object is
     *     {@link TestEnum }
     *     
     */
    public TestEnum getSubE() {
        return subE;
    }

    /**
     * Sets the value of the subE property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestEnum }
     *     
     */
    public void setSubE(TestEnum value) {
        this.subE = value;
    }

    /**
     * Gets the value of the subF property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getSubF() {
        return subF;
    }

    /**
     * Sets the value of the subF property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setSubF(Float value) {
        this.subF = value;
    }

    /**
     * Gets the value of the subI property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSubI() {
        return subI;
    }

    /**
     * Sets the value of the subI property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSubI(Integer value) {
        this.subI = value;
    }

    /**
     * Gets the value of the subS property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubS() {
        return subS;
    }

    /**
     * Sets the value of the subS property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubS(JAXBElement<String> value) {
        this.subS = ((JAXBElement<String> ) value);
    }

}
