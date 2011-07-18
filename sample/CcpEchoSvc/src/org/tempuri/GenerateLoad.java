
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="runMilliSeconds" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="dummyData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="fileData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "runMilliSeconds",
    "dummyData",
    "fileData"
})
@XmlRootElement(name = "GenerateLoad")
public class GenerateLoad {

    protected Double runMilliSeconds;
    @XmlElementRef(name = "dummyData", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> dummyData;
    @XmlElementRef(name = "fileData", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fileData;

    /**
     * Gets the value of the runMilliSeconds property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRunMilliSeconds() {
        return runMilliSeconds;
    }

    /**
     * Sets the value of the runMilliSeconds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRunMilliSeconds(Double value) {
        this.runMilliSeconds = value;
    }

    /**
     * Gets the value of the dummyData property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getDummyData() {
        return dummyData;
    }

    /**
     * Sets the value of the dummyData property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setDummyData(JAXBElement<byte[]> value) {
        this.dummyData = ((JAXBElement<byte[]> ) value);
    }

    /**
     * Gets the value of the fileData property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFileData() {
        return fileData;
    }

    /**
     * Sets the value of the fileData property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFileData(JAXBElement<String> value) {
        this.fileData = ((JAXBElement<String> ) value);
    }

}
