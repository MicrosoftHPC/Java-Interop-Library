#ifndef EVENTWRITER
#define EVENTWRITER

#include <windows.h>
#include <evntprov.h>
#include "RuntimeEvents.h"

#define DATA_DESCRIPTOR_SIZE 4

#ifndef TRACELEVEL
#define TRACELEVEL

#define LEVEL_CRITICAL		5
#define LEVEL_ERROR			4
#define LEVEL_WARNING		3
#define LEVEL_INFO			2
#define LEVEL_VERBOSE		1

#endif

#ifndef EVENTWRITER_ERRORINFO
#define EVENTWRITER_ERRORINFO

#define EVENTWRITER_ERRORINFO_WRONGLEVLE		-2147220001
#define EVENTWRITER_ERRORINFO_WRONGMESSAGEGUID	-2147220003
#define EVENTWRITER_ERRORINFO_WRONGDISPATCHGUID -2147220004

#define ERROR_REGISTRATIONFAILED -2147220006
#define ERROR_WRITEEVENTFAILED	 -2147220007

#endif

int WriteEvent(int tracelevel, int sessionid, GUID messageid, GUID dispatchid, LPCWSTR msg);

#endif