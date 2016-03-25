/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microsoft.hpc.servicehost;

import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.ws.security.WSConstants;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

import com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext;
import com.microsoft.hpc.session.RetryOperationError;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author v-dafu
 */
public class WSSPassThroughInterceptor extends AbstractSoapInterceptor {

    HpcServiceHostWrapper hpcHostWrapper;

    private static final Set<QName> HEADERS = new HashSet<QName>();

    static {
        HEADERS.add(new QName(WSConstants.WSSE_NS, WSConstants.WSSE_LN));
        HEADERS.add(new QName(WSConstants.WSSE11_NS, WSConstants.WSSE_LN));
        HEADERS.add(new QName(WSConstants.ENC_NS, WSConstants.ENC_DATA_LN));
    }

    public WSSPassThroughInterceptor(HpcServiceHostWrapper wrapper) {
        super(Phase.PRE_PROTOCOL);
        this.hpcHostWrapper = wrapper;
    }

    public WSSPassThroughInterceptor(String phase, HpcServiceHostWrapper wrapper) {
        super(phase);
        this.hpcHostWrapper = wrapper;
    }

    @Override
    public Set<QName> getUnderstoodHeaders() {
        return HEADERS;
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) {
        // do nothing

        // this interceptor simply returns all WS-Security headers in its getUnderstoodHeaders()
        // method, so that CXF does not complain that they have not been "processed"
        // this is useful if you only need to look at the non-encrypted XML
    }
}
