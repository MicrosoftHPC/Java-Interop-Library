
package org.tempuri;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.3.3
 * 2011-04-14T16:08:10.431+08:00
 * Generated source version: 2.3.3
 * 
 */

@WebFault(name = "ArgumentNullException", targetNamespace = "http://schemas.datacontract.org/2004/07/System")
public class ITestServiceEchoFaultArgumentNullExceptionFaultFaultMessage extends Exception {
    public static final long serialVersionUID = 20110414160810L;
    
    private org.datacontract.schemas._2004._07.system.ArgumentNullException argumentNullException;

    public ITestServiceEchoFaultArgumentNullExceptionFaultFaultMessage() {
        super();
    }
    
    public ITestServiceEchoFaultArgumentNullExceptionFaultFaultMessage(String message) {
        super(message);
    }
    
    public ITestServiceEchoFaultArgumentNullExceptionFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ITestServiceEchoFaultArgumentNullExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.system.ArgumentNullException argumentNullException) {
        super(message);
        this.argumentNullException = argumentNullException;
    }

    public ITestServiceEchoFaultArgumentNullExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.system.ArgumentNullException argumentNullException, Throwable cause) {
        super(message, cause);
        this.argumentNullException = argumentNullException;
    }

    public org.datacontract.schemas._2004._07.system.ArgumentNullException getFaultInfo() {
        return this.argumentNullException;
    }
}