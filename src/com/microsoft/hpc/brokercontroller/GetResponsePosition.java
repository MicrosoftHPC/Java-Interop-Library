
package com.microsoft.hpc.brokercontroller;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetResponsePosition.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GetResponsePosition">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Begin"/>
 *     &lt;enumeration value="Current"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GetResponsePosition")
@XmlEnum
public enum GetResponsePosition {

    @XmlEnumValue("Begin")
    BEGIN("Begin"),
    @XmlEnumValue("Current")
    CURRENT("Current");
    private final String value;

    GetResponsePosition(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GetResponsePosition fromValue(String v) {
        for (GetResponsePosition c: GetResponsePosition.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
