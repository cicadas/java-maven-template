package template.test.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import template.util.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetAddress;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author zhouzh
 * Date: 8/1/13
 * Time: 2:17 PM
 */
public class ClientHelper {
    public static void setHostname(String hostname) {
        ClientHelper.hostname = hostname;
    }

    private static String hostname = null;


    public static void setPort(String p) {
        if (StringUtils.isNotEmpty(p)) {
            // update config map
            TestConfig.INSTANCE.set(TestConfig.PORT, p);
        }

    }


    public static String getHostname() throws Exception {
        hostname = TestConfig.INSTANCE.get(TestConfig.TEST_HOSTNAME);
        if (StringUtils.isEmpty(hostname))
            hostname = InetAddress.getLocalHost().getHostName();
        Logger.info("Localhost = " + hostname);
        return hostname;
    }

    public static String getBaseUrl() throws Exception {
        return String.format("%s://%s:%s", TestConfig.INSTANCE.get(TestConfig.PROTOCOL), getHostname(), TestConfig.INSTANCE.get(TestConfig.PORT));
    }

    public static DefaultHttpClient get(HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs,
                                               String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs,
                                               String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = base.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme(TestConfig.INSTANCE.get(TestConfig.PROTOCOL), ssf, Integer.parseInt(TestConfig.INSTANCE.get(TestConfig.PORT))));
            return new DefaultHttpClient(ccm, base.getParams());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
