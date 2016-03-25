
package com.microsoft.hpc.dataservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfanyType;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc.dataservice package. 
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

    private final static QName _DataFault_QNAME = new QName("http://hpc.microsoft.com/dataservice/", "DataFault");
    private final static QName _OpenDataClientDataClientId_QNAME = new QName("http://hpc.microsoft.com/dataservice/", "dataClientId");
    private final static QName _CreateDataClientAllowedUsers_QNAME = new QName("http://hpc.microsoft.com/dataservice/", "allowedUsers");
    private final static QName _DataFaultContext_QNAME = new QName("http://hpc.microsoft.com/dataservice/", "Context");
    private final static QName _DataFaultReason_QNAME = new QName("http://hpc.microsoft.com/dataservice/", "Reason");
    private final static QName _CreateDataClientResponseCreateDataClientResult_QNAME = new QName("http://hpc.microsoft.com/dataservice/", "CreateDataClientResult");
    private final static QName _OpenDataClientResponseOpenDataClientResult_QNAME = new QName("http://hpc.microsoft.com/dataservice/", "OpenDataClientResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc.dataservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteDataClient }
     * 
     */
    public DeleteDataClient createDeleteDataClient() {
        return new DeleteDataClient();
    }

    /**
     * Create an instance of {@link OpenDataClient }
     * 
     */
    public OpenDataClient createOpenDataClient() {
        return new OpenDataClient();
    }

    /**
     * Create an instance of {@link CreateDataClientResponse }
     * 
     */
    public CreateDataClientResponse createCreateDataClientResponse() {
        return new CreateDataClientResponse();
    }

    /**
     * Create an instance of {@link OpenDataClientResponse }
     * 
     */
    public OpenDataClientResponse createOpenDataClientResponse() {
        return new OpenDataClientResponse();
    }

    /**
     * Create an instance of {@link DataFault }
     * 
     */
    public DataFault createDataFault() {
        return new DataFault();
    }

    /**
     * Create an instance of {@link AssociateDataClientWithSessionResponse }
     * 
     */
    public AssociateDataClientWithSessionResponse createAssociateDataClientWithSessionResponse() {
        return new AssociateDataClientWithSessionResponse();
    }

    /**
     * Create an instance of {@link DeleteDataClientResponse }
     * 
     */
    public DeleteDataClientResponse createDeleteDataClientResponse() {
        return new DeleteDataClientResponse();
    }

    /**
     * Create an instance of {@link CreateDataClient }
     * 
     */
    public CreateDataClient createCreateDataClient() {
        return new CreateDataClient();
    }

    /**
     * Create an instance of {@link AssociateDataClientWithSession }
     * 
     */
    public AssociateDataClientWithSession createAssociateDataClientWithSession() {
        return new AssociateDataClientWithSession();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "DataFault")
    public JAXBElement<DataFault> createDataFault(DataFault value) {
        return new JAXBElement<DataFault>(_DataFault_QNAME, DataFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "dataClientId", scope = OpenDataClient.class)
    public JAXBElement<String> createOpenDataClientDataClientId(String value) {
        return new JAXBElement<String>(_OpenDataClientDataClientId_QNAME, String.class, OpenDataClient.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "allowedUsers", scope = CreateDataClient.class)
    public JAXBElement<ArrayOfstring> createCreateDataClientAllowedUsers(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_CreateDataClientAllowedUsers_QNAME, ArrayOfstring.class, CreateDataClient.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "dataClientId", scope = CreateDataClient.class)
    public JAXBElement<String> createCreateDataClientDataClientId(String value) {
        return new JAXBElement<String>(_OpenDataClientDataClientId_QNAME, String.class, CreateDataClient.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "Context", scope = DataFault.class)
    public JAXBElement<ArrayOfanyType> createDataFaultContext(ArrayOfanyType value) {
        return new JAXBElement<ArrayOfanyType>(_DataFaultContext_QNAME, ArrayOfanyType.class, DataFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "Reason", scope = DataFault.class)
    public JAXBElement<String> createDataFaultReason(String value) {
        return new JAXBElement<String>(_DataFaultReason_QNAME, String.class, DataFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "dataClientId", scope = AssociateDataClientWithSession.class)
    public JAXBElement<String> createAssociateDataClientWithSessionDataClientId(String value) {
        return new JAXBElement<String>(_OpenDataClientDataClientId_QNAME, String.class, AssociateDataClientWithSession.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "CreateDataClientResult", scope = CreateDataClientResponse.class)
    public JAXBElement<String> createCreateDataClientResponseCreateDataClientResult(String value) {
        return new JAXBElement<String>(_CreateDataClientResponseCreateDataClientResult_QNAME, String.class, CreateDataClientResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "dataClientId", scope = DeleteDataClient.class)
    public JAXBElement<String> createDeleteDataClientDataClientId(String value) {
        return new JAXBElement<String>(_OpenDataClientDataClientId_QNAME, String.class, DeleteDataClient.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/dataservice/", name = "OpenDataClientResult", scope = OpenDataClientResponse.class)
    public JAXBElement<String> createOpenDataClientResponseOpenDataClientResult(String value) {
        return new JAXBElement<String>(_OpenDataClientResponseOpenDataClientResult_QNAME, String.class, OpenDataClientResponse.class, value);
    }

}
