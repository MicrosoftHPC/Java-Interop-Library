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
package com.microsoft.hpc.scheduler.session.servicecontext.etw;

import java.util.UUID;

import com.microsoft.hpc.properties.ErrorCode;
import com.microsoft.hpc.scheduler.session.Constant;
import com.microsoft.hpc.scheduler.session.servicecontext.JavaTraceLevelConverterEnum;
import com.microsoft.hpc.scheduler.session.servicecontext.ServiceContext;

/**
 * @author t-hengz
 * @date Aug 6, 2012
 * @description invoke c++ method to call ETW API with JNI
 */
public class UserEvent {
    /**
     * @field used for checking tracehelper.dll 
     */
	static private boolean dllexist;
	
    /**
     * @description load tracehelper.dll
     */
	static
	{
		dllexist = true;
		try{
			System.loadLibrary("tracehelper");
		}
		catch(java.lang.UnsatisfiedLinkError e){
			dllexist = false;
		}
	}

	static private native int CWriteUserEvent(int Flag, int SessionId, String MessageId, String DispatchId, String msg);
	
    /**
     * @param level
     * @param sessionId
     * @param messageId
     * @param dispatchId
     * @param msg
     * 
     */
	static public int WriteUserEvent(JavaTraceLevelConverterEnum level, int SessionId, UUID messageid, UUID dispatchid, String msg)
	{
		//check if .dll exist
		if(!dllexist)
			return ErrorCode.ERROR_DLLNOTEXIST;
		// filter by trace level
		if(ServiceContext.getSoaDiagTraceLevel().ordinal() < level.ordinal()) {
		    return 0;
		}
		
		//check input
		if (msg==null)
			return ErrorCode.ERROR_NULLMSG;
		
		if (messageid==null)
			return ErrorCode.ERROR_INVALIDMESSAGEID;

		if (dispatchid==null)
			return ErrorCode.ERROR_INVALIDDISPATCHID;
		
		//call c method
		int status = ErrorCode.ERROR_INVALIDFLAG;
		
		if (level.equals(JavaTraceLevelConverterEnum.Critical))
			status = CWriteUserEvent(Constant.TRACE_CRITICAL,SessionId,messageid.toString(),dispatchid.toString(),msg);
		
		if (level.equals(JavaTraceLevelConverterEnum.Error))
			status = CWriteUserEvent(Constant.TRACE_ERROR,SessionId,messageid.toString(),dispatchid.toString(),msg);
		
		if (level.equals(JavaTraceLevelConverterEnum.Warning))
			status = CWriteUserEvent(Constant.TRACE_WARNING,SessionId,messageid.toString(),dispatchid.toString(),msg);
		
		if (level.equals(JavaTraceLevelConverterEnum.Information))
			status = CWriteUserEvent(Constant.TRACE_INFO,SessionId,messageid.toString(),dispatchid.toString(),msg);
		
		if (level.equals(JavaTraceLevelConverterEnum.Verbose))
			status = CWriteUserEvent(Constant.TRACE_VERBOSE,SessionId,messageid.toString(),dispatchid.toString(),msg);
		
		return status;
	}

}
