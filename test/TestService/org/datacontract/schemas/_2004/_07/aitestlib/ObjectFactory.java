
package org.datacontract.schemas._2004._07.aitestlib;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.aitestlib package. 
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

    private final static QName _CommonDataError_QNAME = new QName("http://schemas.datacontract.org/2004/07/AITestLib.Helper", "CommonDataError");
    private final static QName _CommonDataErrorReason_QNAME = new QName("http://schemas.datacontract.org/2004/07/AITestLib.Helper", "Reason");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.aitestlib
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CommonDataError }
     * 
     */
    public CommonDataError createCommonDataError() {
        return new CommonDataError();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommonDataError }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AITestLib.Helper", name = "CommonDataError")
    public JAXBElement<CommonDataError> createCommonDataError(CommonDataError value) {
        return new JAXBElement<CommonDataError>(_CommonDataError_QNAME, CommonDataError.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/AITestLib.Helper", name = "Reason", scope = CommonDataError.class)
    public JAXBElement<String> createCommonDataErrorReason(String value) {
        return new JAXBElement<String>(_CommonDataErrorReason_QNAME, String.class, CommonDataError.class, value);
    }

}
