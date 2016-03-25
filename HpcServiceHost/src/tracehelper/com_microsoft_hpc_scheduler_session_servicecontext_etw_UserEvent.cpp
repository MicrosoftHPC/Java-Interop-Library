#include "com_microsoft_hpc_scheduler_session_servicecontext_etw_UserEvent.h"
#include "eventwriter.h"

JNIEXPORT jint JNICALL Java_com_microsoft_hpc_scheduler_session_servicecontext_etw_UserEvent_CWriteUserEvent
	(JNIEnv *env, jclass clazz, jint tracelevel, jint sessionid, jstring messageid, jstring dispatchid, jstring msg)
{
	LPCWSTR messageidstr;
	LPCWSTR dispatchidstr;
	LPCWSTR messagestr;
	
	//get string
	messageidstr = (LPCWSTR)(env)->GetStringChars(messageid, 0);
	dispatchidstr = (LPCWSTR)(env)->GetStringChars(dispatchid, 0);
	messagestr = (LPCWSTR)(env)->GetStringChars(msg, 0);
	
	//get guid
	GUID messageguid;
	GUID dispatchguid;
	UuidFromString((RPC_WSTR)messageidstr,&messageguid);
	UuidFromString((RPC_WSTR)dispatchidstr,&dispatchguid);

	//write event
	int status = WriteEvent(tracelevel,sessionid,messageguid,dispatchguid,messagestr);

	//clean
	(env)->ReleaseStringChars(messageid, (const jchar*)messageidstr);
	(env)->ReleaseStringChars(dispatchid, (const jchar*)dispatchidstr);
	(env)->ReleaseStringChars(msg, (const jchar*)messagestr);

	return status;
}
