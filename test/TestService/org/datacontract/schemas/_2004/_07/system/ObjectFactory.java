
package org.datacontract.schemas._2004._07.system;

import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.system package. 
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

    private final static QName _ArgumentException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "ArgumentException");
    private final static QName _ConsoleKey_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "ConsoleKey");
    private final static QName _ArithmeticException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "ArithmeticException");
    private final static QName _SystemException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "SystemException");
    private final static QName _OutOfMemoryException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "OutOfMemoryException");
    private final static QName _ArgumentNullException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "ArgumentNullException");
    private final static QName _ConsoleModifiers_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "ConsoleModifiers");
    private final static QName _ConsoleKeyInfo_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "ConsoleKeyInfo");
    private final static QName _Exception_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "Exception");
    private final static QName _DivideByZeroException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "DivideByZeroException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.system
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link ArgumentException }
     * 
     */
    public ArgumentException createArgumentException() {
        return new ArgumentException();
    }

    /**
     * Create an instance of {@link SystemException }
     * 
     */
    public SystemException createSystemException() {
        return new SystemException();
    }

    /**
     * Create an instance of {@link DivideByZeroException }
     * 
     */
    public DivideByZeroException createDivideByZeroException() {
        return new DivideByZeroException();
    }

    /**
     * Create an instance of {@link ConsoleKeyInfo }
     * 
     */
    public ConsoleKeyInfo createConsoleKeyInfo() {
        return new ConsoleKeyInfo();
    }

    /**
     * Create an instance of {@link ArgumentNullException }
     * 
     */
    public ArgumentNullException createArgumentNullException() {
        return new ArgumentNullException();
    }

    /**
     * Create an instance of {@link OutOfMemoryException }
     * 
     */
    public OutOfMemoryException createOutOfMemoryException() {
        return new OutOfMemoryException();
    }

    /**
     * Create an instance of {@link ArithmeticException }
     * 
     */
    public ArithmeticException createArithmeticException() {
        return new ArithmeticException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArgumentException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "ArgumentException")
    public JAXBElement<ArgumentException> createArgumentException(ArgumentException value) {
        return new JAXBElement<ArgumentException>(_ArgumentException_QNAME, ArgumentException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsoleKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "ConsoleKey")
    public JAXBElement<ConsoleKey> createConsoleKey(ConsoleKey value) {
        return new JAXBElement<ConsoleKey>(_ConsoleKey_QNAME, ConsoleKey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArithmeticException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "ArithmeticException")
    public JAXBElement<ArithmeticException> createArithmeticException(ArithmeticException value) {
        return new JAXBElement<ArithmeticException>(_ArithmeticException_QNAME, ArithmeticException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "SystemException")
    public JAXBElement<SystemException> createSystemException(SystemException value) {
        return new JAXBElement<SystemException>(_SystemException_QNAME, SystemException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutOfMemoryException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "OutOfMemoryException")
    public JAXBElement<OutOfMemoryException> createOutOfMemoryException(OutOfMemoryException value) {
        return new JAXBElement<OutOfMemoryException>(_OutOfMemoryException_QNAME, OutOfMemoryException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArgumentNullException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "ArgumentNullException")
    public JAXBElement<ArgumentNullException> createArgumentNullException(ArgumentNullException value) {
        return new JAXBElement<ArgumentNullException>(_ArgumentNullException_QNAME, ArgumentNullException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "ConsoleModifiers")
    public JAXBElement<List<String>> createConsoleModifiers(List<String> value) {
        return new JAXBElement<List<String>>(_ConsoleModifiers_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsoleKeyInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "ConsoleKeyInfo")
    public JAXBElement<ConsoleKeyInfo> createConsoleKeyInfo(ConsoleKeyInfo value) {
        return new JAXBElement<ConsoleKeyInfo>(_ConsoleKeyInfo_QNAME, ConsoleKeyInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DivideByZeroException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "DivideByZeroException")
    public JAXBElement<DivideByZeroException> createDivideByZeroException(DivideByZeroException value) {
        return new JAXBElement<DivideByZeroException>(_DivideByZeroException_QNAME, DivideByZeroException.class, null, value);
    }

}
