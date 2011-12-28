
package org.datacontract.schemas._2004._07.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TestStruct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TestStruct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="d" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="e" type="{http://schemas.datacontract.org/2004/07/services}TestEnum" minOccurs="0"/>
 *         &lt;element name="f" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="i32_1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="i32_2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="i64" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="s" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestStruct", propOrder = {
    "d",
    "e",
    "f",
    "i321",
    "i322",
    "i64",
    "s"
})
public class TestStruct {

    @XmlElement(name = "d", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Double d;
    @XmlElement(name = "e", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected TestEnum e;
    @XmlElement(name = "f", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Float f;
    @XmlElement(name = "i32_1", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Integer i321;
    @XmlElement(name = "i32_2", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Integer i322;
    @XmlElement(name = "i64", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Long i64;
    @XmlElementRef(name = "s", namespace = "http://schemas.datacontract.org/2004/07/services", type = JAXBElement.class, required = false)
    protected JAXBElement<String> s;

    /**
     * Gets the value of the d property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getD() {
        return d;
    }

    /**
     * Sets the value of the d property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setD(Double value) {
        this.d = value;
    }

    /**
     * Gets the value of the e property.
     * 
     * @return
     *     possible object is
     *     {@link TestEnum }
     *     
     */
    public TestEnum getE() {
        return e;
    }

    /**
     * Sets the value of the e property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestEnum }
     *     
     */
    public void setE(TestEnum value) {
        this.e = value;
    }

    /**
     * Gets the value of the f property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getF() {
        return f;
    }

    /**
     * Sets the value of the f property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setF(Float value) {
        this.f = value;
    }

    /**
     * Gets the value of the i321 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getI321() {
        return i321;
    }

    /**
     * Sets the value of the i321 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setI321(Integer value) {
        this.i321 = value;
    }

    /**
     * Gets the value of the i322 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getI322() {
        return i322;
    }

    /**
     * Sets the value of the i322 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setI322(Integer value) {
        this.i322 = value;
    }

    /**
     * Gets the value of the i64 property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getI64() {
        return i64;
    }

    /**
     * Sets the value of the i64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setI64(Long value) {
        this.i64 = value;
    }

    /**
     * Gets the value of the s property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getS() {
        return s;
    }

    /**
     * Sets the value of the s property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setS(JAXBElement<String> value) {
        this.s = ((JAXBElement<String> ) value);
    }

}
