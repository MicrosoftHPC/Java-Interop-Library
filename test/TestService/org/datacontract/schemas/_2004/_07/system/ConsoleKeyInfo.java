
package org.datacontract.schemas._2004._07.system;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConsoleKeyInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsoleKeyInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_key" type="{http://schemas.datacontract.org/2004/07/System}ConsoleKey"/>
 *         &lt;element name="_keyChar" type="{http://schemas.microsoft.com/2003/10/Serialization/}char"/>
 *         &lt;element name="_mods" type="{http://schemas.datacontract.org/2004/07/System}ConsoleModifiers"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsoleKeyInfo", propOrder = {
    "key",
    "keyChar",
    "mods"
})
public class ConsoleKeyInfo {

    @XmlElement(name = "_key", required = true)
    protected ConsoleKey key;
    @XmlElement(name = "_keyChar")
    protected int keyChar;
    @XmlList
    @XmlElement(name = "_mods", required = true)
    protected List<String> mods;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link ConsoleKey }
     *     
     */
    public ConsoleKey getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsoleKey }
     *     
     */
    public void setKey(ConsoleKey value) {
        this.key = value;
    }

    /**
     * Gets the value of the keyChar property.
     * 
     */
    public int getKeyChar() {
        return keyChar;
    }

    /**
     * Sets the value of the keyChar property.
     * 
     */
    public void setKeyChar(int value) {
        this.keyChar = value;
    }

    /**
     * Gets the value of the mods property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mods property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMods().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMods() {
        if (mods == null) {
            mods = new ArrayList<String>();
        }
        return this.mods;
    }

}
