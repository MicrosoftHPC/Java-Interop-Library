
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
 *         &lt;element name="selectedNode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="delayOnSelectedNode" type="{http://schemas.microsoft.com/2003/10/Serialization/}duration" minOccurs="0"/>
 *         &lt;element name="delayOnOtherNodes" type="{http://schemas.microsoft.com/2003/10/Serialization/}duration" minOccurs="0"/>
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
    "selectedNode",
    "delayOnSelectedNode",
    "delayOnOtherNodes"
})
@XmlRootElement(name = "EchoWithDelayOnSelectedNode")
public class EchoWithDelayOnSelectedNode {

    @XmlElement(name = "refID", namespace = "http://tempuri.org/")
    protected Integer refID;
    @XmlElementRef(name = "selectedNode", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> selectedNode;
    @XmlElement(name = "delayOnSelectedNode", namespace = "http://tempuri.org/")
    protected Duration delayOnSelectedNode;
    @XmlElement(name = "delayOnOtherNodes", namespace = "http://tempuri.org/")
    protected Duration delayOnOtherNodes;

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
     * Gets the value of the selectedNode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSelectedNode() {
        return selectedNode;
    }

    /**
     * Sets the value of the selectedNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSelectedNode(JAXBElement<String> value) {
        this.selectedNode = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the delayOnSelectedNode property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getDelayOnSelectedNode() {
        return delayOnSelectedNode;
    }

    /**
     * Sets the value of the delayOnSelectedNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setDelayOnSelectedNode(Duration value) {
        this.delayOnSelectedNode = value;
    }

    /**
     * Gets the value of the delayOnOtherNodes property.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getDelayOnOtherNodes() {
        return delayOnOtherNodes;
    }

    /**
     * Sets the value of the delayOnOtherNodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setDelayOnOtherNodes(Duration value) {
        this.delayOnOtherNodes = value;
    }

}
