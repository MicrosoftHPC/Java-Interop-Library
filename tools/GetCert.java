
import java.io.*;
import java.security.*;
import java.security.cert.*;
import javax.net.ssl.*;
import static java.lang.System.out;

public class GetCert {

	public static void main(String[] args) throws Exception {
		String hostname;
		int port = 443;
		char[] passphrase;
		if (args.length==0 || args.length>2 )
		{
			out.println("Usage: java GetCert <hostname> [passphrase]");
			return;
		}
		hostname = args[0];
		String p = (args.length == 1) ? "changeit" : args[1];
		passphrase = p.toCharArray();

		File file = new File("cacerts_new");
		if (file.isFile() == false) {
			File dir = new File(System.getProperty("java.home") + File.separatorChar + "lib" + File.separatorChar + "security");
			file = new File(dir, "cacerts");
		}

		InputStream in = new FileInputStream(file);
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(in, passphrase);
		in.close();

		SSLContext context = SSLContext.getInstance("TLS");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(ks);
		X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];
		SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);

		context.init(null, new TrustManager[] {tm}, null);
		SSLSocketFactory factory = context.getSocketFactory();

		SSLSocket socket = (SSLSocket)factory.createSocket(hostname, port);
		socket.setSoTimeout(15000);
		try {
			socket.startHandshake();
			socket.close();
			out.println("The certificate trusted is already in store");
			return;
		} catch (SSLException e) {
			out.println("The certificate trusted is not in store");
		}

		X509Certificate[] chain = tm.chain;

		for (int k = 0; k < chain.length; k++) {
			X509Certificate cert = chain[k];
			String alias = hostname + "-" + (k + 1);
			ks.setCertificateEntry(alias, cert);
			out.println("Added certificate with alias: "+alias);
		}

		OutputStream out = new FileOutputStream("cacerts_new");
		ks.store(out, passphrase);
		out.close();

	}

	private static class SavingTrustManager implements X509TrustManager {

		private final X509TrustManager tm;
		private X509Certificate[] chain;

		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		public X509Certificate[] getAcceptedIssuers() {
			throw new UnsupportedOperationException();
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {
			throw new UnsupportedOperationException();
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {
			this.chain = chain;
			tm.checkServerTrusted(chain, authType);
		}

	}

}
