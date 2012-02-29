
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
