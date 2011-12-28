
package org.datacontract.schemas._2004._07.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfstringstring;


/**
 * <p>Java class for ComputerInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComputerInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="called" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="envs" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfKeyValueOfstringstring" minOccurs="0"/>
 *         &lt;element name="jobID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="onExitCalled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="refID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="runAsUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scheduler" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sub" type="{http://schemas.datacontract.org/2004/07/services}Sub" minOccurs="0"/>
 *         &lt;element name="taskID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ts" type="{http://schemas.datacontract.org/2004/07/services}TestStruct" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComputerInfo", propOrder = {
    "name",
    "called",
    "envs",
    "jobID",
    "onExitCalled",
    "refID",
    "runAsUser",
    "scheduler",
    "sub",
    "taskID",
    "ts"
})
public class ComputerInfo {

    @XmlElementRef(name = "Name", namespace = "http://schemas.datacontract.org/2004/07/services", type = JAXBElement.class, required = false)
    protected JAXBElement<String> name;
    @XmlSchemaType(name = "dateTime")
    @XmlElement(name="called", namespace="http://schemas.datacontract.org/2004/07/services")
    protected XMLGregorianCalendar called;
    @XmlElementRef(name = "envs", namespace = "http://schemas.datacontract.org/2004/07/services", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfKeyValueOfstringstring> envs;
    @XmlElement(name = "jobID", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Integer jobID;
    @XmlElement(name = "onExitCalled", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Boolean onExitCalled;
    @XmlElement(name = "refID", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Integer refID;
    @XmlElementRef(name = "runAsUser", namespace = "http://schemas.datacontract.org/2004/07/services", type = JAXBElement.class, required = false)
    protected JAXBElement<String> runAsUser;
    @XmlElementRef(name = "scheduler", namespace = "http://schemas.datacontract.org/2004/07/services", type = JAXBElement.class, required = false)
    protected JAXBElement<String> scheduler;
    @XmlElementRef(name = "sub", namespace = "http://schemas.datacontract.org/2004/07/services", type = JAXBElement.class, required = false)
    protected JAXBElement<Sub> sub;
    @XmlElement(name = "taskID", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected Integer taskID;
    @XmlElement(name = "ts", namespace = "http://schemas.datacontract.org/2004/07/services")
    protected TestStruct ts;

    public ComputerInfo()
    {
        ObjectFactory fact = new ObjectFactory();
        envs = fact.createComputerInfoEnvs(new com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfstringstring());
    }
        
    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setName(JAXBElement<String> value) {
        this.name = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the called property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalled() {
        return called;
    }

    /**
     * Sets the value of the called property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalled(XMLGregorianCalendar value) {
        this.called = value;
    }

    /**
     * Gets the value of the envs property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfstringstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfKeyValueOfstringstring> getEnvs() {
        return envs;
    }

    /**
     * Sets the value of the envs property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfstringstring }{@code >}
     *     
     */
    public void setEnvs(JAXBElement<ArrayOfKeyValueOfstringstring> value) {
        this.envs = ((JAXBElement<ArrayOfKeyValueOfstringstring> ) value);
    }

    /**
     * Gets the value of the jobID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJobID() {
        return jobID;
    }

    /**
     * Sets the value of the jobID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJobID(Integer value) {
        this.jobID = value;
    }

    /**
     * Gets the value of the onExitCalled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOnExitCalled() {
        return onExitCalled;
    }

    /**
     * Sets the value of the onExitCalled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOnExitCalled(Boolean value) {
        this.onExitCalled = value;
    }

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
     * Gets the value of the runAsUser property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRunAsUser() {
        return runAsUser;
    }

    /**
     * Sets the value of the runAsUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRunAsUser(JAXBElement<String> value) {
        this.runAsUser = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the scheduler property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getScheduler() {
        return scheduler;
    }

    /**
     * Sets the value of the scheduler property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setScheduler(JAXBElement<String> value) {
        this.scheduler = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the sub property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sub }{@code >}
     *     
     */
    public JAXBElement<Sub> getSub() {
        return sub;
    }

    /**
     * Sets the value of the sub property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sub }{@code >}
     *     
     */
    public void setSub(JAXBElement<Sub> value) {
        this.sub = ((JAXBElement<Sub> ) value);
    }

    /**
     * Gets the value of the taskID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTaskID() {
        return taskID;
    }

    /**
     * Sets the value of the taskID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTaskID(Integer value) {
        this.taskID = value;
    }

    /**
     * Gets the value of the ts property.
     * 
     * @return
     *     possible object is
     *     {@link TestStruct }
     *     
     */
    public TestStruct getTs() {
        return ts;
    }

    /**
     * Sets the value of the ts property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestStruct }
     *     
     */
    public void setTs(TestStruct value) {
        this.ts = value;
    }

}
