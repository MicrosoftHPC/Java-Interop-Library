
package com.microsoft.hpc.soadiagservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.hpc.ArrayOfTraceEventItem;


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
 *         &lt;element name="QuerySessionTraceResult" type="{http://hpc.microsoft.com}ArrayOfTraceEventItem" minOccurs="0"/>
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
    "querySessionTraceResult"
})
@XmlRootElement(name = "QuerySessionTraceResponse")
public class QuerySessionTraceResponse {

    @XmlElementRef(name = "QuerySessionTraceResult", namespace = "http://hpc.microsoft.com/SoaDiagService/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfTraceEventItem> querySessionTraceResult;

    /**
     * Gets the value of the querySessionTraceResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTraceEventItem }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTraceEventItem> getQuerySessionTraceResult() {
        return querySessionTraceResult;
    }

    /**
     * Sets the value of the querySessionTraceResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTraceEventItem }{@code >}
     *     
     */
    public void setQuerySessionTraceResult(JAXBElement<ArrayOfTraceEventItem> value) {
        this.querySessionTraceResult = ((JAXBElement<ArrayOfTraceEventItem> ) value);
    }

}
