
package com.microsoft.hpc.session;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfanyType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc.session package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SessionFault_QNAME = new QName("http://hpc.microsoft.com/session/", "SessionFault");
    private final static QName _SessionFaultReason_QNAME = new QName("http://hpc.microsoft.com/session/", "Reason");
    private final static QName _SessionFaultContext_QNAME = new QName("http://hpc.microsoft.com/session/", "Context");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc.session
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SessionFault }
     * 
     */
    public SessionFault createSessionFault() {
        return new SessionFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "SessionFault")
    public JAXBElement<SessionFault> createSessionFault(SessionFault value) {
        return new JAXBElement<SessionFault>(_SessionFault_QNAME, SessionFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "Reason", scope = SessionFault.class)
    public JAXBElement<String> createSessionFaultReason(String value) {
        return new JAXBElement<String>(_SessionFaultReason_QNAME, String.class, SessionFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "Context", scope = SessionFault.class)
    public JAXBElement<ArrayOfanyType> createSessionFaultContext(ArrayOfanyType value) {
        return new JAXBElement<ArrayOfanyType>(_SessionFaultContext_QNAME, ArrayOfanyType.class, SessionFault.class, value);
    }

}
