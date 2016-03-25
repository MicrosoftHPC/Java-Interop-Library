package org.tempuri;

import java.io.ByteArrayInputStream;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.hpc.soam.Message;
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
 *         &lt;element name="SoamOutput" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "soamOutput"
})
@XmlRootElement(name = "SoamInvokeResponse")
public class SoamInvokeResponse {

    @XmlJavaTypeAdapter(DummyAdapterBytes.class)
    @XmlElementRef(name = "SoamOutput", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> soamOutput;

    /**
     * Gets the value of the soamOutput property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link byte[] }{@code >}
     *
     */
    public JAXBElement<byte[]> getSoamOutput() {
        return soamOutput;
    }

    public void getSoamOutputObject(Message output) {
        try {
            output.onDeserialize(new ByteArrayInputStream(soamOutput.getValue()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Sets the value of the soamOutput property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link byte[] }{@code >}
     *
     */
    public void setSoamOutput(JAXBElement<byte[]> value) {
        this.soamOutput = ((JAXBElement<byte[]>) value);
    }

}
