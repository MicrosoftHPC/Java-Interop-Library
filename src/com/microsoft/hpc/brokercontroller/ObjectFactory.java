
package com.microsoft.hpc.brokercontroller;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc.brokercontroller package. 
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

    private final static QName _FlushClientid_QNAME = new QName("http://hpc.microsoft.com/brokercontroller/", "clientid");
    private final static QName _GetBrokerClientStatusClientId_QNAME = new QName("http://hpc.microsoft.com/brokercontroller/", "clientId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc.brokercontroller
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BrokerResponseMessages }
     * 
     */
    public BrokerResponseMessages createBrokerResponseMessages() {
        return new BrokerResponseMessages();
    }

    /**
     * Create an instance of {@link GetRequestsCount }
     * 
     */
    public GetRequestsCount createGetRequestsCount() {
        return new GetRequestsCount();
    }

    /**
     * Create an instance of {@link PurgeResponse }
     * 
     */
    public PurgeResponse createPurgeResponse() {
        return new PurgeResponse();
    }

    /**
     * Create an instance of {@link Flush }
     * 
     */
    public Flush createFlush() {
        return new Flush();
    }

    /**
     * Create an instance of {@link GetBrokerClientStatusResponse }
     * 
     */
    public GetBrokerClientStatusResponse createGetBrokerClientStatusResponse() {
        return new GetBrokerClientStatusResponse();
    }

    /**
     * Create an instance of {@link Purge }
     * 
     */
    public Purge createPurge() {
        return new Purge();
    }

    /**
     * Create an instance of {@link PullResponses }
     * 
     */
    public PullResponses createPullResponses() {
        return new PullResponses();
    }

    /**
     * Create an instance of {@link FlushResponse }
     * 
     */
    public FlushResponse createFlushResponse() {
        return new FlushResponse();
    }

    /**
     * Create an instance of {@link GetRequestsCountResponse }
     * 
     */
    public GetRequestsCountResponse createGetRequestsCountResponse() {
        return new GetRequestsCountResponse();
    }

    /**
     * Create an instance of {@link PullResponsesResponse }
     * 
     */
    public PullResponsesResponse createPullResponsesResponse() {
        return new PullResponsesResponse();
    }

    /**
     * Create an instance of {@link EndRequestsResponse }
     * 
     */
    public EndRequestsResponse createEndRequestsResponse() {
        return new EndRequestsResponse();
    }

    /**
     * Create an instance of {@link EndRequests }
     * 
     */
    public EndRequests createEndRequests() {
        return new EndRequests();
    }

    /**
     * Create an instance of {@link GetBrokerClientStatus }
     * 
     */
    public GetBrokerClientStatus createGetBrokerClientStatus() {
        return new GetBrokerClientStatus();
    }

    /**
     * Create an instance of {@link BrokerResponseMessages.SOAPMessage }
     * 
     */
    public BrokerResponseMessages.SOAPMessage createBrokerResponseMessagesSOAPMessage() {
        return new BrokerResponseMessages.SOAPMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokercontroller/", name = "clientid", scope = Flush.class)
    public JAXBElement<String> createFlushClientid(String value) {
        return new JAXBElement<String>(_FlushClientid_QNAME, String.class, Flush.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokercontroller/", name = "clientid", scope = Purge.class)
    public JAXBElement<String> createPurgeClientid(String value) {
        return new JAXBElement<String>(_FlushClientid_QNAME, String.class, Purge.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokercontroller/", name = "clientid", scope = EndRequests.class)
    public JAXBElement<String> createEndRequestsClientid(String value) {
        return new JAXBElement<String>(_FlushClientid_QNAME, String.class, EndRequests.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokercontroller/", name = "clientId", scope = GetBrokerClientStatus.class)
    public JAXBElement<String> createGetBrokerClientStatusClientId(String value) {
        return new JAXBElement<String>(_GetBrokerClientStatusClientId_QNAME, String.class, GetBrokerClientStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokercontroller/", name = "clientId", scope = GetRequestsCount.class)
    public JAXBElement<String> createGetRequestsCountClientId(String value) {
        return new JAXBElement<String>(_GetBrokerClientStatusClientId_QNAME, String.class, GetRequestsCount.class, value);
    }

}
