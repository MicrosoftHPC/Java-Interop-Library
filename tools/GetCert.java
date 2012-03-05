//------------------------------------------------------------------------------
// <copyright file="GetCert.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//      Get the SSL certificates from the server and store them in local keystore
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

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import javax.net.ssl.*;
import static java.lang.System.out;

public class GetCert {
	
	public static void printCert(Certificate cert)
	{
		out.println("Certificate:"+cert.toString());
		if (cert instanceof X509Certificate) {
			X509Certificate certX509=(X509Certificate)cert;
			out.println("Version:"+certX509.getVersion());
			out.println("Serial:"+certX509.getSerialNumber());
			out.println("Issuer:"+certX509.getIssuerDN());
			out.println("ValidBy:"+certX509.getNotBefore());
			out.println("SigAlg:"+certX509.getSigAlgName());
		}
		return;
	}

	public static void main(String[] args)
			throws Exception {
		String hostname;
		int portnumber = 443;
		char[] password;
		if (args.length==0 || args.length>2 )
		{
			out.println("Usage: java GetCert <hostname> [password]");
			return;
		}
		hostname = args[0];
		String pass = (args.length == 1) ? "changeit" : args[1];
		password = pass.toCharArray();

		SSLContext context = SSLContext.getInstance("TLS");
		TrustManager tm =new X509TrustManager (){
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				return;
			}
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				return;
			}
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		context.init(null, new TrustManager[] {tm}, null);
		SSLSocketFactory factory = context.getSocketFactory();
		SSLSocket socket = (SSLSocket)factory.createSocket(hostname, portnumber);
		socket.setSoTimeout(15000);
		Certificate[] serverCerts=null;
		try {
			socket.startHandshake();
			serverCerts = socket.getSession().getPeerCertificates(); 
			socket.close();
			out.println("Server certificates successfully retrieved.");
		} catch (SSLException e) {
			out.println("Obtain server certificates failed.");
			e.printStackTrace();
			return;
		}
		
		out.println("Open keystore to check and install the certs...");
		
		File file = new File("cacerts_new");
		if (file.isFile() == false) {
			File dir = new File(System.getProperty("java.home") + File.separatorChar + "lib" + File.separatorChar + "security");
			file = new File(dir, "cacerts");
		}
		InputStream in = new FileInputStream(file);
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(in, password);
		in.close();
		
		for (int k = 0; k < serverCerts.length; k++) {
			Certificate cert = serverCerts[k];
			System.out.print("-----BEGIN CERTIFICATE-----\n");
            printCert(cert);
            System.out.print("\n-----END CERTIFICATE-----\n");
            
			if (ks.getCertificateAlias(cert)==null) {
				String alias = hostname + "-" + (k + 1);
				ks.setCertificateEntry(alias, cert);
				out.println("Added the certificate with alias: "+alias);
			} else {
				out.println("This certificate is already in store.");
			}
		}
	
		OutputStream out = new FileOutputStream("cacerts_new");
		ks.store(out, password);
		out.close();
		
		return;

	}

}
