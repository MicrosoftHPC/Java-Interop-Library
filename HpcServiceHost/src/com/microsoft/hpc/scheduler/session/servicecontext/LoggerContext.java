// ------------------------------------------------------------------------------
// <copyright file="LoggerContext.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Logger the runtime information
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

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.microsoft.hpc.scheduler.session.Constant;

/**
 * @author t-junchw
 * @date May 10, 2011 #description service logger & ETW trace adapter
 */
public final class LoggerContext
{
    /**
     * @field trace level: ALL, WARNING, SERVER, INFO, FINE, NONE
     */
    Level traceLevel;

    /**
     * @field singleton for class
     */
    final static Logger logger = Logger.getLogger("HPC");

    /**
     * @field service trace file path format
     *        path=tracefolder\\jobid_taskid.taskinstanceid_trace.svclog
     */
    String traceFilePathFormat = "%s%s%s_%s.%s_trace.svclog";

    String envSOA_HOME = Environment
            .getEnvironmentVariable(Constant.SOA_HOMEEnvVar);
    String envCCP_TASKID = Environment
            .getEnvironmentVariable(Constant.TaskIDEnvVar);
    String envCCP_JOBID = Environment
            .getEnvironmentVariable(Constant.JobIDEnvVar);
    String envCCP_TASKINSTANCEID = Environment
            .getEnvironmentVariable(Constant.TASKINSTANCEIDEnvVar);

    /**
     * @description constructor of LoggerContext
     * @param level
     *            Trace Level to be converted from String to Level
     */
    public LoggerContext(String level)
    {
        this.traceLevel = Level.parse(level);

        String tracefolder = envSOA_HOME + File.separator + "SOATrace";
        String filepath = null;

        try
        {
            // judge if directory exists
            if (!(new File(tracefolder).isDirectory()))
            {
                new File(tracefolder).mkdir();
            }
            filepath = String.format(traceFilePathFormat, tracefolder, File.separator, 
                    envCCP_JOBID, envCCP_TASKID, envCCP_TASKINSTANCEID);
        } catch (SecurityException e)
        {
            TraceHelper.traceError(e.toString());
            TraceHelper.traceStackError(e);

            // put trace file in the temp folder of system
            filepath = System.getProperty("java.io.tmpdir");
        }

        int MaxFileNume = 1;
        logger.setLevel(Level.ALL);

        try
        {
            // File Handler
            FileHandler fileHandler;
            FileLogFormatter fileLogFormatter = new FileLogFormatter();
            fileHandler = new FileHandler(filepath, 0, MaxFileNume, false);
            fileHandler.setLevel(traceLevel);
            fileHandler.setFormatter(fileLogFormatter);
            logger.addHandler(fileHandler);

            // Console Handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(traceLevel);
            ConsoleLogFormatter consoleLogFormatter = new ConsoleLogFormatter();
            consoleHandler.setFormatter(consoleLogFormatter);
            logger.addHandler(consoleHandler);

        } catch (SecurityException e)
        {
            TraceHelper.traceError(e.toString());
            TraceHelper.traceStackError(e);
        } catch (IOException e)
        {
            TraceHelper.traceError(e.toString());
            TraceHelper.traceStackError(e);
        }

    }

    /**
     * interface for trace event
     * 
     * @param level
     *            trace level
     * @param message
     *            log message
     */
    public final void traceEvent(Level level, String message)
    {
        logger.log(level, message);
    }
    
    /**
     * @author t-junchw
     * @date May 10, 2011
     * @description set the ETW log format
     */
    class FileLogFormatter extends Formatter
    {

        /**
         * (non-Javadoc)
         * 
         * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
         */
        @Override
        public String format(LogRecord record)
        {
            Date date = new Date();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datestring = sd.format(date);
            String computername = "localhost";
            try
            {
                computername = InetAddress.getLocalHost().getHostName();
            } catch (Exception e)
            {
                TraceHelper.traceError(e.toString());

            }
            
            // Do not out put the trace with OFF level
            if(record.getLevel()==Level.OFF)
                return "";

            // convert trace level back to ETW trace level
            String levelString = ETWTraceLevelConverterEnum.valueOf(
                    record.getLevel().toString()).getValue();
 
            
            // convert trace level from String type to Integer type
            int inttracelevel = traceLeveltoInteger(levelString);
            
            // convert to ETW trace format
            return String.format(Constant.ETWTraceFormat, levelString,
                    inttracelevel, datestring, computername, record.getMessage());

        }

        /**
         * @description convert trace level from String type to Integer type
         * @param levelString
         * @return
         */
        private int traceLeveltoInteger(String levelString)
        {
            if (levelString.endsWith("Verbose"))
            {
                return 16;
            } else if (levelString.endsWith("Critical"))
            {
                return 1;
            } else if (levelString.endsWith("Information"))
            {
                return 8;
            } else if (levelString.endsWith("Off"))
            {
                return 0;
            } else if (levelString.endsWith("Error"))
            {
                return 2;
            } else if (levelString.endsWith("Warning"))
            {
                return 4;
            }
            return 0;
        }
    }
    
    /**
     * @author t-junchw
     * @date May 20, 2011
     * @description set the console log format
     */
    class ConsoleLogFormatter extends Formatter
    {

        /**
         * (non-Javadoc)
         * 
         * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
         */
        @Override
        public String format(LogRecord record)
        {
            Date date = new Date();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datestring = sd.format(date);

            // convert to console trace format
            return String.format("%s, %s \r\n", datestring,
                    record.getMessage());
        }
    }


}

