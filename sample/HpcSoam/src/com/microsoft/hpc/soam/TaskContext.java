/*
 */
package com.microsoft.hpc.soam;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author v-dafu
 */
public class TaskContext {

    private String m_userData = "";
    private byte[] m_input = null;
    private byte[] m_output = null;

    public String getUserData() {
        return m_userData;
    }

    public void setUserData(String m_userData) {
        this.m_userData = m_userData;
    }

    public void setInput(byte[] soamInput) {
        m_input = soamInput;
    }

    public byte[] getOutput() {
        return m_output;
    }

    public void populateTaskInput(Message inMsg) throws SoamException {
        if (inMsg == null) {
            throw new SoamException("Input object is null.");
        }
        if (m_input == null) {
            throw new SoamException("No input data bytes.");
        }

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(m_input);
            inMsg.onDeserialize(bais);
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

    public void setTaskOutput(Message outMsg) throws SoamException {
        if (outMsg == null) {
            throw new SoamException("Output object is null.");
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            outMsg.onSerialize(baos);
            m_output = baos.toByteArray();
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

}
