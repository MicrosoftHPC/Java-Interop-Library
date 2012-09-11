
package com.microsoft.hpc;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RequestState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Null"/>
 *     &lt;enumeration value="Incoming"/>
 *     &lt;enumeration value="Calculating"/>
 *     &lt;enumeration value="Succeeded"/>
 *     &lt;enumeration value="Failed"/>
 *     &lt;enumeration value="FailedToAuthenticateRequest"/>
 *     &lt;enumeration value="FailedToSendResponse"/>
 *     &lt;enumeration value="ClientIdInvalid"/>
 *     &lt;enumeration value="ClientIdMismatch"/>
 *     &lt;enumeration value="ClientStateInvalid"/>
 *     &lt;enumeration value="FrontendGeneralError"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RequestState")
@XmlEnum
public enum RequestState {

    @XmlEnumValue("Null")
    NULL("Null"),
    @XmlEnumValue("Incoming")
    INCOMING("Incoming"),
    @XmlEnumValue("Calculating")
    CALCULATING("Calculating"),
    @XmlEnumValue("Succeeded")
    SUCCEEDED("Succeeded"),
    @XmlEnumValue("Failed")
    FAILED("Failed"),
    @XmlEnumValue("FailedToAuthenticateRequest")
    FAILED_TO_AUTHENTICATE_REQUEST("FailedToAuthenticateRequest"),
    @XmlEnumValue("FailedToSendResponse")
    FAILED_TO_SEND_RESPONSE("FailedToSendResponse"),
    @XmlEnumValue("ClientIdInvalid")
    CLIENT_ID_INVALID("ClientIdInvalid"),
    @XmlEnumValue("ClientIdMismatch")
    CLIENT_ID_MISMATCH("ClientIdMismatch"),
    @XmlEnumValue("ClientStateInvalid")
    CLIENT_STATE_INVALID("ClientStateInvalid"),
    @XmlEnumValue("FrontendGeneralError")
    FRONTEND_GENERAL_ERROR("FrontendGeneralError");
    private final String value;

    RequestState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RequestState fromValue(String v) {
        for (RequestState c: RequestState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
