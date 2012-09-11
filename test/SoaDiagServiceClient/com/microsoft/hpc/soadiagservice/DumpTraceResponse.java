
package com.microsoft.hpc.soadiagservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="DumpTraceResult" type="{http://schemas.microsoft.com/Message}StreamBody"/>
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
    "dumpTraceResult"
})
@XmlRootElement(name = "DumpTraceResponse")
public class DumpTraceResponse {

    @XmlElement(name = "DumpTraceResult", required = true)
    protected byte[] dumpTraceResult;

    /**
     * Gets the value of the dumpTraceResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDumpTraceResult() {
        return dumpTraceResult;
    }

    /**
     * Sets the value of the dumpTraceResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDumpTraceResult(byte[] value) {
        this.dumpTraceResult = ((byte[]) value);
    }

}
