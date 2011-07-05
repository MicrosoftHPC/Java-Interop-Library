// ------------------------------------------------------------------------------
// <copyright file="Parameter.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Abstract parameter
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
package com.microsoft.hpc.servicehost;

import java.text.ParseException;

public abstract class Parameter
{
    /**
     * @field abstract parameter inherited by parameters in different types
     */
    private String name;

    /**
     * @field help description of parameter
     */
    private String description;

    /**
     * @description Initializes a new instance of the Parameter class.
     * @param name
     * @param description
     */
    public Parameter(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    /**
     * @return parameter name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return parameter value
     */
    public abstract Object getValue();

    /**
     * @return parameter description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @description output the help info of parameter
     */
    public void printHelp()
    {
        System.out.println(String.format(" %s - %s ", this.name,
                this.description));
    }

    /**
     * @description parse args to get parameter value
     * @param args
     * @throws ParseException
     */
    public void parse(String[] args) throws ParseException
    {
        boolean findParameter = false;

        int i = 0;
        for (; i < args.length; i++)
        {
            if (this.name.equals(args[i]))
            {
                findParameter = true;
                break;
            }
        }

        if (!findParameter)
        {
            throw new ParseException(String.format(
                    "Can't find mandatory parameter %s.", this.name), 0);
        }

        if (i + 1 >= args.length)
        {
            throw new ParseException(String.format(
                    "Can't find the value of parameter %s.", this.name), 0);
        }

        this.parseValue(args[i + 1]);
    }

    /**
     * @description convert String type value to specific type value
     * @param value
     * @throws ParseException
     */
    protected abstract void parseValue(String value) throws ParseException;
}
