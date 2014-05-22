package template.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.tuple.ImmutablePair;
import template.core.ContextMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouzh
 */
@SuppressWarnings("unchecked")
public final class SecurityHelper {
    static final ConcurrentHashMap<String, ImmutablePair<String, String>> configs = new ContextMap<>();

    public static void setTrust() {
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }
        };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            SSLContext.setDefault(sc);
        } catch (Exception ignored) {
        }
    }

    private static void reload() throws IOException {
        if (configs.isEmpty()) {
            ObjectMapper mapper = JsonHelper.getMapper();
            JsonNode rootNode = mapper.readTree(SecurityHelper.class.getResourceAsStream("/data.zip"));
            Iterator<JsonNode> it = rootNode.elements();
            configs.clear();
            while (it.hasNext()) {
                ObjectNode node = (ObjectNode) it.next();
                String s = node.get("s").asText();
                String n = node.get("n").asText();
                String p = node.get("p").asText();
                ImmutablePair<String, String> pair = new ImmutablePair(n, p);
                configs.put(s, pair);
            }
        }
    }

}
