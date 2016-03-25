/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microsoft.hpc.soam;

import com.microsoft.hpc.scheduler.session.DataClient;
import java.io.ByteArrayInputStream;

/**
 *
 * @author v-dafu
 */
public class SessionContext {

    private byte[] m_commonData = null;
    private final String m_commonDataClientId = com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext.getCommonDataClientId();

    public String getSessionId() throws SoamException {
        try {
            return com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext.getJobId();
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

    public void populateCommonData(Message commonData) throws SoamException {
        if (commonData == null) {
            throw new SoamException("Common data object is null.");
        }

        if (m_commonData == null) {
            getCommonData();
        }
        if (m_commonData == null) {
            throw new SoamException("No common data object.");
        }

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(m_commonData);
            commonData.onDeserialize(bais);
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

    public byte[] getCommonData() throws SoamException {
        if (m_commonData == null && m_commonDataClientId != null && !m_commonDataClientId.isEmpty()) {
            try {
                DataClient client = com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext.getDataClient(m_commonDataClientId);
                m_commonData = client.readRawBytesAll();
            } catch (java.lang.Exception ex) {
                throw new SoamException(ex);
            }
        }

        return m_commonData;
    }

    public void discardCommonData() throws SoamException {
        m_commonData = null;
    }

}
