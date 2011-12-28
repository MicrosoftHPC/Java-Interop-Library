
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfstringstring;
import org.datacontract.schemas._2004._07.services.ClassFoo;
import org.datacontract.schemas._2004._07.services.ClassObj;
import org.datacontract.schemas._2004._07.services.ComputerInfo;
import org.datacontract.schemas._2004._07.services.StatisticInfo;
import org.datacontract.schemas._2004._07.system.Exception;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
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

    private final static QName _EchoObjectResponseEchoObjectResult_QNAME = new QName("http://tempuri.org/", "EchoObjectResult");
    private final static QName _EchoObject3ResponseEchoObject3Result_QNAME = new QName("http://tempuri.org/", "EchoObject3Result");
    private final static QName _EchoObject4Obj_QNAME = new QName("http://tempuri.org/", "obj");
    private final static QName _EchoFaultResponseEchoFaultResult_QNAME = new QName("http://tempuri.org/", "EchoFaultResult");
    private final static QName _EchoObject2ResponseEchoObject2Result_QNAME = new QName("http://tempuri.org/", "EchoObject2Result");
    private final static QName _EchoResponseEchoResult_QNAME = new QName("http://tempuri.org/", "EchoResult");
    private final static QName _EchoWithDelayResponseEchoWithDelayResult_QNAME = new QName("http://tempuri.org/", "EchoWithDelayResult");
    private final static QName _GenerateLoadWithInputFileResponseGenerateLoadWithInputFileResult_QNAME = new QName("http://tempuri.org/", "GenerateLoadWithInputFileResult");
    private final static QName _EchoWithOnExitLogPath_QNAME = new QName("http://tempuri.org/", "logPath");
    private final static QName _EchoClassCls_QNAME = new QName("http://tempuri.org/", "cls");
    private final static QName _EchoWithOnExitResponseEchoWithOnExitResult_QNAME = new QName("http://tempuri.org/", "EchoWithOnExitResult");
    private final static QName _ConsumeCPUResponseConsumeCPUResult_QNAME = new QName("http://tempuri.org/", "ConsumeCPUResult");
    private final static QName _EchoFaultWithNameExceptionName_QNAME = new QName("http://tempuri.org/", "exceptionName");
    private final static QName _EchoFaultEx_QNAME = new QName("http://tempuri.org/", "ex");
    private final static QName _GenerateLoadCommonDataPath_QNAME = new QName("http://tempuri.org/", "common_data_path");
    private final static QName _GenerateLoadInputData_QNAME = new QName("http://tempuri.org/", "input_data");
    private final static QName _EchoObject4ResponseEchoObject4Result_QNAME = new QName("http://tempuri.org/", "EchoObject4Result");
    private final static QName _EchoWithParamS_QNAME = new QName("http://tempuri.org/", "s");
    private final static QName _EchoWithDelayOnSelectedNodeSelectedNode_QNAME = new QName("http://tempuri.org/", "selectedNode");
    private final static QName _GetCommonDataResponseGetCommonDataResult_QNAME = new QName("http://tempuri.org/", "GetCommonDataResult");
    private final static QName _EchoClassResponseEchoClassResult_QNAME = new QName("http://tempuri.org/", "EchoClassResult");
    private final static QName _EchoObject3Type_QNAME = new QName("http://tempuri.org/", "type");
    private final static QName _EchoObject3O_QNAME = new QName("http://tempuri.org/", "o");
    private final static QName _EchoWithDelayOnSelectedNodeResponseEchoWithDelayOnSelectedNodeResult_QNAME = new QName("http://tempuri.org/", "EchoWithDelayOnSelectedNodeResult");
    private final static QName _TraceMsg_QNAME = new QName("http://tempuri.org/", "msg");
    private final static QName _EchoAppSettingsResponseEchoAppSettingsResult_QNAME = new QName("http://tempuri.org/", "EchoAppSettingsResult");
    private final static QName _CheckACLOnAzureResponseCheckACLOnAzureResult_QNAME = new QName("http://tempuri.org/", "CheckACLOnAzureResult");
    private final static QName _GenerateLoadResponseGenerateLoadResult_QNAME = new QName("http://tempuri.org/", "GenerateLoadResult");
    private final static QName _EchoStructResponseEchoStructResult_QNAME = new QName("http://tempuri.org/", "EchoStructResult");
    private final static QName _ServiceSideAsyncEchoResponseServiceSideAsyncEchoResult_QNAME = new QName("http://tempuri.org/", "ServiceSideAsyncEchoResult");
    private final static QName _TraceResponseTraceResult_QNAME = new QName("http://tempuri.org/", "TraceResult");
    private final static QName _SerializationTestStream_QNAME = new QName("http://tempuri.org/", "stream");
    private final static QName _GetCommonDataExpectedMd5Hash_QNAME = new QName("http://tempuri.org/", "expectedMd5Hash");
    private final static QName _GetCommonDataDataClientId_QNAME = new QName("http://tempuri.org/", "dataClientId");
    private final static QName _EchoExceptionResponseEchoExceptionResult_QNAME = new QName("http://tempuri.org/", "EchoExceptionResult");
    private final static QName _RunInprocSoaJobResponseRunInprocSoaJobResult_QNAME = new QName("http://tempuri.org/", "RunInprocSoaJobResult");
    private final static QName _EchoWithParamResponseEchoWithParamResult_QNAME = new QName("http://tempuri.org/", "EchoWithParamResult");
    private final static QName _EchoFaultWithNameResponseEchoFaultWithNameResult_QNAME = new QName("http://tempuri.org/", "EchoFaultWithNameResult");
    private final static QName _GenerateLoadWithInputFileInputDataPath_QNAME = new QName("http://tempuri.org/", "input_data_path");
    private final static QName _EchoWithFailResponseEchoWithFailResult_QNAME = new QName("http://tempuri.org/", "EchoWithFailResult");
    private final static QName _GenerateLoadWithResponseDataResponseGenerateLoadWithResponseDataResult_QNAME = new QName("http://tempuri.org/", "GenerateLoadWithResponseDataResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EchoAppSettings }
     * 
     */
    public EchoAppSettings createEchoAppSettings() {
        return new EchoAppSettings();
    }

    /**
     * Create an instance of {@link EchoFaultWithNameResponse }
     * 
     */
    public EchoFaultWithNameResponse createEchoFaultWithNameResponse() {
        return new EchoFaultWithNameResponse();
    }

    /**
     * Create an instance of {@link EchoWithParamResponse }
     * 
     */
    public EchoWithParamResponse createEchoWithParamResponse() {
        return new EchoWithParamResponse();
    }

    /**
     * Create an instance of {@link EchoAppSettingsResponse }
     * 
     */
    public EchoAppSettingsResponse createEchoAppSettingsResponse() {
        return new EchoAppSettingsResponse();
    }

    /**
     * Create an instance of {@link GenerateLoadResponse }
     * 
     */
    public GenerateLoadResponse createGenerateLoadResponse() {
        return new GenerateLoadResponse();
    }

    /**
     * Create an instance of {@link RunInprocSoaJobResponse }
     * 
     */
    public RunInprocSoaJobResponse createRunInprocSoaJobResponse() {
        return new RunInprocSoaJobResponse();
    }

    /**
     * Create an instance of {@link EchoResponse }
     * 
     */
    public EchoResponse createEchoResponse() {
        return new EchoResponse();
    }

    /**
     * Create an instance of {@link EchoClass }
     * 
     */
    public EchoClass createEchoClass() {
        return new EchoClass();
    }

    /**
     * Create an instance of {@link EchoWithDelay }
     * 
     */
    public EchoWithDelay createEchoWithDelay() {
        return new EchoWithDelay();
    }

    /**
     * Create an instance of {@link EchoObjectResponse }
     * 
     */
    public EchoObjectResponse createEchoObjectResponse() {
        return new EchoObjectResponse();
    }

    /**
     * Create an instance of {@link EchoWithParam }
     * 
     */
    public EchoWithParam createEchoWithParam() {
        return new EchoWithParam();
    }

    /**
     * Create an instance of {@link ConsumeCPU }
     * 
     */
    public ConsumeCPU createConsumeCPU() {
        return new ConsumeCPU();
    }

    /**
     * Create an instance of {@link GenerateLoadWithInputFile }
     * 
     */
    public GenerateLoadWithInputFile createGenerateLoadWithInputFile() {
        return new GenerateLoadWithInputFile();
    }

    /**
     * Create an instance of {@link GetCommonDataResponse }
     * 
     */
    public GetCommonDataResponse createGetCommonDataResponse() {
        return new GetCommonDataResponse();
    }

    /**
     * Create an instance of {@link ServiceSideAsyncEchoResponse }
     * 
     */
    public ServiceSideAsyncEchoResponse createServiceSideAsyncEchoResponse() {
        return new ServiceSideAsyncEchoResponse();
    }

    /**
     * Create an instance of {@link EchoObject4Response }
     * 
     */
    public EchoObject4Response createEchoObject4Response() {
        return new EchoObject4Response();
    }

    /**
     * Create an instance of {@link EchoFaultWithName }
     * 
     */
    public EchoFaultWithName createEchoFaultWithName() {
        return new EchoFaultWithName();
    }

    /**
     * Create an instance of {@link KillResponse }
     * 
     */
    public KillResponse createKillResponse() {
        return new KillResponse();
    }

    /**
     * Create an instance of {@link GenerateLoadWithResponseDataResponse }
     * 
     */
    public GenerateLoadWithResponseDataResponse createGenerateLoadWithResponseDataResponse() {
        return new GenerateLoadWithResponseDataResponse();
    }

    /**
     * Create an instance of {@link EchoException }
     * 
     */
    public EchoException createEchoException() {
        return new EchoException();
    }

    /**
     * Create an instance of {@link ConsumeCPUResponse }
     * 
     */
    public ConsumeCPUResponse createConsumeCPUResponse() {
        return new ConsumeCPUResponse();
    }

    /**
     * Create an instance of {@link Echo }
     * 
     */
    public Echo createEcho() {
        return new Echo();
    }

    /**
     * Create an instance of {@link SerializationTest }
     * 
     */
    public SerializationTest createSerializationTest() {
        return new SerializationTest();
    }

    /**
     * Create an instance of {@link EchoWithDelayResponse }
     * 
     */
    public EchoWithDelayResponse createEchoWithDelayResponse() {
        return new EchoWithDelayResponse();
    }

    /**
     * Create an instance of {@link Kill }
     * 
     */
    public Kill createKill() {
        return new Kill();
    }

    /**
     * Create an instance of {@link EchoDoubleResponse }
     * 
     */
    public EchoDoubleResponse createEchoDoubleResponse() {
        return new EchoDoubleResponse();
    }

    /**
     * Create an instance of {@link GenerateLoad }
     * 
     */
    public GenerateLoad createGenerateLoad() {
        return new GenerateLoad();
    }

    /**
     * Create an instance of {@link TraceResponse }
     * 
     */
    public TraceResponse createTraceResponse() {
        return new TraceResponse();
    }

    /**
     * Create an instance of {@link EchoObject2 }
     * 
     */
    public EchoObject2 createEchoObject2() {
        return new EchoObject2();
    }

    /**
     * Create an instance of {@link EchoObject3 }
     * 
     */
    public EchoObject3 createEchoObject3() {
        return new EchoObject3();
    }

    /**
     * Create an instance of {@link EchoWithFail }
     * 
     */
    public EchoWithFail createEchoWithFail() {
        return new EchoWithFail();
    }

    /**
     * Create an instance of {@link EchoClassResponse }
     * 
     */
    public EchoClassResponse createEchoClassResponse() {
        return new EchoClassResponse();
    }

    /**
     * Create an instance of {@link EchoFaultResponse }
     * 
     */
    public EchoFaultResponse createEchoFaultResponse() {
        return new EchoFaultResponse();
    }

    /**
     * Create an instance of {@link EchoObject4 }
     * 
     */
    public EchoObject4 createEchoObject4() {
        return new EchoObject4();
    }

    /**
     * Create an instance of {@link ServiceSideAsyncEcho }
     * 
     */
    public ServiceSideAsyncEcho createServiceSideAsyncEcho() {
        return new ServiceSideAsyncEcho();
    }

    /**
     * Create an instance of {@link GenerateLoadWithInputFileResponse }
     * 
     */
    public GenerateLoadWithInputFileResponse createGenerateLoadWithInputFileResponse() {
        return new GenerateLoadWithInputFileResponse();
    }

    /**
     * Create an instance of {@link EchoObject3Response }
     * 
     */
    public EchoObject3Response createEchoObject3Response() {
        return new EchoObject3Response();
    }

    /**
     * Create an instance of {@link EchoDouble }
     * 
     */
    public EchoDouble createEchoDouble() {
        return new EchoDouble();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link GetCommonData }
     * 
     */
    public GetCommonData createGetCommonData() {
        return new GetCommonData();
    }

    /**
     * Create an instance of {@link EchoFault }
     * 
     */
    public EchoFault createEchoFault() {
        return new EchoFault();
    }

    /**
     * Create an instance of {@link Trace }
     * 
     */
    public Trace createTrace() {
        return new Trace();
    }

    /**
     * Create an instance of {@link SerializationTestResponse }
     * 
     */
    public SerializationTestResponse createSerializationTestResponse() {
        return new SerializationTestResponse();
    }

    /**
     * Create an instance of {@link LastTime }
     * 
     */
    public LastTime createLastTime() {
        return new LastTime();
    }

    /**
     * Create an instance of {@link EchoWithDelayOnSelectedNode }
     * 
     */
    public EchoWithDelayOnSelectedNode createEchoWithDelayOnSelectedNode() {
        return new EchoWithDelayOnSelectedNode();
    }

    /**
     * Create an instance of {@link EchoStructResponse }
     * 
     */
    public EchoStructResponse createEchoStructResponse() {
        return new EchoStructResponse();
    }

    /**
     * Create an instance of {@link LastTimeResponse }
     * 
     */
    public LastTimeResponse createLastTimeResponse() {
        return new LastTimeResponse();
    }

    /**
     * Create an instance of {@link EchoWithOnExitResponse }
     * 
     */
    public EchoWithOnExitResponse createEchoWithOnExitResponse() {
        return new EchoWithOnExitResponse();
    }

    /**
     * Create an instance of {@link EchoWithDelayOnSelectedNodeResponse }
     * 
     */
    public EchoWithDelayOnSelectedNodeResponse createEchoWithDelayOnSelectedNodeResponse() {
        return new EchoWithDelayOnSelectedNodeResponse();
    }

    /**
     * Create an instance of {@link GenerateLoadWithResponseData }
     * 
     */
    public GenerateLoadWithResponseData createGenerateLoadWithResponseData() {
        return new GenerateLoadWithResponseData();
    }

    /**
     * Create an instance of {@link RunInprocSoaJob }
     * 
     */
    public RunInprocSoaJob createRunInprocSoaJob() {
        return new RunInprocSoaJob();
    }

    /**
     * Create an instance of {@link EchoWithOnExit }
     * 
     */
    public EchoWithOnExit createEchoWithOnExit() {
        return new EchoWithOnExit();
    }

    /**
     * Create an instance of {@link EchoObject2Response }
     * 
     */
    public EchoObject2Response createEchoObject2Response() {
        return new EchoObject2Response();
    }

    /**
     * Create an instance of {@link EchoWithFailResponse }
     * 
     */
    public EchoWithFailResponse createEchoWithFailResponse() {
        return new EchoWithFailResponse();
    }

    /**
     * Create an instance of {@link EchoStruct }
     * 
     */
    public EchoStruct createEchoStruct() {
        return new EchoStruct();
    }

    /**
     * Create an instance of {@link EchoExceptionResponse }
     * 
     */
    public EchoExceptionResponse createEchoExceptionResponse() {
        return new EchoExceptionResponse();
    }

    /**
     * Create an instance of {@link EchoObject }
     * 
     */
    public EchoObject createEchoObject() {
        return new EchoObject();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoObjectResult", scope = EchoObjectResponse.class)
    public JAXBElement<Object> createEchoObjectResponseEchoObjectResult(Object value) {
        return new JAXBElement<Object>(_EchoObjectResponseEchoObjectResult_QNAME, Object.class, EchoObjectResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoObject3Result", scope = EchoObject3Response.class)
    public JAXBElement<Object> createEchoObject3ResponseEchoObject3Result(Object value) {
        return new JAXBElement<Object>(_EchoObject3ResponseEchoObject3Result_QNAME, Object.class, EchoObject3Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassObj }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "obj", scope = EchoObject4 .class)
    public JAXBElement<ClassObj> createEchoObject4Obj(ClassObj value) {
        return new JAXBElement<ClassObj>(_EchoObject4Obj_QNAME, ClassObj.class, EchoObject4 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoFaultResult", scope = EchoFaultResponse.class)
    public JAXBElement<ComputerInfo> createEchoFaultResponseEchoFaultResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoFaultResponseEchoFaultResult_QNAME, ComputerInfo.class, EchoFaultResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassObj }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoObject2Result", scope = EchoObject2Response.class)
    public JAXBElement<ClassObj> createEchoObject2ResponseEchoObject2Result(ClassObj value) {
        return new JAXBElement<ClassObj>(_EchoObject2ResponseEchoObject2Result_QNAME, ClassObj.class, EchoObject2Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoResult", scope = EchoResponse.class)
    public JAXBElement<ComputerInfo> createEchoResponseEchoResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoResponseEchoResult_QNAME, ComputerInfo.class, EchoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoWithDelayResult", scope = EchoWithDelayResponse.class)
    public JAXBElement<ComputerInfo> createEchoWithDelayResponseEchoWithDelayResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoWithDelayResponseEchoWithDelayResult_QNAME, ComputerInfo.class, EchoWithDelayResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatisticInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GenerateLoadWithInputFileResult", scope = GenerateLoadWithInputFileResponse.class)
    public JAXBElement<StatisticInfo> createGenerateLoadWithInputFileResponseGenerateLoadWithInputFileResult(StatisticInfo value) {
        return new JAXBElement<StatisticInfo>(_GenerateLoadWithInputFileResponseGenerateLoadWithInputFileResult_QNAME, StatisticInfo.class, GenerateLoadWithInputFileResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "logPath", scope = EchoWithOnExit.class)
    public JAXBElement<String> createEchoWithOnExitLogPath(String value) {
        return new JAXBElement<String>(_EchoWithOnExitLogPath_QNAME, String.class, EchoWithOnExit.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassFoo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "cls", scope = EchoClass.class)
    public JAXBElement<ClassFoo> createEchoClassCls(ClassFoo value) {
        return new JAXBElement<ClassFoo>(_EchoClassCls_QNAME, ClassFoo.class, EchoClass.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoWithOnExitResult", scope = EchoWithOnExitResponse.class)
    public JAXBElement<ComputerInfo> createEchoWithOnExitResponseEchoWithOnExitResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoWithOnExitResponseEchoWithOnExitResult_QNAME, ComputerInfo.class, EchoWithOnExitResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ConsumeCPUResult", scope = ConsumeCPUResponse.class)
    public JAXBElement<String> createConsumeCPUResponseConsumeCPUResult(String value) {
        return new JAXBElement<String>(_ConsumeCPUResponseConsumeCPUResult_QNAME, String.class, ConsumeCPUResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "exceptionName", scope = EchoFaultWithName.class)
    public JAXBElement<String> createEchoFaultWithNameExceptionName(String value) {
        return new JAXBElement<String>(_EchoFaultWithNameExceptionName_QNAME, String.class, EchoFaultWithName.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ex", scope = EchoFault.class)
    public JAXBElement<Exception> createEchoFaultEx(Exception value) {
        return new JAXBElement<Exception>(_EchoFaultEx_QNAME, Exception.class, EchoFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "common_data_path", scope = GenerateLoad.class)
    public JAXBElement<String> createGenerateLoadCommonDataPath(String value) {
        return new JAXBElement<String>(_GenerateLoadCommonDataPath_QNAME, String.class, GenerateLoad.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input_data", scope = GenerateLoad.class)
    public JAXBElement<byte[]> createGenerateLoadInputData(byte[] value) {
        return new JAXBElement<byte[]>(_GenerateLoadInputData_QNAME, byte[].class, GenerateLoad.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassObj }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoObject4Result", scope = EchoObject4Response.class)
    public JAXBElement<ClassObj> createEchoObject4ResponseEchoObject4Result(ClassObj value) {
        return new JAXBElement<ClassObj>(_EchoObject4ResponseEchoObject4Result_QNAME, ClassObj.class, EchoObject4Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "s", scope = EchoWithParam.class)
    public JAXBElement<String> createEchoWithParamS(String value) {
        return new JAXBElement<String>(_EchoWithParamS_QNAME, String.class, EchoWithParam.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "selectedNode", scope = EchoWithDelayOnSelectedNode.class)
    public JAXBElement<String> createEchoWithDelayOnSelectedNodeSelectedNode(String value) {
        return new JAXBElement<String>(_EchoWithDelayOnSelectedNodeSelectedNode_QNAME, String.class, EchoWithDelayOnSelectedNode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetCommonDataResult", scope = GetCommonDataResponse.class)
    public JAXBElement<ComputerInfo> createGetCommonDataResponseGetCommonDataResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_GetCommonDataResponseGetCommonDataResult_QNAME, ComputerInfo.class, GetCommonDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassFoo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoClassResult", scope = EchoClassResponse.class)
    public JAXBElement<ClassFoo> createEchoClassResponseEchoClassResult(ClassFoo value) {
        return new JAXBElement<ClassFoo>(_EchoClassResponseEchoClassResult_QNAME, ClassFoo.class, EchoClassResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "type", scope = EchoObject3 .class)
    public JAXBElement<String> createEchoObject3Type(String value) {
        return new JAXBElement<String>(_EchoObject3Type_QNAME, String.class, EchoObject3 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "o", scope = EchoObject3 .class)
    public JAXBElement<Object> createEchoObject3O(Object value) {
        return new JAXBElement<Object>(_EchoObject3O_QNAME, Object.class, EchoObject3 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoWithDelayOnSelectedNodeResult", scope = EchoWithDelayOnSelectedNodeResponse.class)
    public JAXBElement<ComputerInfo> createEchoWithDelayOnSelectedNodeResponseEchoWithDelayOnSelectedNodeResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoWithDelayOnSelectedNodeResponseEchoWithDelayOnSelectedNodeResult_QNAME, ComputerInfo.class, EchoWithDelayOnSelectedNodeResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "msg", scope = Trace.class)
    public JAXBElement<String> createTraceMsg(String value) {
        return new JAXBElement<String>(_TraceMsg_QNAME, String.class, Trace.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfstringstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoAppSettingsResult", scope = EchoAppSettingsResponse.class)
    public JAXBElement<ArrayOfKeyValueOfstringstring> createEchoAppSettingsResponseEchoAppSettingsResult(ArrayOfKeyValueOfstringstring value) {
        return new JAXBElement<ArrayOfKeyValueOfstringstring>(_EchoAppSettingsResponseEchoAppSettingsResult_QNAME, ArrayOfKeyValueOfstringstring.class, EchoAppSettingsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatisticInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GenerateLoadResult", scope = GenerateLoadResponse.class)
    public JAXBElement<StatisticInfo> createGenerateLoadResponseGenerateLoadResult(StatisticInfo value) {
        return new JAXBElement<StatisticInfo>(_GenerateLoadResponseGenerateLoadResult_QNAME, StatisticInfo.class, GenerateLoadResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoStructResult", scope = EchoStructResponse.class)
    public JAXBElement<ComputerInfo> createEchoStructResponseEchoStructResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoStructResponseEchoStructResult_QNAME, ComputerInfo.class, EchoStructResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ServiceSideAsyncEchoResult", scope = ServiceSideAsyncEchoResponse.class)
    public JAXBElement<ComputerInfo> createServiceSideAsyncEchoResponseServiceSideAsyncEchoResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_ServiceSideAsyncEchoResponseServiceSideAsyncEchoResult_QNAME, ComputerInfo.class, ServiceSideAsyncEchoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "TraceResult", scope = TraceResponse.class)
    public JAXBElement<String> createTraceResponseTraceResult(String value) {
        return new JAXBElement<String>(_TraceResponseTraceResult_QNAME, String.class, TraceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ex", scope = EchoException.class)
    public JAXBElement<Exception> createEchoExceptionEx(Exception value) {
        return new JAXBElement<Exception>(_EchoFaultEx_QNAME, Exception.class, EchoException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "stream", scope = SerializationTest.class)
    public JAXBElement<byte[]> createSerializationTestStream(byte[] value) {
        return new JAXBElement<byte[]>(_SerializationTestStream_QNAME, byte[].class, SerializationTest.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input_data", scope = GenerateLoadWithResponseData.class)
    public JAXBElement<byte[]> createGenerateLoadWithResponseDataInputData(byte[] value) {
        return new JAXBElement<byte[]>(_GenerateLoadInputData_QNAME, byte[].class, GenerateLoadWithResponseData.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "expectedMd5Hash", scope = GetCommonData.class)
    public JAXBElement<String> createGetCommonDataExpectedMd5Hash(String value) {
        return new JAXBElement<String>(_GetCommonDataExpectedMd5Hash_QNAME, String.class, GetCommonData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "dataClientId", scope = GetCommonData.class)
    public JAXBElement<String> createGetCommonDataDataClientId(String value) {
        return new JAXBElement<String>(_GetCommonDataDataClientId_QNAME, String.class, GetCommonData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoExceptionResult", scope = EchoExceptionResponse.class)
    public JAXBElement<ComputerInfo> createEchoExceptionResponseEchoExceptionResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoExceptionResponseEchoExceptionResult_QNAME, ComputerInfo.class, EchoExceptionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "type", scope = EchoObject.class)
    public JAXBElement<String> createEchoObjectType(String value) {
        return new JAXBElement<String>(_EchoObject3Type_QNAME, String.class, EchoObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "o", scope = EchoObject.class)
    public JAXBElement<Object> createEchoObjectO(Object value) {
        return new JAXBElement<Object>(_EchoObject3O_QNAME, Object.class, EchoObject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RunInprocSoaJobResult", scope = RunInprocSoaJobResponse.class)
    public JAXBElement<String> createRunInprocSoaJobResponseRunInprocSoaJobResult(String value) {
        return new JAXBElement<String>(_RunInprocSoaJobResponseRunInprocSoaJobResult_QNAME, String.class, RunInprocSoaJobResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassObj }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "obj", scope = EchoObject2 .class)
    public JAXBElement<ClassObj> createEchoObject2Obj(ClassObj value) {
        return new JAXBElement<ClassObj>(_EchoObject4Obj_QNAME, ClassObj.class, EchoObject2 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoWithParamResult", scope = EchoWithParamResponse.class)
    public JAXBElement<ComputerInfo> createEchoWithParamResponseEchoWithParamResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoWithParamResponseEchoWithParamResult_QNAME, ComputerInfo.class, EchoWithParamResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoFaultWithNameResult", scope = EchoFaultWithNameResponse.class)
    public JAXBElement<ComputerInfo> createEchoFaultWithNameResponseEchoFaultWithNameResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoFaultWithNameResponseEchoFaultWithNameResult_QNAME, ComputerInfo.class, EchoFaultWithNameResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "common_data_path", scope = GenerateLoadWithInputFile.class)
    public JAXBElement<String> createGenerateLoadWithInputFileCommonDataPath(String value) {
        return new JAXBElement<String>(_GenerateLoadCommonDataPath_QNAME, String.class, GenerateLoadWithInputFile.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "input_data_path", scope = GenerateLoadWithInputFile.class)
    public JAXBElement<String> createGenerateLoadWithInputFileInputDataPath(String value) {
        return new JAXBElement<String>(_GenerateLoadWithInputFileInputDataPath_QNAME, String.class, GenerateLoadWithInputFile.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "EchoWithFailResult", scope = EchoWithFailResponse.class)
    public JAXBElement<ComputerInfo> createEchoWithFailResponseEchoWithFailResult(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_EchoWithFailResponseEchoWithFailResult_QNAME, ComputerInfo.class, EchoWithFailResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatisticInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GenerateLoadWithResponseDataResult", scope = GenerateLoadWithResponseDataResponse.class)
    public JAXBElement<StatisticInfo> createGenerateLoadWithResponseDataResponseGenerateLoadWithResponseDataResult(StatisticInfo value) {
        return new JAXBElement<StatisticInfo>(_GenerateLoadWithResponseDataResponseGenerateLoadWithResponseDataResult_QNAME, StatisticInfo.class, GenerateLoadWithResponseDataResponse.class, value);
    }

}
