/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microsoft.hpc.soam;

import java.nio.file.Paths;

/**
 *
 * @author v-dafu
 */
public class ServiceContext {

    private final static String LogFolderName = "work";

    public String getServiceName() throws SoamException {
        try {
            return com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext.getSoamServiceName();
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

    public String getApplicationName() throws SoamException {
        try {
            return com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext.getServiceName();
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

    public String getDeployDirectory() throws SoamException {
        try {
            return com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext.getSoaHome();
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

    public String getLogDirectory() throws SoamException {
        try {
            return Paths.get(com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext.getSoaHome(), LogFolderName).toString();
        } catch (Exception ex) {
            throw new SoamException(ex);
        }
    }

}
