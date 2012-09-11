
package com.microsoft.hpc;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ResponseType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Null"/>
 *     &lt;enumeration value="CommunicationError"/>
 *     &lt;enumeration value="EndpointNotFound"/>
 *     &lt;enumeration value="Faulted"/>
 *     &lt;enumeration value="Succeeded"/>
 *     &lt;enumeration value="Incomplete"/>
 *     &lt;enumeration value="DispatcherClosed"/>
 *     &lt;enumeration value="FailedToSendRequest"/>
 *     &lt;enumeration value="RetryOperationError"/>
 *     &lt;enumeration value="SessionFault"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResponseType")
@XmlEnum
public enum ResponseType {

    @XmlEnumValue("Null")
    NULL("Null"),
    @XmlEnumValue("CommunicationError")
    COMMUNICATION_ERROR("CommunicationError"),
    @XmlEnumValue("EndpointNotFound")
    ENDPOINT_NOT_FOUND("EndpointNotFound"),
    @XmlEnumValue("Faulted")
    FAULTED("Faulted"),
    @XmlEnumValue("Succeeded")
    SUCCEEDED("Succeeded"),
    @XmlEnumValue("Incomplete")
    INCOMPLETE("Incomplete"),
    @XmlEnumValue("DispatcherClosed")
    DISPATCHER_CLOSED("DispatcherClosed"),
    @XmlEnumValue("FailedToSendRequest")
    FAILED_TO_SEND_REQUEST("FailedToSendRequest"),
    @XmlEnumValue("RetryOperationError")
    RETRY_OPERATION_ERROR("RetryOperationError"),
    @XmlEnumValue("SessionFault")
    SESSION_FAULT("SessionFault");
    private final String value;

    ResponseType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResponseType fromValue(String v) {
        for (ResponseType c: ResponseType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
