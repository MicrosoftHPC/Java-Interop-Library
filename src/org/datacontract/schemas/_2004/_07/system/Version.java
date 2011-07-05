
package org.datacontract.schemas._2004._07.system;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Version complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Version">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_Build" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_Major" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_Minor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_Revision" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Version", propOrder = {
    "build",
    "major",
    "minor",
    "revision"
})
public class Version {

    @XmlElement(name = "_Build")
    protected int build;
    @XmlElement(name = "_Major")
    protected int major;
    @XmlElement(name = "_Minor")
    protected int minor;
    @XmlElement(name = "_Revision")
    protected int revision;

    /**
     * Gets the value of the build property.
     * 
     */
    public int getBuild() {
        return build;
    }

    /**
     * Sets the value of the build property.
     * 
     */
    public void setBuild(int value) {
        this.build = value;
    }

    /**
     * Gets the value of the major property.
     * 
     */
    public int getMajor() {
        return major;
    }

    /**
     * Sets the value of the major property.
     * 
     */
    public void setMajor(int value) {
        this.major = value;
    }

    /**
     * Gets the value of the minor property.
     * 
     */
    public int getMinor() {
        return minor;
    }

    /**
     * Sets the value of the minor property.
     * 
     */
    public void setMinor(int value) {
        this.minor = value;
    }

    /**
     * Gets the value of the revision property.
     * 
     */
    public int getRevision() {
        return revision;
    }

    /**
     * Sets the value of the revision property.
     * 
     */
    public void setRevision(int value) {
        this.revision = value;
    }

}
