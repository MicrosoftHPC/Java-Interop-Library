
package org.tempuri;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.3.3
 * 2011-04-14T16:08:10.423+08:00
 * Generated source version: 2.3.3
 * 
 */

@WebFault(name = "Exception", targetNamespace = "http://schemas.datacontract.org/2004/07/System")
public class ITestServiceEchoFaultExceptionFaultFaultMessage extends java.lang.Exception {
    public static final long serialVersionUID = 20110414160810L;
    
    private org.datacontract.schemas._2004._07.system.Exception exception;

    public ITestServiceEchoFaultExceptionFaultFaultMessage() {
        super();
    }
    
    public ITestServiceEchoFaultExceptionFaultFaultMessage(String message) {
        super(message);
    }
    
    public ITestServiceEchoFaultExceptionFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ITestServiceEchoFaultExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.system.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public ITestServiceEchoFaultExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.system.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public org.datacontract.schemas._2004._07.system.Exception getFaultInfo() {
        return this.exception;
    }
}