
package com.microsoft.hpc.genericservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc.genericservice package. 
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

    private final static QName _GenericOperationArgs_QNAME = new QName("http://hpc.microsoft.com/GenericService", "args");
    private final static QName _GenericOperationResponseGenericOperationResult_QNAME = new QName("http://hpc.microsoft.com/GenericService", "GenericOperationResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc.genericservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GenericOperationResponse }
     * 
     */
    public GenericOperationResponse createGenericOperationResponse() {
        return new GenericOperationResponse();
    }

    /**
     * Create an instance of {@link GenericOperation }
     * 
     */
    public GenericOperation createGenericOperation() {
        return new GenericOperation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/GenericService", name = "args", scope = GenericOperation.class)
    public JAXBElement<String> createGenericOperationArgs(String value) {
        return new JAXBElement<String>(_GenericOperationArgs_QNAME, String.class, GenericOperation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/GenericService", name = "GenericOperationResult", scope = GenericOperationResponse.class)
    public JAXBElement<String> createGenericOperationResponseGenericOperationResult(String value) {
        return new JAXBElement<String>(_GenericOperationResponseGenericOperationResult_QNAME, String.class, GenericOperationResponse.class, value);
    }

}
