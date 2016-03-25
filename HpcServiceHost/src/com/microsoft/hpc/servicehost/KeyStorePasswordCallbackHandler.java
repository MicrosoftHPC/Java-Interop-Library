/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microsoft.hpc.servicehost;

import com.microsoft.hpc.scheduler.session.servicecontext.TraceHelper;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

/**
 *
 * @author v-dafu
 */
public class KeyStorePasswordCallbackHandler implements CallbackHandler {

    // the user name
    public static final String USERNAME = "hpcsoa";

    // the password
    public static final String PASSWORD = "!!!123abc";

    @Override
    public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            WSPasswordCallback pwcb = (WSPasswordCallback) callback;
            String identifier = pwcb.getIdentifier();
            TraceHelper.traceInformation("KeyStorePasswordCallbackHandler.handle(): identifier=" + identifier);

            if (identifier.equals(this.USERNAME)) {
                TraceHelper.traceInformation("KeyStorePasswordCallbackHandler.handle(): set password=" + this.PASSWORD);
                pwcb.setPassword(this.PASSWORD);
                return;
            }
            
            TraceHelper.traceInformation("KeyStorePasswordCallbackHandler.handle(): unkwon user -> identifier=" + identifier + " this.user=" + this.USERNAME);
            throw new UnsupportedCallbackException(pwcb);
        }
    }

}
