
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="runTime" type="{http://schemas.microsoft.com/2003/10/Serialization/}duration" minOccurs="0"/>
 *         &lt;element name="exitDelay" type="{http://schemas.microsoft.com/2003/10/Serialization/}duration" minOccurs="0"/>
 *         &lt;element name="logPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "runTime",
    "exitDelay",
    "logPath"
})
@XmlRootElement(name = "EchoWithOnExit")
public class EchoWithOnExit {

    @XmlElement(name = "refID", namespace = "http://tempuri.org/")
    protected Integer refID;
    @XmlElement(name = "runTime", namespace = "http://tempuri.org/")
    protected Duration runTime;
    @XmlElement(name = "exitDelay", namespace = "http://tempuri.org/")
    protected Duration exitDelay;
    @XmlElementRef(name = "logPath", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> logPath;

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
     * Gets the value of the runTime property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getRunTime() {
        return runTime;
    }

    /**
     * Sets the value of the runTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setRunTime(Duration value) {
        this.runTime = value;
    }

    /**
     * Gets the value of the exitDelay property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getExitDelay() {
        return exitDelay;
    }

    /**
     * Sets the value of the exitDelay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setExitDelay(Duration value) {
        this.exitDelay = value;
    }

    /**
     * Gets the value of the logPath property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLogPath() {
        return logPath;
    }

    /**
     * Sets the value of the logPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLogPath(JAXBElement<String> value) {
        this.logPath = ((JAXBElement<String> ) value);
    }

}
