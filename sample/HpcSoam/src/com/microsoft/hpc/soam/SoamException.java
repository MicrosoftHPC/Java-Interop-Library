/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microsoft.hpc.soam;

/**
 *
 * @author v-dafu
 */
public class SoamException extends Exception {

    private int ErrorCode = 0;
    private String ErrorType = "SoamException";

    public SoamException() {
        super();
    }

    public SoamException(String message) {
        super(message);
    }

    public SoamException(String message, int errorCode) {
        super(message);
        ErrorCode = errorCode;
    }

    public SoamException(String message, Throwable cause) {
        super(message, cause);
    }

    public SoamException(Throwable cause) {
        super(cause);
    }
    
    public int getErrorCode(){
        return ErrorCode;
    }
    
    public String getErrorType(){
        return ErrorType;
    }
}
