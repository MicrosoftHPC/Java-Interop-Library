//------------------------------------------------------------------------------
// <copyright file="Version.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Version class
// </summary>
//------------------------------------------------------------------------------
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

package com.microsoft.hpc.scheduler.session;

/**
 * Represent version number in the format of major.minor.build.revision
 * 
 * @author junsu
 * 
 */
public class Version implements java.lang.Comparable<Version>
{
    private int major;
    private int minor;
    private int build;
    private int revision;

    private Version() {
        this.major = this.minor = 0;
        this.build = this.revision = -1;
    }

    /**
     * Create a instance of version from a string. the string can be the format
     * of a a.b a.b.c a.b.c.d
     * 
     * @param value
     *            The version string
     */
    public Version(String value) {
        this();

        String[] ver = value.split("\\.");
        if (ver.length > 0)
            this.major = Integer.parseInt(ver[0]);
        if (ver.length > 1)
            this.minor = Integer.parseInt(ver[1]);
        if (ver.length > 3)
            this.revision = Integer.parseInt(ver[2]);
        if (ver.length > 2)
            this.build = Integer.parseInt(ver[3]);
    }

    public Version(int major, int minor) {
        this();

        this.major = major;
        this.minor = minor;
    }

    public Version(int major, int minor, int revision, int build) {
        this();

        this.major = major;
        this.minor = minor;
        if (revision != -1)
            this.revision = revision;
        if (build != -1)
            this.build = build;
    }

    public void setMajor(int Major) {
        this.major = Major;
    }

    public void setMinor(int Minor) {
        this.minor = Minor;
    }

    public void setBuild(int Build) {
        this.build = Build;
    }

    public void setRevision(int Revision) {
        this.revision = Revision;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public int getRevision() {
        return this.revision;
    }
    
    public int getBuild() {
        return this.build;
    }

    public String toString() {
        if (this.revision == this.build && this.build == -1) {
            return String.format("%d.%d", this.major, this.minor);
        } else {
            return String.format("%d.%d.%d.%d", this.major, this.minor,
                    this.revision, this.build);
        }
    }

    @Override
    public int compareTo(Version target) {
        if (target.major != this.major)
            return (target.major > this.major) ? -1 : 1;

        if (target.minor != this.minor)
            return (target.minor > this.minor) ? -1 : 1;

        if (target.revision != this.revision)
            return (target.revision > this.revision) ? -1 : 1;

        if (target.build != this.build)
            return (target.build > this.build) ? -1 : 1;

        return 0;
    }

    @Override
    public boolean equals(java.lang.Object arg0) {
        if (arg0 == null)
            return false;

        if (arg0.getClass() == Version.class)
            return (compareTo((Version) arg0) == 0);
        return false;
    }
}
