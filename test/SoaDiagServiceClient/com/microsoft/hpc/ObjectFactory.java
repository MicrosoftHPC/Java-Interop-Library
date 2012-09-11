
package com.microsoft.hpc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc package. 
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

    private final static QName _TraceEventItem_QNAME = new QName("http://hpc.microsoft.com", "TraceEventItem");
    private final static QName _ArrayOfRequestFootprint_QNAME = new QName("http://hpc.microsoft.com", "ArrayOfRequestFootprint");
    private final static QName _ArrayOfTraceEventItem_QNAME = new QName("http://hpc.microsoft.com", "ArrayOfTraceEventItem");
    private final static QName _RequestFootprint_QNAME = new QName("http://hpc.microsoft.com", "RequestFootprint");
    private final static QName _ResponseType_QNAME = new QName("http://hpc.microsoft.com", "ResponseType");
    private final static QName _ArrayOfRequestData_QNAME = new QName("http://hpc.microsoft.com", "ArrayOfRequestData");
    private final static QName _RequestData_QNAME = new QName("http://hpc.microsoft.com", "RequestData");
    private final static QName _RequestContinuationToken_QNAME = new QName("http://hpc.microsoft.com", "RequestContinuationToken");
    private final static QName _RequestState_QNAME = new QName("http://hpc.microsoft.com", "RequestState");
    private final static QName _TraceEventItemData_QNAME = new QName("http://hpc.microsoft.com", "Data");
    private final static QName _RequestFootprintUserTraces_QNAME = new QName("http://hpc.microsoft.com", "UserTraces");
    private final static QName _RequestFootprintTargetMachine_QNAME = new QName("http://hpc.microsoft.com", "TargetMachine");
    private final static QName _RequestFootprintExceptionDetail_QNAME = new QName("http://hpc.microsoft.com", "ExceptionDetail");
    private final static QName _RequestDataDispatchHistory_QNAME = new QName("http://hpc.microsoft.com", "DispatchHistory");
    private final static QName _RequestDataContext_QNAME = new QName("http://hpc.microsoft.com", "Context");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfTraceEventItem }
     * 
     */
    public ArrayOfTraceEventItem createArrayOfTraceEventItem() {
        return new ArrayOfTraceEventItem();
    }

    /**
     * Create an instance of {@link RequestData }
     * 
     */
    public RequestData createRequestData() {
        return new RequestData();
    }

    /**
     * Create an instance of {@link ArrayOfRequestData }
     * 
     */
    public ArrayOfRequestData createArrayOfRequestData() {
        return new ArrayOfRequestData();
    }

    /**
     * Create an instance of {@link RequestFootprint }
     * 
     */
    public RequestFootprint createRequestFootprint() {
        return new RequestFootprint();
    }

    /**
     * Create an instance of {@link RequestContinuationToken }
     * 
     */
    public RequestContinuationToken createRequestContinuationToken() {
        return new RequestContinuationToken();
    }

    /**
     * Create an instance of {@link TraceEventItem }
     * 
     */
    public TraceEventItem createTraceEventItem() {
        return new TraceEventItem();
    }

    /**
     * Create an instance of {@link ArrayOfRequestFootprint }
     * 
     */
    public ArrayOfRequestFootprint createArrayOfRequestFootprint() {
        return new ArrayOfRequestFootprint();
    }

    /**
     * Create an instance of {@link CleanupTrace }
     * 
     */
    public CleanupTrace createCleanupTrace() {
        return new CleanupTrace();
    }

    /**
     * Create an instance of {@link CleanupTraceResponse }
     * 
     */
    public CleanupTraceResponse createCleanupTraceResponse() {
        return new CleanupTraceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TraceEventItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "TraceEventItem")
    public JAXBElement<TraceEventItem> createTraceEventItem(TraceEventItem value) {
        return new JAXBElement<TraceEventItem>(_TraceEventItem_QNAME, TraceEventItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRequestFootprint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "ArrayOfRequestFootprint")
    public JAXBElement<ArrayOfRequestFootprint> createArrayOfRequestFootprint(ArrayOfRequestFootprint value) {
        return new JAXBElement<ArrayOfRequestFootprint>(_ArrayOfRequestFootprint_QNAME, ArrayOfRequestFootprint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTraceEventItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "ArrayOfTraceEventItem")
    public JAXBElement<ArrayOfTraceEventItem> createArrayOfTraceEventItem(ArrayOfTraceEventItem value) {
        return new JAXBElement<ArrayOfTraceEventItem>(_ArrayOfTraceEventItem_QNAME, ArrayOfTraceEventItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestFootprint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "RequestFootprint")
    public JAXBElement<RequestFootprint> createRequestFootprint(RequestFootprint value) {
        return new JAXBElement<RequestFootprint>(_RequestFootprint_QNAME, RequestFootprint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "ResponseType")
    public JAXBElement<ResponseType> createResponseType(ResponseType value) {
        return new JAXBElement<ResponseType>(_ResponseType_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRequestData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "ArrayOfRequestData")
    public JAXBElement<ArrayOfRequestData> createArrayOfRequestData(ArrayOfRequestData value) {
        return new JAXBElement<ArrayOfRequestData>(_ArrayOfRequestData_QNAME, ArrayOfRequestData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "RequestData")
    public JAXBElement<RequestData> createRequestData(RequestData value) {
        return new JAXBElement<RequestData>(_RequestData_QNAME, RequestData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestContinuationToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "RequestContinuationToken")
    public JAXBElement<RequestContinuationToken> createRequestContinuationToken(RequestContinuationToken value) {
        return new JAXBElement<RequestContinuationToken>(_RequestContinuationToken_QNAME, RequestContinuationToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "RequestState")
    public JAXBElement<RequestState> createRequestState(RequestState value) {
        return new JAXBElement<RequestState>(_RequestState_QNAME, RequestState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "Data", scope = TraceEventItem.class)
    public JAXBElement<byte[]> createTraceEventItemData(byte[] value) {
        return new JAXBElement<byte[]>(_TraceEventItemData_QNAME, byte[].class, TraceEventItem.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTraceEventItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "UserTraces", scope = RequestFootprint.class)
    public JAXBElement<ArrayOfTraceEventItem> createRequestFootprintUserTraces(ArrayOfTraceEventItem value) {
        return new JAXBElement<ArrayOfTraceEventItem>(_RequestFootprintUserTraces_QNAME, ArrayOfTraceEventItem.class, RequestFootprint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "TargetMachine", scope = RequestFootprint.class)
    public JAXBElement<String> createRequestFootprintTargetMachine(String value) {
        return new JAXBElement<String>(_RequestFootprintTargetMachine_QNAME, String.class, RequestFootprint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "ExceptionDetail", scope = RequestFootprint.class)
    public JAXBElement<String> createRequestFootprintExceptionDetail(String value) {
        return new JAXBElement<String>(_RequestFootprintExceptionDetail_QNAME, String.class, RequestFootprint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRequestFootprint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "DispatchHistory", scope = RequestData.class)
    public JAXBElement<ArrayOfRequestFootprint> createRequestDataDispatchHistory(ArrayOfRequestFootprint value) {
        return new JAXBElement<ArrayOfRequestFootprint>(_RequestDataDispatchHistory_QNAME, ArrayOfRequestFootprint.class, RequestData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com", name = "Context", scope = RequestData.class)
    public JAXBElement<ArrayOfstring> createRequestDataContext(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_RequestDataContext_QNAME, ArrayOfstring.class, RequestData.class, value);
    }

}
