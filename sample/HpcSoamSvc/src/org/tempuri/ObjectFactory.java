package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.tempuri package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SoamInvokeSoamInput_QNAME = new QName("http://tempuri.org/", "SoamInput");
    private final static QName _SoamInvokeResponseSoamOutput_QNAME = new QName("http://tempuri.org/", "SoamOutput");
    private final static QName _SoamCommonDataDataClientId_QNAME = new QName("http://tempuri.org/", "DataClientId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: org.tempuri
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SoamInvokeResponse }
     *
     * @return
     */
    public SoamInvokeResponse createSoamInvokeResponse() {
        return new SoamInvokeResponse();
    }

    /**
     * Create an instance of {@link SoamInvoke }
     *
     * @return
     */
    public SoamInvoke createSoamInvoke() {
        return new SoamInvoke();
    }

    /**
     * Create an instance of {@link SoamCommonData }
     *
     */
    public SoamCommonData createSoamCommonData() {
        return new SoamCommonData();
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     *
     * @param value
     * @return
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "SoamInput", scope = SoamInvoke.class)
    public JAXBElement<byte[]> createSoamInvokeSoamInput(byte[] value) {
        return new JAXBElement<byte[]>(_SoamInvokeSoamInput_QNAME, byte[].class, SoamInvoke.class, ((byte[]) value));
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     *
     * @param value
     * @return
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "SoamOutput", scope = SoamInvokeResponse.class)
    public JAXBElement<byte[]> createSoamInvokeResponseSoamOutput(byte[] value) {
        return new JAXBElement<byte[]>(_SoamInvokeResponseSoamOutput_QNAME, byte[].class, SoamInvokeResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "DataClientId", scope = SoamCommonData.class)
    public JAXBElement<String> createSoamCommonDataDataClientId(String value) {
        return new JAXBElement<String>(_SoamCommonDataDataClientId_QNAME, String.class, SoamCommonData.class, value);
    }

}
