// ------------------------------------------------------------------------------
// <copyright file="ServiceRegistrationRepo.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Common implementation to find the service Registration file
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
import java.util.Map;


/**
 * @author t-junchw
 * @date May 10, 2011
 * @description utility to get setting from the service registration file
 */
public class ServiceRegistrationRepo
{

    private String[] centralPaths;
    private String centrialPathList;

    /**
     * @description constructor of the ServiceRegistration class
     * @param headnode
     * @param centrialPath
     */
    public ServiceRegistrationRepo(String headnode, String centrialPath)
    {
        if (centrialPath == null || centrialPath.isEmpty())
        {
            centralPaths = null;
        } else
        {
            centrialPathList = centrialPath;
            centralPaths = centrialPath.split(";");
            Map<String, String> environment = Environment
                    .getEnvironmentVariables();
            for (int i = 0; i < centralPaths.length; i++)
            {
                centralPaths[i] = expandUserEnvironmentStrings(centralPaths[i],
                        environment);
            }

        }
    }

    /**
     * @return the service registration directories
     */
    public String[] getServiceRegistrationDirectories()
    {
        return centralPaths;
    }

    /**
     * @return the user setting which is in environment
     */
    public String getCentrialPath()
    {
        return centrialPathList;
    }

    /**
     * @param pathlist
     */
    public void setCentrialPath(String pathlist)
    {
        this.centrialPathList = pathlist;
    }

    // <summary>
    // Get the xml file which config the service.
    // </summary>
    // <param name="fileName">Service config file name</param>
    // <returns>The file path. If no file found, a FileNotFoundException will
    // raise</returns>
    /**
     * @description get the xml config file of the service
     * @param filename
     * @return The file path. If no file found, a FileNotFoundException will
     *         raise
     */
    public String getServiceRegistrationPath(String filename)
    {
        if (centralPaths != null)
        {
            for (String centralPath : centralPaths)
            {
                if (centralPath != null && !centralPath.isEmpty())
                {
                    // Else return path to config with just service name
                    File file = new File(centralPath, filename);
                    TraceHelper.traceInformation(file.getName());
                    if (file.exists())
                        return file.getAbsolutePath();
                }
            }
        }

        return null;
    }

    /**
     * @description Expand a String using the user Environment Variables table
     *              This code is based on RtlExpandEnvironmentStrings_U
     * @param name
     * @param environment
     * @return
     */
    private static String expandUserEnvironmentStrings(String name,
            Map<String, String> environment)
    {
        if (name == null)
            return null;
        StringBuilder result = new StringBuilder();

        for (int ix = 0; ix < name.length();)
        {
            if (name.charAt(ix) == '%')
            {
                int iy = name.indexOf('%', ix + 1);
                if (iy >= 0)
                {
                    // Found a second % in the String, go lookup if an
                    // environment
                    // variable exist for this subString
                    //
                    String value = environment.get(name.substring(ix + 1, iy
                            - ix - 1));
                    if (value != null)
                    {
                        // Yes!! found a matching env var. Append it to the
                        // result
                        // String and set the running index to the character
                        // following
                        // the % sign.
                        result.append(value);
                        ix = iy + 1;
                        continue;
                    }
                }
            }

            // This is not a % character, or no second % exist or no matching
            // environment variable exist. Copy this character to the result
            // String.
            //
            result.append(name.charAt(ix));
            ix++;
        }
        return result.toString();
    }

}
