
package com.microsoft.hpc.brokerlauncher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.hpc.SessionStartInfoContract;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfint;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc.brokerlauncher package. 
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

    private final static QName _BrokerInitializationResult_QNAME = new QName("http://hpc.microsoft.com/BrokerLauncher", "BrokerInitializationResult");
    private final static QName _AttachResponseAttachResult_QNAME = new QName("http://hpc.microsoft.com/brokerlauncher/", "AttachResult");
    private final static QName _BrokerInitializationResultBrokerEpr_QNAME = new QName("http://hpc.microsoft.com/BrokerLauncher", "BrokerEpr");
    private final static QName _BrokerInitializationResultControllerEpr_QNAME = new QName("http://hpc.microsoft.com/BrokerLauncher", "ControllerEpr");
    private final static QName _BrokerInitializationResultResponseEpr_QNAME = new QName("http://hpc.microsoft.com/BrokerLauncher", "ResponseEpr");
    private final static QName _CreateDurableResponseCreateDurableResult_QNAME = new QName("http://hpc.microsoft.com/brokerlauncher/", "CreateDurableResult");
    private final static QName _GetActiveBrokerIdListResponseGetActiveBrokerIdListResult_QNAME = new QName("http://hpc.microsoft.com/brokerlauncher/", "GetActiveBrokerIdListResult");
    private final static QName _CreateDurableInfo_QNAME = new QName("http://hpc.microsoft.com/brokerlauncher/", "info");
    private final static QName _CreateResponseCreateResult_QNAME = new QName("http://hpc.microsoft.com/brokerlauncher/", "CreateResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc.brokerlauncher
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BrokerInitializationResult }
     * 
     */
    public BrokerInitializationResult createBrokerInitializationResult() {
        return new BrokerInitializationResult();
    }

    /**
     * Create an instance of {@link Close }
     * 
     */
    public Close createClose() {
        return new Close();
    }

    /**
     * Create an instance of {@link CreateDurableResponse }
     * 
     */
    public CreateDurableResponse createCreateDurableResponse() {
        return new CreateDurableResponse();
    }

    /**
     * Create an instance of {@link PingBrokerResponse }
     * 
     */
    public PingBrokerResponse createPingBrokerResponse() {
        return new PingBrokerResponse();
    }

    /**
     * Create an instance of {@link PingBroker }
     * 
     */
    public PingBroker createPingBroker() {
        return new PingBroker();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link AttachResponse }
     * 
     */
    public AttachResponse createAttachResponse() {
        return new AttachResponse();
    }

    /**
     * Create an instance of {@link CreateDurable }
     * 
     */
    public CreateDurable createCreateDurable() {
        return new CreateDurable();
    }

    /**
     * Create an instance of {@link Attach }
     * 
     */
    public Attach createAttach() {
        return new Attach();
    }

    /**
     * Create an instance of {@link CloseResponse }
     * 
     */
    public CloseResponse createCloseResponse() {
        return new CloseResponse();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link GetActiveBrokerIdListResponse }
     * 
     */
    public GetActiveBrokerIdListResponse createGetActiveBrokerIdListResponse() {
        return new GetActiveBrokerIdListResponse();
    }

    /**
     * Create an instance of {@link GetActiveBrokerIdList }
     * 
     */
    public GetActiveBrokerIdList createGetActiveBrokerIdList() {
        return new GetActiveBrokerIdList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrokerInitializationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/BrokerLauncher", name = "BrokerInitializationResult")
    public JAXBElement<BrokerInitializationResult> createBrokerInitializationResult(BrokerInitializationResult value) {
        return new JAXBElement<BrokerInitializationResult>(_BrokerInitializationResult_QNAME, BrokerInitializationResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrokerInitializationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokerlauncher/", name = "AttachResult", scope = AttachResponse.class)
    public JAXBElement<BrokerInitializationResult> createAttachResponseAttachResult(BrokerInitializationResult value) {
        return new JAXBElement<BrokerInitializationResult>(_AttachResponseAttachResult_QNAME, BrokerInitializationResult.class, AttachResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/BrokerLauncher", name = "BrokerEpr", scope = BrokerInitializationResult.class)
    public JAXBElement<ArrayOfstring> createBrokerInitializationResultBrokerEpr(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_BrokerInitializationResultBrokerEpr_QNAME, ArrayOfstring.class, BrokerInitializationResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/BrokerLauncher", name = "ControllerEpr", scope = BrokerInitializationResult.class)
    public JAXBElement<ArrayOfstring> createBrokerInitializationResultControllerEpr(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_BrokerInitializationResultControllerEpr_QNAME, ArrayOfstring.class, BrokerInitializationResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/BrokerLauncher", name = "ResponseEpr", scope = BrokerInitializationResult.class)
    public JAXBElement<ArrayOfstring> createBrokerInitializationResultResponseEpr(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_BrokerInitializationResultResponseEpr_QNAME, ArrayOfstring.class, BrokerInitializationResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrokerInitializationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokerlauncher/", name = "CreateDurableResult", scope = CreateDurableResponse.class)
    public JAXBElement<BrokerInitializationResult> createCreateDurableResponseCreateDurableResult(BrokerInitializationResult value) {
        return new JAXBElement<BrokerInitializationResult>(_CreateDurableResponseCreateDurableResult_QNAME, BrokerInitializationResult.class, CreateDurableResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokerlauncher/", name = "GetActiveBrokerIdListResult", scope = GetActiveBrokerIdListResponse.class)
    public JAXBElement<ArrayOfint> createGetActiveBrokerIdListResponseGetActiveBrokerIdListResult(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_GetActiveBrokerIdListResponseGetActiveBrokerIdListResult_QNAME, ArrayOfint.class, GetActiveBrokerIdListResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionStartInfoContract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokerlauncher/", name = "info", scope = CreateDurable.class)
    public JAXBElement<SessionStartInfoContract> createCreateDurableInfo(SessionStartInfoContract value) {
        return new JAXBElement<SessionStartInfoContract>(_CreateDurableInfo_QNAME, SessionStartInfoContract.class, CreateDurable.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionStartInfoContract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokerlauncher/", name = "info", scope = Create.class)
    public JAXBElement<SessionStartInfoContract> createCreateInfo(SessionStartInfoContract value) {
        return new JAXBElement<SessionStartInfoContract>(_CreateDurableInfo_QNAME, SessionStartInfoContract.class, Create.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrokerInitializationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/brokerlauncher/", name = "CreateResult", scope = CreateResponse.class)
    public JAXBElement<BrokerInitializationResult> createCreateResponseCreateResult(BrokerInitializationResult value) {
        return new JAXBElement<BrokerInitializationResult>(_CreateResponseCreateResult_QNAME, BrokerInitializationResult.class, CreateResponse.class, value);
    }

}
