
package com.microsoft.hpc.soadiagservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.hpc.ArrayOfRequestData;
import com.microsoft.hpc.ArrayOfTraceEventItem;
import com.microsoft.hpc.RequestData;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc.soadiagservice package. 
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

    private final static QName _QueryUserTraceByRequestResponseRequest_QNAME = new QName("http://hpc.microsoft.com/SoaDiagService/", "request");
    private final static QName _QuerySessionTraceResponseQuerySessionTraceResult_QNAME = new QName("http://hpc.microsoft.com/SoaDiagService/", "QuerySessionTraceResult");
    private final static QName _QueryForRequestResponseQueryForRequestResult_QNAME = new QName("http://hpc.microsoft.com/SoaDiagService/", "QueryForRequestResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc.soadiagservice
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link QueryUserTraceByRequest }
     * 
     */
    public QueryUserTraceByRequest createQueryUserTraceByRequest() {
        return new QueryUserTraceByRequest();
    }

    /**
     * Create an instance of {@link QueryForRequest }
     * 
     */
    public QueryForRequest createQueryForRequest() {
        return new QueryForRequest();
    }

    /**
     * Create an instance of {@link QuerySessionTrace }
     * 
     */
    public QuerySessionTrace createQuerySessionTrace() {
        return new QuerySessionTrace();
    }

    /**
     * Create an instance of {@link DumpTrace }
     * 
     */
    public DumpTrace createDumpTrace() {
        return new DumpTrace();
    }

    /**
     * Create an instance of {@link DumpTraceResponse }
     * 
     */
    public DumpTraceResponse createDumpTraceResponse() {
        return new DumpTraceResponse();
    }

    /**
     * Create an instance of {@link QueryUserTraceByRequestResponse }
     * 
     */
    public QueryUserTraceByRequestResponse createQueryUserTraceByRequestResponse() {
        return new QueryUserTraceByRequestResponse();
    }

    /**
     * Create an instance of {@link QueryForRequestResponse }
     * 
     */
    public QueryForRequestResponse createQueryForRequestResponse() {
        return new QueryForRequestResponse();
    }

    /**
     * Create an instance of {@link QuerySessionTraceResponse }
     * 
     */
    public QuerySessionTraceResponse createQuerySessionTraceResponse() {
        return new QuerySessionTraceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SoaDiagService/", name = "request", scope = QueryUserTraceByRequestResponse.class)
    public JAXBElement<RequestData> createQueryUserTraceByRequestResponseRequest(RequestData value) {
        return new JAXBElement<RequestData>(_QueryUserTraceByRequestResponseRequest_QNAME, RequestData.class, QueryUserTraceByRequestResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTraceEventItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SoaDiagService/", name = "QuerySessionTraceResult", scope = QuerySessionTraceResponse.class)
    public JAXBElement<ArrayOfTraceEventItem> createQuerySessionTraceResponseQuerySessionTraceResult(ArrayOfTraceEventItem value) {
        return new JAXBElement<ArrayOfTraceEventItem>(_QuerySessionTraceResponseQuerySessionTraceResult_QNAME, ArrayOfTraceEventItem.class, QuerySessionTraceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SoaDiagService/", name = "request", scope = QueryUserTraceByRequest.class)
    public JAXBElement<RequestData> createQueryUserTraceByRequestRequest(RequestData value) {
        return new JAXBElement<RequestData>(_QueryUserTraceByRequestResponseRequest_QNAME, RequestData.class, QueryUserTraceByRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRequestData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SoaDiagService/", name = "QueryForRequestResult", scope = QueryForRequestResponse.class)
    public JAXBElement<ArrayOfRequestData> createQueryForRequestResponseQueryForRequestResult(ArrayOfRequestData value) {
        return new JAXBElement<ArrayOfRequestData>(_QueryForRequestResponseQueryForRequestResult_QNAME, ArrayOfRequestData.class, QueryForRequestResponse.class, value);
    }

}
