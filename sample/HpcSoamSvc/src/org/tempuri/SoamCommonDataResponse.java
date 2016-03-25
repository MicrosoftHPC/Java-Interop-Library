package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SoamCommonDataResult" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "soamCommonDataResult"
})
@XmlRootElement(name = "SoamCommonDataResponse")
public class SoamCommonDataResponse {

    @XmlJavaTypeAdapter(DummyAdapterInteger.class)
    @XmlElement(name = "SoamCommonDataResult", namespace = "http://tempuri.org/")
    protected Integer soamCommonDataResult;

    /**
     * Gets the value of the soamCommonDataResult property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getSoamCommonDataResult() {
        return soamCommonDataResult;
    }

    /**
     * Sets the value of the soamCommonDataResult property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setSoamCommonDataResult(Integer value) {
        this.soamCommonDataResult = value;
    }

}
