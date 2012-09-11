
package com.microsoft.hpc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestFootprint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestFootprint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DispatchId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/>
 *         &lt;element name="DispatchTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ExceptionDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GeneratedFaultReply" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ResponseTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ResponseType" type="{http://hpc.microsoft.com}ResponseType" minOccurs="0"/>
 *         &lt;element name="TargetMachine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaskId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="UserTraces" type="{http://hpc.microsoft.com}ArrayOfTraceEventItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestFootprint", propOrder = {
    "dispatchId",
    "dispatchTime",
    "exceptionDetail",
    "generatedFaultReply",
    "responseTime",
    "responseType",
    "targetMachine",
    "taskId",
    "userTraces"
})
public class RequestFootprint {

    @XmlElement(name = "DispatchId")
    protected String dispatchId;
    @XmlElement(name = "DispatchTime")
    protected Long dispatchTime;
    @XmlElementRef(name = "ExceptionDetail", namespace = "http://hpc.microsoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> exceptionDetail;
    @XmlElement(name = "GeneratedFaultReply")
    protected Boolean generatedFaultReply;
    @XmlElement(name = "ResponseTime")
    protected Long responseTime;
    @XmlElement(name = "ResponseType")
    protected ResponseType responseType;
    @XmlElementRef(name = "TargetMachine", namespace = "http://hpc.microsoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> targetMachine;
    @XmlElement(name = "TaskId")
    protected Integer taskId;
    @XmlElementRef(name = "UserTraces", namespace = "http://hpc.microsoft.com", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfTraceEventItem> userTraces;

    /**
     * Gets the value of the dispatchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispatchId() {
        return dispatchId;
    }

    /**
     * Sets the value of the dispatchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispatchId(String value) {
        this.dispatchId = value;
    }

    /**
     * Gets the value of the dispatchTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDispatchTime() {
        return dispatchTime;
    }

    /**
     * Sets the value of the dispatchTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDispatchTime(Long value) {
        this.dispatchTime = value;
    }

    /**
     * Gets the value of the exceptionDetail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExceptionDetail() {
        return exceptionDetail;
    }

    /**
     * Sets the value of the exceptionDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExceptionDetail(JAXBElement<String> value) {
        this.exceptionDetail = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the generatedFaultReply property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGeneratedFaultReply() {
        return generatedFaultReply;
    }

    /**
     * Sets the value of the generatedFaultReply property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGeneratedFaultReply(Boolean value) {
        this.generatedFaultReply = value;
    }

    /**
     * Gets the value of the responseTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getResponseTime() {
        return responseTime;
    }

    /**
     * Sets the value of the responseTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setResponseTime(Long value) {
        this.responseTime = value;
    }

    /**
     * Gets the value of the responseType property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseType }
     *     
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * Sets the value of the responseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseType }
     *     
     */
    public void setResponseType(ResponseType value) {
        this.responseType = value;
    }

    /**
     * Gets the value of the targetMachine property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTargetMachine() {
        return targetMachine;
    }

    /**
     * Sets the value of the targetMachine property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTargetMachine(JAXBElement<String> value) {
        this.targetMachine = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the taskId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * Sets the value of the taskId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTaskId(Integer value) {
        this.taskId = value;
    }

    /**
     * Gets the value of the userTraces property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTraceEventItem }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTraceEventItem> getUserTraces() {
        return userTraces;
    }

    /**
     * Sets the value of the userTraces property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTraceEventItem }{@code >}
     *     
     */
    public void setUserTraces(JAXBElement<ArrayOfTraceEventItem> value) {
        this.userTraces = ((JAXBElement<ArrayOfTraceEventItem> ) value);
    }

}
