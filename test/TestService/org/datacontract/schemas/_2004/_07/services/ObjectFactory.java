
package org.datacontract.schemas._2004._07.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfstringstring;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.services package. 
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

    private final static QName _TestStruct_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "TestStruct");
    private final static QName _ComputerInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "ComputerInfo");
    private final static QName _ClassObj_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "ClassObj");
    private final static QName _TestEnum_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "TestEnum");
    private final static QName _ClassFoo_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "ClassFoo");
    private final static QName _StatisticInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "StatisticInfo");
    private final static QName _ClassStr_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "ClassStr");
    private final static QName _Sub_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "Sub");
    private final static QName _StatisticInfoInstanceId_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "instanceId");
    private final static QName _StatisticInfoResponseData_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "responseData");
    private final static QName _ClassObjType_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "type");
    private final static QName _ClassObjO_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "o");
    private final static QName _ComputerInfoRunAsUser_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "runAsUser");
    private final static QName _ComputerInfoEnvs_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "envs");
    private final static QName _ComputerInfoScheduler_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "scheduler");
    private final static QName _ComputerInfoName_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "Name");
    private final static QName _ComputerInfoSub_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "sub");
    private final static QName _ClassStrStr_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "str");
    private final static QName _ClassFooS_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "s");
    private final static QName _SubSubS_QNAME = new QName("http://schemas.datacontract.org/2004/07/services", "sub_s");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ComputerInfo }
     * 
     */
    public ComputerInfo createComputerInfo() {
        return new ComputerInfo();
    }

    /**
     * Create an instance of {@link StatisticInfo }
     * 
     */
    public StatisticInfo createStatisticInfo() {
        return new StatisticInfo();
    }

    /**
     * Create an instance of {@link ClassFoo }
     * 
     */
    public ClassFoo createClassFoo() {
        return new ClassFoo();
    }

    /**
     * Create an instance of {@link ClassObj }
     * 
     */
    public ClassObj createClassObj() {
        return new ClassObj();
    }

    /**
     * Create an instance of {@link TestStruct }
     * 
     */
    public TestStruct createTestStruct() {
        return new TestStruct();
    }

    /**
     * Create an instance of {@link ClassStr }
     * 
     */
    public ClassStr createClassStr() {
        return new ClassStr();
    }

    /**
     * Create an instance of {@link Sub }
     * 
     */
    public Sub createSub() {
        return new Sub();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestStruct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "TestStruct")
    public JAXBElement<TestStruct> createTestStruct(TestStruct value) {
        return new JAXBElement<TestStruct>(_TestStruct_QNAME, TestStruct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComputerInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "ComputerInfo")
    public JAXBElement<ComputerInfo> createComputerInfo(ComputerInfo value) {
        return new JAXBElement<ComputerInfo>(_ComputerInfo_QNAME, ComputerInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassObj }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "ClassObj")
    public JAXBElement<ClassObj> createClassObj(ClassObj value) {
        return new JAXBElement<ClassObj>(_ClassObj_QNAME, ClassObj.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestEnum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "TestEnum")
    public JAXBElement<TestEnum> createTestEnum(TestEnum value) {
        return new JAXBElement<TestEnum>(_TestEnum_QNAME, TestEnum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassFoo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "ClassFoo")
    public JAXBElement<ClassFoo> createClassFoo(ClassFoo value) {
        return new JAXBElement<ClassFoo>(_ClassFoo_QNAME, ClassFoo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatisticInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "StatisticInfo")
    public JAXBElement<StatisticInfo> createStatisticInfo(StatisticInfo value) {
        return new JAXBElement<StatisticInfo>(_StatisticInfo_QNAME, StatisticInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassStr }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "ClassStr")
    public JAXBElement<ClassStr> createClassStr(ClassStr value) {
        return new JAXBElement<ClassStr>(_ClassStr_QNAME, ClassStr.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sub }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "Sub")
    public JAXBElement<Sub> createSub(Sub value) {
        return new JAXBElement<Sub>(_Sub_QNAME, Sub.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "instanceId", scope = StatisticInfo.class)
    public JAXBElement<String> createStatisticInfoInstanceId(String value) {
        return new JAXBElement<String>(_StatisticInfoInstanceId_QNAME, String.class, StatisticInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "responseData", scope = StatisticInfo.class)
    public JAXBElement<byte[]> createStatisticInfoResponseData(byte[] value) {
        return new JAXBElement<byte[]>(_StatisticInfoResponseData_QNAME, byte[].class, StatisticInfo.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "type", scope = ClassObj.class)
    public JAXBElement<String> createClassObjType(String value) {
        return new JAXBElement<String>(_ClassObjType_QNAME, String.class, ClassObj.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "o", scope = ClassObj.class)
    public JAXBElement<Object> createClassObjO(Object value) {
        return new JAXBElement<Object>(_ClassObjO_QNAME, Object.class, ClassObj.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "runAsUser", scope = ComputerInfo.class)
    public JAXBElement<String> createComputerInfoRunAsUser(String value) {
        return new JAXBElement<String>(_ComputerInfoRunAsUser_QNAME, String.class, ComputerInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyValueOfstringstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "envs", scope = ComputerInfo.class)
    public JAXBElement<ArrayOfKeyValueOfstringstring> createComputerInfoEnvs(ArrayOfKeyValueOfstringstring value) {
        return new JAXBElement<ArrayOfKeyValueOfstringstring>(_ComputerInfoEnvs_QNAME, ArrayOfKeyValueOfstringstring.class, ComputerInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "scheduler", scope = ComputerInfo.class)
    public JAXBElement<String> createComputerInfoScheduler(String value) {
        return new JAXBElement<String>(_ComputerInfoScheduler_QNAME, String.class, ComputerInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "Name", scope = ComputerInfo.class)
    public JAXBElement<String> createComputerInfoName(String value) {
        return new JAXBElement<String>(_ComputerInfoName_QNAME, String.class, ComputerInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sub }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "sub", scope = ComputerInfo.class)
    public JAXBElement<Sub> createComputerInfoSub(Sub value) {
        return new JAXBElement<Sub>(_ComputerInfoSub_QNAME, Sub.class, ComputerInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "str", scope = ClassStr.class)
    public JAXBElement<String> createClassStrStr(String value) {
        return new JAXBElement<String>(_ClassStrStr_QNAME, String.class, ClassStr.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "s", scope = ClassFoo.class)
    public JAXBElement<String> createClassFooS(String value) {
        return new JAXBElement<String>(_ClassFooS_QNAME, String.class, ClassFoo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "sub_s", scope = Sub.class)
    public JAXBElement<String> createSubSubS(String value) {
        return new JAXBElement<String>(_SubSubS_QNAME, String.class, Sub.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/services", name = "s", scope = TestStruct.class)
    public JAXBElement<String> createTestStructS(String value) {
        return new JAXBElement<String>(_ClassFooS_QNAME, String.class, TestStruct.class, value);
    }

}
