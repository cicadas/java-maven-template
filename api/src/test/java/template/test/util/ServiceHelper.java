package template.test.util;

import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;


/**
 * @author zhouzh
 *         Time: 3:27 PM
 */
public class ServiceHelper {

    private static final String URL_PATTERN = "%s://%s:%s";
    private String protocol;
    private int port = -1;
    private String baseUrl;
    private String hostname;

    public String getProtocol() {
        if (StringUtils.isEmpty(protocol))
            setDefaultValue();
        return protocol;
    }

    public ServiceHelper setProtocol(String protocol) {
        this.protocol = protocol;
        if (protocol.equals("http"))
            port = 4080;
        else if (protocol.equals("https"))
            port = 4443;
        return this;
    }

    private void setDefaultValue() {
        protocol = "http";
        port = 4080;
    }

    public int getPort() {
        if (port == -1)
            setDefaultValue();
        return port;
    }

    public ServiceHelper setPort(int port) {
        this.port = port;
        return this;
    }

    public String getBaseUrl() {
        if (StringUtils.isNotEmpty(protocol) && StringUtils.isNotEmpty(hostname) && port != -1)
            baseUrl = String.format(URL_PATTERN, getProtocol(), hostname, getPort());
        else
            baseUrl = getBaseUrlFromConfig();
        return baseUrl;
    }

    private String getBaseUrlFromConfig() {
        return String.format(URL_PATTERN,
                TestConfig.INSTANCE.get(TestConfig.PROTOCOL),
                TestConfig.INSTANCE.get(TestConfig.TEST_HOSTNAME),
                TestConfig.INSTANCE.get(TestConfig.PORT));
    }

    public ServiceHelper setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public WebTarget build() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }};


        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession sslSession) {
                        return true;
                    }
                });
        SSLContext.setDefault(sc);

        Client client = ClientBuilder.newBuilder().sslContext(sc).build();
        return client
                .register(CurlRequestFilter.class)
                .target(getBaseUrl());
    }
}
