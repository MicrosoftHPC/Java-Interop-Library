/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microsoft.hpc.soam;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author v-dafu
 */
public abstract class ServiceContainer {

    public void onApplicationAttach(ServiceContext serviceContext) throws SoamException {
    }

    public void onApplicationDetach() throws SoamException {
    }

    public void onCreateService(ServiceContext serviceContext) throws SoamException {
    }

    public void onDestroyService() throws SoamException {
    }

    public abstract void onInvoke(TaskContext taskContext) throws SoamException;

    public void onSessionEnter(SessionContext sessionContext) throws SoamException {
    }

    public void onSessionLeave() throws SoamException {
    }   

}
