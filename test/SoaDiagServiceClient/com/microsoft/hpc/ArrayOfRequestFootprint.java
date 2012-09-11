
package com.microsoft.hpc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRequestFootprint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRequestFootprint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestFootprint" type="{http://hpc.microsoft.com}RequestFootprint" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRequestFootprint", propOrder = {
    "requestFootprint"
})
public class ArrayOfRequestFootprint {

    @XmlElement(name = "RequestFootprint", nillable = true)
    protected List<RequestFootprint> requestFootprint;

    /**
     * Gets the value of the requestFootprint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requestFootprint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequestFootprint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RequestFootprint }
     * 
     * 
     */
    public List<RequestFootprint> getRequestFootprint() {
        if (requestFootprint == null) {
            requestFootprint = new ArrayList<RequestFootprint>();
        }
        return this.requestFootprint;
    }

}
