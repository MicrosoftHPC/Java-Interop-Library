
/*
 * 
 */

package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.0
 * 2012-09-18T10:11:49.221+08:00
 * Generated source version: 2.4.0
 * 
 */


@WebServiceClient(name = "ITestService", 
                  wsdlLocation = "file:tempuri.org.wsdl",
                  targetNamespace = "http://tempuri.org/") 
public class ITestService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "ITestService");
    public final static QName DefaultBindingITestService = new QName("http://tempuri.org/", "DefaultBinding_ITestService");
    static {
        URL url = null;
        try {
            url = new URL("file:tempuri.org.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ITestService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:tempuri.org.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ITestService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ITestService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ITestService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ITestService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ITestService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ITestService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ITestService
     */
    @WebEndpoint(name = "DefaultBinding_ITestService")
    public ITestService getDefaultBindingITestService() {
        return super.getPort(DefaultBindingITestService, ITestService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ITestService
     */
    @WebEndpoint(name = "DefaultBinding_ITestService")
    public ITestService getDefaultBindingITestService(WebServiceFeature... features) {
        return super.getPort(DefaultBindingITestService, ITestService.class, features);
    }

}
