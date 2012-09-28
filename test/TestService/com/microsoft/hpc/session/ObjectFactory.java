
package com.microsoft.hpc.session;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


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

    private final static QName _RetryOperationError_QNAME = new QName("http://hpc.microsoft.com/session/", "RetryOperationError");
    private final static QName _AuthenticationFailure_QNAME = new QName("http://hpc.microsoft.com/session/", "AuthenticationFailure");
    private final static QName _RetryOperationErrorReason_QNAME = new QName("http://hpc.microsoft.com/session/", "reason");
    private final static QName _AuthenticationFailureUserName_QNAME = new QName("http://hpc.microsoft.com/session/", "userName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc.session
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthenticationFailure }
     * 
     */
    public AuthenticationFailure createAuthenticationFailure() {
        return new AuthenticationFailure();
    }

    /**
     * Create an instance of {@link RetryOperationError }
     * 
     */
    public RetryOperationError createRetryOperationError() {
        return new RetryOperationError();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetryOperationError }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "RetryOperationError")
    public JAXBElement<RetryOperationError> createRetryOperationError(RetryOperationError value) {
        return new JAXBElement<RetryOperationError>(_RetryOperationError_QNAME, RetryOperationError.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticationFailure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "AuthenticationFailure")
    public JAXBElement<AuthenticationFailure> createAuthenticationFailure(AuthenticationFailure value) {
        return new JAXBElement<AuthenticationFailure>(_AuthenticationFailure_QNAME, AuthenticationFailure.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "reason", scope = RetryOperationError.class)
    public JAXBElement<String> createRetryOperationErrorReason(String value) {
        return new JAXBElement<String>(_RetryOperationErrorReason_QNAME, String.class, RetryOperationError.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "userName", scope = AuthenticationFailure.class)
    public JAXBElement<String> createAuthenticationFailureUserName(String value) {
        return new JAXBElement<String>(_AuthenticationFailureUserName_QNAME, String.class, AuthenticationFailure.class, value);
    }

}
