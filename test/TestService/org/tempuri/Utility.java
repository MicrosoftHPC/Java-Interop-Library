//-----------------------------------------------------------------------
// <copyright file="Utility.java" company="Microsoft">
//     Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//    Utility class for EchoSvc
// </summary>
// <author>Jiabin Hu</author>
//-----------------------------------------------------------------------

package org.tempuri;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.microsoft.hpc.session.RetryOperationError;

class Utility {
    public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static String getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(cal.getTime());
	}
	
	public static ITestServiceEchoRetryOperationErrorFaultFaultMessage BuildRetryOperationError(String reason)
	{
	    com.microsoft.hpc.aitestsvclib.session.ObjectFactory fact = 
	        new com.microsoft.hpc.aitestsvclib.session.ObjectFactory();
	    RetryOperationError err = new RetryOperationError();
		err.setReason(fact.createRetryOperationErrorReason(reason));
		ITestServiceEchoRetryOperationErrorFaultFaultMessage msg 
			= new ITestServiceEchoRetryOperationErrorFaultFaultMessage("", err);
		return msg;
	}
	
	public static ITestServiceEchoRetryOperationErrorFaultFaultMessage BuildRetryOperationError(String reason, String message)
	{
	    com.microsoft.hpc.aitestsvclib.session.ObjectFactory fact = 
            new com.microsoft.hpc.aitestsvclib.session.ObjectFactory();
		RetryOperationError err = new RetryOperationError();
		err.setReason(fact.createRetryOperationErrorReason(reason));
		ITestServiceEchoRetryOperationErrorFaultFaultMessage msg 
			= new ITestServiceEchoRetryOperationErrorFaultFaultMessage(
				message, err);
		return msg;
	}
	
    public static XMLGregorianCalendar getXMLCurrentTime() throws DatatypeConfigurationException
    {
        GregorianCalendar gcal = new GregorianCalendar();
        XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        return xcal;
    }
    
    public static XMLGregorianCalendar convertXMLGregorianCalendar(Date date) throws DatatypeConfigurationException
    {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return cal;
    }
    
    public static void sleep(long duration) 
    {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
