package org.tempuri;

import java.io.ByteArrayOutputStream;
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
 *         &lt;element name="SoamInput" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "soamInput"
})
@XmlRootElement(name = "SoamInvoke")
public class SoamInvoke{

    @XmlJavaTypeAdapter(DummyAdapterBytes.class)
    @XmlElementRef(name = "SoamInput", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> soamInput;

    /**
     * Gets the value of the soamInput property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *
     */
    public JAXBElement<byte[]> getSoamInput() {
        return soamInput;
    }

    /**
     * Sets the value of the soamInput property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *
     */
    public void setSoamInput(JAXBElement<byte[]> value) {
        this.soamInput = ((JAXBElement<byte[]>) value);
    }

    public void setSoamInputObject(Message input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            input.onSerialize(baos);

            ObjectFactory of = new ObjectFactory();
            this.soamInput = of.createSoamInvokeSoamInput(baos.toByteArray());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
