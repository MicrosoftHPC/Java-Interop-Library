
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


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
 *         &lt;element name="traceMsgs" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="sleepBeforeTrace" type="{http://schemas.microsoft.com/2003/10/Serialization/}duration" minOccurs="0"/>
 *         &lt;element name="sleepAfterTrace" type="{http://schemas.microsoft.com/2003/10/Serialization/}duration" minOccurs="0"/>
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
    "traceMsgs",
    "sleepBeforeTrace",
    "sleepAfterTrace",
    "testActionId"
})
@XmlRootElement(name = "Trace")
public class Trace {
    @XmlElement(name = "refID", namespace = "http://tempuri.org/")
    protected Integer refID;
    @XmlElementRef(name = "traceMsgs", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> traceMsgs;
    @XmlElement(name = "sleepBeforeTrace", namespace = "http://tempuri.org/")
    protected Duration sleepBeforeTrace;
    @XmlElement(name = "sleepAfterTrace", namespace = "http://tempuri.org/")
    protected Duration sleepAfterTrace;
    @XmlElement(name = "testActionId", namespace = "http://tempuri.org/")
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
     * Gets the value of the traceMsgs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getTraceMsgs() {
        return traceMsgs;
    }

    /**
     * Sets the value of the traceMsgs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setTraceMsgs(JAXBElement<ArrayOfstring> value) {
        this.traceMsgs = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the sleepBeforeTrace property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getSleepBeforeTrace() {
        return sleepBeforeTrace;
    }

    /**
     * Sets the value of the sleepBeforeTrace property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setSleepBeforeTrace(Duration value) {
        this.sleepBeforeTrace = value;
    }

    /**
     * Gets the value of the sleepAfterTrace property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getSleepAfterTrace() {
        return sleepAfterTrace;
    }

    /**
     * Sets the value of the sleepAfterTrace property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setSleepAfterTrace(Duration value) {
        this.sleepAfterTrace = value;
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
