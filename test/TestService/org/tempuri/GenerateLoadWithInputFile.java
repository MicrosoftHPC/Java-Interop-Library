
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
 *         &lt;element name="refID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="millisec" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="input_data_path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="common_data_path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "millisec",
    "inputDataPath",
    "commonDataPath"
})
@XmlRootElement(name = "GenerateLoadWithInputFile")
public class GenerateLoadWithInputFile {

    protected Integer refID;
    protected Long millisec;
    @XmlElementRef(name = "input_data_path", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> inputDataPath;
    @XmlElementRef(name = "common_data_path", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> commonDataPath;

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
     * Gets the value of the millisec property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMillisec() {
        return millisec;
    }

    /**
     * Sets the value of the millisec property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMillisec(Long value) {
        this.millisec = value;
    }

    /**
     * Gets the value of the inputDataPath property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInputDataPath() {
        return inputDataPath;
    }

    /**
     * Sets the value of the inputDataPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInputDataPath(JAXBElement<String> value) {
        this.inputDataPath = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the commonDataPath property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCommonDataPath() {
        return commonDataPath;
    }

    /**
     * Sets the value of the commonDataPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCommonDataPath(JAXBElement<String> value) {
        this.commonDataPath = ((JAXBElement<String> ) value);
    }

}
