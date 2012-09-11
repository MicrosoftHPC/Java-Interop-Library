
package com.microsoft.hpc.soadiagservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.microsoft.hpc.ArrayOfRequestData;
import com.microsoft.hpc.RequestContinuationToken;


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
 *         &lt;element name="QueryForRequestResult" type="{http://hpc.microsoft.com}ArrayOfRequestData" minOccurs="0"/>
 *         &lt;element name="token" type="{http://hpc.microsoft.com}RequestContinuationToken" minOccurs="0"/>
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
    "queryForRequestResult",
    "token"
})
@XmlRootElement(name = "QueryForRequestResponse")
public class QueryForRequestResponse {

    @XmlElementRef(name = "QueryForRequestResult", namespace = "http://hpc.microsoft.com/SoaDiagService/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfRequestData> queryForRequestResult;
    protected RequestContinuationToken token;

    /**
     * Gets the value of the queryForRequestResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRequestData }{@code >}
     *     
     */
    public JAXBElement<ArrayOfRequestData> getQueryForRequestResult() {
        return queryForRequestResult;
    }

    /**
     * Sets the value of the queryForRequestResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRequestData }{@code >}
     *     
     */
    public void setQueryForRequestResult(JAXBElement<ArrayOfRequestData> value) {
        this.queryForRequestResult = ((JAXBElement<ArrayOfRequestData> ) value);
    }

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link RequestContinuationToken }
     *     
     */
    public RequestContinuationToken getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestContinuationToken }
     *     
     */
    public void setToken(RequestContinuationToken value) {
        this.token = value;
    }

}
