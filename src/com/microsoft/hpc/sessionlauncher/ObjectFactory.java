
package com.microsoft.hpc.sessionlauncher;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.hpc.SessionStartInfoContract;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;
import org.datacontract.schemas._2004._07.system.ArrayOfVersion;
import org.datacontract.schemas._2004._07.system.Version;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc.sessionlauncher package. 
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

    private final static QName _SessionInfo_QNAME = new QName("http://hpc.microsoft.com/SessionLauncher", "SessionInfo");
    private final static QName _GetInfoResponseGetInfoResult_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "GetInfoResult");
    private final static QName _SessionInfoBrokerEpr_QNAME = new QName("http://hpc.microsoft.com/SessionLauncher", "BrokerEpr");
    private final static QName _SessionInfoServerVersion_QNAME = new QName("http://hpc.microsoft.com/SessionLauncher", "ServerVersion");
    private final static QName _SessionInfoBrokerLauncherEpr_QNAME = new QName("http://hpc.microsoft.com/SessionLauncher", "BrokerLauncherEpr");
    private final static QName _SessionInfoServiceVersion_QNAME = new QName("http://hpc.microsoft.com/SessionLauncher", "ServiceVersion");
    private final static QName _SessionInfoResponseEpr_QNAME = new QName("http://hpc.microsoft.com/SessionLauncher", "ResponseEpr");
    private final static QName _SessionInfoControllerEpr_QNAME = new QName("http://hpc.microsoft.com/SessionLauncher", "ControllerEpr");
    private final static QName _GetServiceVersionsServiceName_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "serviceName");
    private final static QName _AllocateDurableResponseAllocateDurableResult_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "AllocateDurableResult");
    private final static QName _AllocateDurableResponseServiceVersion_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "serviceVersion");
    private final static QName _GetServerVersionResponseGetServerVersionResult_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "GetServerVersionResult");
    private final static QName _AllocateDurableEndpointPrefix_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "endpointPrefix");
    private final static QName _AllocateDurableInfo_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "info");
    private final static QName _TerminateHeadnode_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "headnode");
    private final static QName _GetDataServerInfoResponseGetDataServerInfoResult_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "GetDataServerInfoResult");
    private final static QName _GetServiceVersionsResponseGetServiceVersionsResult_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "GetServiceVersionsResult");
    private final static QName _AllocateResponseAllocateResult_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "AllocateResult");
    private final static QName _GetSOAConfigurationResponseGetSOAConfigurationResult_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "GetSOAConfigurationResult");
    private final static QName _GetSOAConfigurationKey_QNAME = new QName("http://hpc.microsoft.com/sessionlauncher/", "key");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc.sessionlauncher
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AllocateDurable }
     * 
     */
    public AllocateDurable createAllocateDurable() {
        return new AllocateDurable();
    }

    /**
     * Create an instance of {@link GetSOAConfiguration }
     * 
     */
    public GetSOAConfiguration createGetSOAConfiguration() {
        return new GetSOAConfiguration();
    }

    /**
     * Create an instance of {@link Terminate }
     * 
     */
    public Terminate createTerminate() {
        return new Terminate();
    }

    /**
     * Create an instance of {@link TerminateResponse }
     * 
     */
    public TerminateResponse createTerminateResponse() {
        return new TerminateResponse();
    }

    /**
     * Create an instance of {@link GetServiceVersions }
     * 
     */
    public GetServiceVersions createGetServiceVersions() {
        return new GetServiceVersions();
    }

    /**
     * Create an instance of {@link GetSOAConfigurationResponse }
     * 
     */
    public GetSOAConfigurationResponse createGetSOAConfigurationResponse() {
        return new GetSOAConfigurationResponse();
    }

    /**
     * Create an instance of {@link GetServiceVersionsResponse }
     * 
     */
    public GetServiceVersionsResponse createGetServiceVersionsResponse() {
        return new GetServiceVersionsResponse();
    }

    /**
     * Create an instance of {@link GetInfoResponse }
     * 
     */
    public GetInfoResponse createGetInfoResponse() {
        return new GetInfoResponse();
    }

    /**
     * Create an instance of {@link SessionInfo }
     * 
     */
    public SessionInfo createSessionInfo() {
        return new SessionInfo();
    }

    /**
     * Create an instance of {@link AllocateDurableResponse }
     * 
     */
    public AllocateDurableResponse createAllocateDurableResponse() {
        return new AllocateDurableResponse();
    }

    /**
     * Create an instance of {@link GetServerVersion }
     * 
     */
    public GetServerVersion createGetServerVersion() {
        return new GetServerVersion();
    }

    /**
     * Create an instance of {@link GetInfo }
     * 
     */
    public GetInfo createGetInfo() {
        return new GetInfo();
    }

    /**
     * Create an instance of {@link AllocateResponse }
     * 
     */
    public AllocateResponse createAllocateResponse() {
        return new AllocateResponse();
    }

    /**
     * Create an instance of {@link Allocate }
     * 
     */
    public Allocate createAllocate() {
        return new Allocate();
    }

    /**
     * Create an instance of {@link GetServerVersionResponse }
     * 
     */
    public GetServerVersionResponse createGetServerVersionResponse() {
        return new GetServerVersionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SessionLauncher", name = "SessionInfo")
    public JAXBElement<SessionInfo> createSessionInfo(SessionInfo value) {
        return new JAXBElement<SessionInfo>(_SessionInfo_QNAME, SessionInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "GetInfoResult", scope = GetInfoResponse.class)
    public JAXBElement<SessionInfo> createGetInfoResponseGetInfoResult(SessionInfo value) {
        return new JAXBElement<SessionInfo>(_GetInfoResponseGetInfoResult_QNAME, SessionInfo.class, GetInfoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SessionLauncher", name = "BrokerEpr", scope = SessionInfo.class)
    public JAXBElement<ArrayOfstring> createSessionInfoBrokerEpr(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_SessionInfoBrokerEpr_QNAME, ArrayOfstring.class, SessionInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Version }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SessionLauncher", name = "ServerVersion", scope = SessionInfo.class)
    public JAXBElement<Version> createSessionInfoServerVersion(Version value) {
        return new JAXBElement<Version>(_SessionInfoServerVersion_QNAME, Version.class, SessionInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SessionLauncher", name = "BrokerLauncherEpr", scope = SessionInfo.class)
    public JAXBElement<String> createSessionInfoBrokerLauncherEpr(String value) {
        return new JAXBElement<String>(_SessionInfoBrokerLauncherEpr_QNAME, String.class, SessionInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Version }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SessionLauncher", name = "ServiceVersion", scope = SessionInfo.class)
    public JAXBElement<Version> createSessionInfoServiceVersion(Version value) {
        return new JAXBElement<Version>(_SessionInfoServiceVersion_QNAME, Version.class, SessionInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SessionLauncher", name = "ResponseEpr", scope = SessionInfo.class)
    public JAXBElement<ArrayOfstring> createSessionInfoResponseEpr(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_SessionInfoResponseEpr_QNAME, ArrayOfstring.class, SessionInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/SessionLauncher", name = "ControllerEpr", scope = SessionInfo.class)
    public JAXBElement<ArrayOfstring> createSessionInfoControllerEpr(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_SessionInfoControllerEpr_QNAME, ArrayOfstring.class, SessionInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "serviceName", scope = GetServiceVersions.class)
    public JAXBElement<String> createGetServiceVersionsServiceName(String value) {
        return new JAXBElement<String>(_GetServiceVersionsServiceName_QNAME, String.class, GetServiceVersions.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "AllocateDurableResult", scope = AllocateDurableResponse.class)
    public JAXBElement<ArrayOfstring> createAllocateDurableResponseAllocateDurableResult(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_AllocateDurableResponseAllocateDurableResult_QNAME, ArrayOfstring.class, AllocateDurableResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "serviceVersion", scope = AllocateDurableResponse.class)
    public JAXBElement<String> createAllocateDurableResponseServiceVersion(String value) {
        return new JAXBElement<String>(_AllocateDurableResponseServiceVersion_QNAME, String.class, AllocateDurableResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Version }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "GetServerVersionResult", scope = GetServerVersionResponse.class)
    public JAXBElement<Version> createGetServerVersionResponseGetServerVersionResult(Version value) {
        return new JAXBElement<Version>(_GetServerVersionResponseGetServerVersionResult_QNAME, Version.class, GetServerVersionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "endpointPrefix", scope = AllocateDurable.class)
    public JAXBElement<String> createAllocateDurableEndpointPrefix(String value) {
        return new JAXBElement<String>(_AllocateDurableEndpointPrefix_QNAME, String.class, AllocateDurable.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionStartInfoContract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "info", scope = AllocateDurable.class)
    public JAXBElement<SessionStartInfoContract> createAllocateDurableInfo(SessionStartInfoContract value) {
        return new JAXBElement<SessionStartInfoContract>(_AllocateDurableInfo_QNAME, SessionStartInfoContract.class, AllocateDurable.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "headnode", scope = Terminate.class)
    public JAXBElement<String> createTerminateHeadnode(String value) {
        return new JAXBElement<String>(_TerminateHeadnode_QNAME, String.class, Terminate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "endpointPrefix", scope = GetInfo.class)
    public JAXBElement<String> createGetInfoEndpointPrefix(String value) {
        return new JAXBElement<String>(_AllocateDurableEndpointPrefix_QNAME, String.class, GetInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "headnode", scope = GetInfo.class)
    public JAXBElement<String> createGetInfoHeadnode(String value) {
        return new JAXBElement<String>(_TerminateHeadnode_QNAME, String.class, GetInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfVersion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "GetServiceVersionsResult", scope = GetServiceVersionsResponse.class)
    public JAXBElement<ArrayOfVersion> createGetServiceVersionsResponseGetServiceVersionsResult(ArrayOfVersion value) {
        return new JAXBElement<ArrayOfVersion>(_GetServiceVersionsResponseGetServiceVersionsResult_QNAME, ArrayOfVersion.class, GetServiceVersionsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "AllocateResult", scope = AllocateResponse.class)
    public JAXBElement<ArrayOfstring> createAllocateResponseAllocateResult(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_AllocateResponseAllocateResult_QNAME, ArrayOfstring.class, AllocateResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "serviceVersion", scope = AllocateResponse.class)
    public JAXBElement<String> createAllocateResponseServiceVersion(String value) {
        return new JAXBElement<String>(_AllocateDurableResponseServiceVersion_QNAME, String.class, AllocateResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "GetSOAConfigurationResult", scope = GetSOAConfigurationResponse.class)
    public JAXBElement<String> createGetSOAConfigurationResponseGetSOAConfigurationResult(String value) {
        return new JAXBElement<String>(_GetSOAConfigurationResponseGetSOAConfigurationResult_QNAME, String.class, GetSOAConfigurationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "endpointPrefix", scope = Allocate.class)
    public JAXBElement<String> createAllocateEndpointPrefix(String value) {
        return new JAXBElement<String>(_AllocateDurableEndpointPrefix_QNAME, String.class, Allocate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionStartInfoContract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "info", scope = Allocate.class)
    public JAXBElement<SessionStartInfoContract> createAllocateInfo(SessionStartInfoContract value) {
        return new JAXBElement<SessionStartInfoContract>(_AllocateDurableInfo_QNAME, SessionStartInfoContract.class, Allocate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/sessionlauncher/", name = "key", scope = GetSOAConfiguration.class)
    public JAXBElement<String> createGetSOAConfigurationKey(String value) {
        return new JAXBElement<String>(_GetSOAConfigurationKey_QNAME, String.class, GetSOAConfiguration.class, value);
    }

}
