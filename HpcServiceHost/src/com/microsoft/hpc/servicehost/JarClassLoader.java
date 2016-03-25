// ------------------------------------------------------------------------------
// <copyright file="JarClassLoader.java" company="Microsoft">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// <summary>
// Load Class from jar
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.microsoft.hpc.scheduler.session.servicecontext.Environment;
import com.microsoft.hpc.scheduler.session.servicecontext.TraceHelper;

/**
 * @author t-junchw
 * @date May 9, 2011
 * @description customized jar class load
 */
public class JarClassLoader extends ClassLoader {

    private JarFile file;

    // regular expression to see if the class name is end with .class
    private String expression = ".*\\.class$";

    public JarClassLoader(String filename) throws IOException {
        this.file = new JarFile(filename);
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.ClassLoader#findClass(java.lang.String)
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String pathString = name.replace('.', '/') + ".class";
        ZipEntry entry = this.file.getEntry(pathString);
        if (entry == null) {
            throw new ClassNotFoundException(name);
        }

        try {
            byte[] array = new byte[1024];
            InputStream in = this.file.getInputStream(entry);
            ByteArrayOutputStream out = new ByteArrayOutputStream(array.length);
            int length = in.read(array);
            while (length > 0) {
                out.write(array, 0, length);
                length = in.read(array);
            }
            Class<?> tempClass = this.findLoadedClass(name);

            if (tempClass == null) {
                tempClass = defineClass(name, out.toByteArray(), 0, out.size());
            }
            return tempClass;
        } catch (IOException exception) {
            throw new ClassNotFoundException(name, exception);
        }
    }

    /**
     * @description find the first class in jar with the @Webservice annotation
     * @return target class
     * @throws ClassNotFoundException
     */
    public Class<?> findClass() throws ClassNotFoundException {
        Class<?> a = null;
        Enumeration<JarEntry> jarEntries = this.file.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
            if (!jarEntry.isDirectory()) {
                if (jarEntry.getName().matches(expression)) {
                    String name = jarEntry.getName().replace("/", ".")
                            .split(".class")[0];

                    Class<?> tempClass = findClass(name);

                    //if(Environment.isDebug)
                    TraceHelper.traceInformation("try class : " + tempClass.getName());
                    
                    // to see if the found class is an interface
                    if (!tempClass.isInterface()) {
                        for (java.lang.annotation.Annotation annotation : tempClass
                                .getAnnotations()) {
                            // check if it has WebService annotation
                            if (annotation.annotationType() == javax.jws.WebService.class) {
                                a = tempClass;
                                return a;
                            }
                        }
                    }
                }
            }
        }
        return a;

    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.ClassLoader#findResource(java.lang.String)
     */
    @Override
    public URL findResource(String name) {
        JarEntry entry = (JarEntry) this.file.getEntry(name);
        if (entry == null) {
            return null;
        }
        try {
            // Compose the path for file in jar
            return new URL("jar:file:" + this.file.getName() + "!/"
                    + entry.getName());
        } catch (MalformedURLException exception) {
            return null;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.ClassLoader#findResources(java.lang.String)
     */
    @Override
    public Enumeration<URL> findResources(final String name) {
        return new Enumeration<URL>() {
            private URL element = findResource(name);

            public boolean hasMoreElements() {
                return this.element != null;
            }

            public URL nextElement() {
                if (this.element != null) {
                    URL element = this.element;
                    this.element = null;
                    return element;
                }
                throw new NoSuchElementException();
            }
        };
    }

    /**
     * @description find the class from jar by the specific class name
     * @param serviceContractName specific class name
     * @return target class
     * @throws ClassNotFoundException
     */
    public Class<?> loadClassbyInterface(String serviceContractName)
            throws ClassNotFoundException {
        Class<?> a = null;
        Enumeration<JarEntry> jarEntries = this.file.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
            if (!jarEntry.isDirectory()) {
                if (jarEntry.getName().matches(expression)) {
                    // replace '/' to '.'
                    String name = jarEntry.getName().replace("/", ".")
                            .split(".class")[0];
                    Class<?> tempClass = findClass(name);
                    for (Class<?> interfaceClass : tempClass.getInterfaces()) {
                        if (interfaceClass.getName() == serviceContractName) {
                            a = tempClass;
                            break;
                        }
                    }
                }
            }
        }
        return a;
    }
}
