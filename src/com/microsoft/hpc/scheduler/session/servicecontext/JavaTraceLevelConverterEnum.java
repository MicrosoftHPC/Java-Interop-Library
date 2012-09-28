// ------------------------------------------------------------------------------
// <copyright file="JavaTraceLevelConverterEnum.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Java trace level converter
// </summary>
// ------------------------------------------------------------------------------
/*
JAVA INTEROP LIBRARY FOR WINDOWS HPC SERVER

Copyright (c) Microsoft Corporation.  All rights reserved.

This license governs use of the accompanying software. If you use the
software, you accept this license. If you do not accept the license, do not
use the software.

1. Definitions
The terms "reproduce," "reproduction," "derivative works," and "distribution"
have the same meaning here as under U.S. copyright law.
A "contribution. is the original software, or any additions or changes to
the software.
A "contributor. is any person that distributes its contribution under this
license.
"Licensed patents. are a contributor.s patent claims that read directly on
its contribution.

2. Grant of Rights
(A) Copyright Grant- Subject to the terms of this license, including the
license conditions and limitations in section 3, each contributor grants you
a non-exclusive, worldwide, royalty-free copyright license to reproduce its
contribution, prepare derivative works of its contribution, and distribute
its contribution or any derivative works that you create.
(B) Patent Grant- Subject to the terms of this license, including the license
conditions and limitations in section 3, each contributor grants you a
non-exclusive, worldwide, royalty-free license under its licensed patents to
make, have made, use, sell, offer for sale, import, and/or otherwise dispose
of its contribution in the software or derivative works of the contribution
in the software.

3. Conditions and Limitations
(A) No Trademark License- This license does not grant you rights to use any
contributors' name, logo, or trademarks.
(B) If you bring a patent claim against any contributor over patents that
you claim are infringed by the software, your patent license from such
contributor to the software ends automatically.
(C) If you distribute any portion of the software, you must retain all
copyright, patent, trademark, and attribution notices that are present in
the software.
(D) If you distribute any portion of the software in source code form,
you may do so only under this license by including a complete copy of this
license with your distribution. If you distribute any portion of the software
in compiled or object code form, you may only do so under a license that
complies with this license.
(E) The software is licensed "as-is." You bear the risk of using it. The
contributors give no express warranties, guarantees or conditions. You may
have additional consumer rights under your local laws which this license
cannot change. To the extent permitted under your local laws, the contributors
exclude the implied warranties of merchantability, fitness for a particular
purpose and non-infringement.
(F) Platform Limitation- The licenses granted in sections 2(A) & 2(B) extend
only to the software or derivative works that you create that operate with
Windows HPC Server.
*/
package com.microsoft.hpc.scheduler.session.servicecontext;

/**
 * @author t-junchw
 * @date May 16, 2011
 * @description Convert the ETW trace level to java trace level
 */
public enum JavaTraceLevelConverterEnum {
    Off("OFF"),
    
    Critical("SEVERE"), 
    Error("SEVERE"),
    Warning("WARNING"),
    
    // below are only available in System.Diagnostics.TraceEventType 
    Start("START"),
    Stop("STOP"),
    Suspend("SUSPEND"),
    Resume("RESUME"),
    Transfer("TRANSFER"),
    
    Information("INFO"),
    Verbose("ALL");
    
    private final String value; 
    
    JavaTraceLevelConverterEnum(String value) {
        this.value = value;
    } 
    public String getValue() {
        return value;
    }
    
    public static JavaTraceLevelConverterEnum convertFromInteger(int value) {
        switch(value) {
        case 0:
            return Off;
        case 1:
            return Critical;
        case 2:
            return Error;
        case 4:
            return Warning;
        case 8:
            return Information;
        case 16:
            return Verbose;
        case 256:
        case 512:
        case 1024:
        case 2048:
        case 4096:
            return Information;
        }
        return null;
    }
    
    
}