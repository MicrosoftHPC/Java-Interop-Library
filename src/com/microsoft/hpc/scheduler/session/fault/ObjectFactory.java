// <copyright file="SvcHostObjectFactory.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// ObjectFactory for SvcHostSessionFault class
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
package com.microsoft.hpc.scheduler.session.fault;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfanyType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.microsoft.hpc.session package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
/**
 * @author t-junchw
 * @date May 10, 2011
 * @description object factory for SvcHostSessionFault class
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SessionFault_QNAME = new QName("http://hpc.microsoft.com/session/", "SessionFault");
    private final static QName _SessionFaultReason_QNAME = new QName("http://hpc.microsoft.com/session/", "Reason");
    private final static QName _SessionFaultContext_QNAME = new QName("http://hpc.microsoft.com/session/", "Context");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.microsoft.hpc.session
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SessionFault }
     * 
     */
    public SvcHostSessionFault createSessionFault() {
        return new SvcHostSessionFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "SessionFault")
    public JAXBElement<SvcHostSessionFault> createSessionFault(SvcHostSessionFault value) {
        return new JAXBElement<SvcHostSessionFault>(_SessionFault_QNAME, SvcHostSessionFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "Reason", scope = SvcHostSessionFault.class)
    public JAXBElement<String> createSessionFaultReason(String value) {
        return new JAXBElement<String>(_SessionFaultReason_QNAME, String.class, SvcHostSessionFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hpc.microsoft.com/session/", name = "Context", scope = SvcHostSessionFault.class)
    public JAXBElement<ArrayOfanyType> createSessionFaultContext(ArrayOfanyType value) {
        return new JAXBElement<ArrayOfanyType>(_SessionFaultContext_QNAME, ArrayOfanyType.class, SvcHostSessionFault.class, value);
    }

}
