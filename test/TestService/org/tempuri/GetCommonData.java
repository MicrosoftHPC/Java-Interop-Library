
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;


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
 *         &lt;element name="refID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sleepBeforeGet" type="{http://schemas.microsoft.com/2003/10/Serialization/}duration" minOccurs="0"/>
 *         &lt;element name="sleepAfterGet" type="{http://schemas.microsoft.com/2003/10/Serialization/}duration" minOccurs="0"/>
 *         &lt;element name="dataClientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expectedMd5Hash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="testActionId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "refID",
    "sleepBeforeGet",
    "sleepAfterGet",
    "dataClientId",
    "expectedMd5Hash",
    "testActionId"
})
@XmlRootElement(name = "GetCommonData")
public class GetCommonData {

    @XmlElement(name = "refID", namespace="http://tempuri.org/")
    protected Integer refID;
    @XmlElement(name = "sleepBeforeGet", namespace="http://tempuri.org/")
    protected Duration sleepBeforeGet;
    @XmlElement(name = "sleepAfterGet", namespace="http://tempuri.org/")
    protected Duration sleepAfterGet;
    @XmlElementRef(name = "dataClientId", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> dataClientId;
    @XmlElementRef(name = "expectedMd5Hash", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> expectedMd5Hash;
    @XmlElement(name = "testActionId", namespace="http://tempuri.org/")
    protected Integer testActionId;

    /**
     * Gets the value of the refID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRefID() {
        return refID;
    }

    /**
     * Sets the value of the refID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRefID(Integer value) {
        this.refID = value;
    }

    /**
     * Gets the value of the sleepBeforeGet property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getSleepBeforeGet() {
        return sleepBeforeGet;
    }

    /**
     * Sets the value of the sleepBeforeGet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setSleepBeforeGet(Duration value) {
        this.sleepBeforeGet = value;
    }

    /**
     * Gets the value of the sleepAfterGet property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getSleepAfterGet() {
        return sleepAfterGet;
    }

    /**
     * Sets the value of the sleepAfterGet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setSleepAfterGet(Duration value) {
        this.sleepAfterGet = value;
    }

    /**
     * Gets the value of the dataClientId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDataClientId() {
        return dataClientId;
    }

    /**
     * Sets the value of the dataClientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDataClientId(JAXBElement<String> value) {
        this.dataClientId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the expectedMd5Hash property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExpectedMd5Hash() {
        return expectedMd5Hash;
    }

    /**
     * Sets the value of the expectedMd5Hash property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExpectedMd5Hash(JAXBElement<String> value) {
        this.expectedMd5Hash = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the testActionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTestActionId() {
        return testActionId;
    }

    /**
     * Sets the value of the testActionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTestActionId(Integer value) {
        this.testActionId = value;
    }

}
