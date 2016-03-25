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

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author t-jiabhu
 * 
 */
class Utility
{
    public static boolean isNullOrEmpty(String str)
    {
        return str == null || str.isEmpty();
    }

    public static String getCurrentTime()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }
    
    public static XMLGregorianCalendar getXMLCurrentTime() throws DatatypeConfigurationException
    {
        GregorianCalendar gcal = new GregorianCalendar();
        XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        return xcal;
    }

    public static JAXBElement<String> marshalString(String str)
    {
        return (new com.microsoft.schemas._2003._10.serialization.ObjectFactory().createString(str));
    }
}
