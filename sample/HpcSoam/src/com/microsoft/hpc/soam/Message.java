package com.microsoft.hpc.soam;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/////////////////////////////////
// Message 
/////////////////////////////////
public abstract class Message implements Serializable {

    //=========================================================================
    //  Constructors
    //=========================================================================
    public Message() {
        super();
    }
    
    public abstract void onDeserialize(InputStream stream) throws SoamException;
    
    public abstract void onSerialize(OutputStream stream) throws SoamException;
}
