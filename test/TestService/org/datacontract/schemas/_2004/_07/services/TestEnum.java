
package org.datacontract.schemas._2004._07.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TestEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TestEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="e0"/>
 *     &lt;enumeration value="e1"/>
 *     &lt;enumeration value="e2"/>
 *     &lt;enumeration value="e3"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TestEnum")
@XmlEnum
public enum TestEnum {

    @XmlEnumValue("e0")
    E_0("e0"),
    @XmlEnumValue("e1")
    E_1("e1"),
    @XmlEnumValue("e2")
    E_2("e2"),
    @XmlEnumValue("e3")
    E_3("e3");
    private final String value;

    TestEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TestEnum fromValue(String v) {
        for (TestEnum c: TestEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
