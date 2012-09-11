
package com.microsoft.hpc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


/**
 * <p>Java class for RequestData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Context" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/>
 *         &lt;element name="DispatchHistory" type="{http://hpc.microsoft.com}ArrayOfRequestFootprint" minOccurs="0"/>
 *         &lt;element name="RequestId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/>
 *         &lt;element name="RequestReceivedTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ResponseSentTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="State" type="{http://hpc.microsoft.com}RequestState" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestData", propOrder = {
    "context",
    "dispatchHistory",
    "requestId",
    "requestReceivedTime",
    "responseSentTime",
    "state"
})
public class RequestData {

    @XmlElementRef(name = "Context", namespace = "http://hpc.microsoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> context;
    @XmlElementRef(name = "DispatchHistory", namespace = "http://hpc.microsoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfRequestFootprint> dispatchHistory;
    @XmlElement(name = "RequestId")
    protected String requestId;
    @XmlElement(name = "RequestReceivedTime")
    protected Long requestReceivedTime;
    @XmlElement(name = "ResponseSentTime")
    protected Long responseSentTime;
    @XmlElement(name = "State")
    protected RequestState state;

    /**
     * Gets the value of the context property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getContext() {
        return context;
    }

    /**
     * Sets the value of the context property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setContext(JAXBElement<ArrayOfstring> value) {
        this.context = ((JAXBElement<ArrayOfstring> ) value);
    }

    /**
     * Gets the value of the dispatchHistory property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRequestFootprint }{@code >}
     *     
     */
    public JAXBElement<ArrayOfRequestFootprint> getDispatchHistory() {
        return dispatchHistory;
    }

    /**
     * Sets the value of the dispatchHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRequestFootprint }{@code >}
     *     
     */
    public void setDispatchHistory(JAXBElement<ArrayOfRequestFootprint> value) {
        this.dispatchHistory = ((JAXBElement<ArrayOfRequestFootprint> ) value);
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the requestReceivedTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRequestReceivedTime() {
        return requestReceivedTime;
    }

    /**
     * Sets the value of the requestReceivedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRequestReceivedTime(Long value) {
        this.requestReceivedTime = value;
    }

    /**
     * Gets the value of the responseSentTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getResponseSentTime() {
        return responseSentTime;
    }

    /**
     * Sets the value of the responseSentTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setResponseSentTime(Long value) {
        this.responseSentTime = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link RequestState }
     *     
     */
    public RequestState getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestState }
     *     
     */
    public void setState(RequestState value) {
        this.state = value;
    }

}
