package template.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map.Entry;

public class HttpHandler {

    public static final String RESPONSE_STATUS = "http_response_status";
    public static final String RESPONSE_ENTITY = "http_response_entity";
    public static final String SESSION_TRACE = "http_session_trace";

    public static HashMap<String, String> post(String url, String requestBody, HashMap<String, String> params, final HashMap<String, String> headers, boolean isHttps) throws Exception {
        Logger.info("HttpHandler post start.");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        if (isHttps)
            httpclient = wrapClient(httpclient);

        // encode the url
        url = buildURLWithParams(url, params);

        HttpPost httppost = new HttpPost(url);

        // add the headers
        addHeadersToHTTClient(httpclient, httppost, headers);

        StringEntity requestEntity = new StringEntity(requestBody);
        httppost.setEntity(requestEntity);

        HashMap<String, String> responseData = null;
        try {
            HttpContext localContext = new BasicHttpContext();
            HttpResponse response = httpclient.execute(httppost, localContext);
            responseData = flattenHttpResponse(response);
            int status = Integer.parseInt(responseData.get(RESPONSE_STATUS));
            if (status != 200) {
                String message = serializeRequestAndResponse(url, "Post", requestBody, params, headers, isHttps, responseData);
                responseData.put(SESSION_TRACE, message);
            }

        } catch (Exception e) {
            String message = serializeRequestAndResponse(url, "Post", requestBody, params, headers, isHttps, responseData);
            Logger.warn(String.format("error|httphandler|post|details=%s", message), e);
            throw e;
        } finally {
            httppost.releaseConnection();
            httpclient.clearRequestInterceptors();
        }
        Logger.info("HttpHandler post finished.");
        return responseData;
    }

    public static String encode(String text) throws UnsupportedEncodingException {
        return URLEncoder.encode(text, "UTF-8");
    }

    public static HashMap<String, String> get(String url, HashMap<String, String> params, final HashMap<String, String> headers, boolean isHttps) throws Exception {
        Logger.info("HttpHandler get start.");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        if (isHttps)
            httpclient = wrapClient(httpclient);

        // encode the url
        url = buildURLWithParams(url, params);

        // executeSegmentIndexUpdate HTTP GET
        HttpGet httpget = new HttpGet(url);

        // set the params  - http client library not working for the code below
//        if (params != null && !params.isEmpty()) {
//            HttpParams httpParams = httpget.getParams();
//            for (Entry<String, String> entry : params.entrySet()) {
//                httpParams.setParameter(entry.getKey(), entry.getValue());
//            }
//            //httpget.setParams(httpParams);
//        }

        // add the headers
        addHeadersToHTTClient(httpclient, httpget, headers);

        HashMap<String, String> responseData = null;
        try {
            HttpContext localContext = new BasicHttpContext();
            HttpResponse response = httpclient.execute(httpget, localContext);
            responseData = flattenHttpResponse(response);
            int status = Integer.parseInt(responseData.get(RESPONSE_STATUS));
            if (status != 200) {
                String message = serializeRequestAndResponse(url, "GET", "N/A", params, headers, isHttps, responseData);
                responseData.put(SESSION_TRACE, message);
            }
        } catch (Exception e) {
            String message = serializeRequestAndResponse(url, "GET", "N/A", params, headers, isHttps, responseData);
            Logger.warn(String.format("error|httphandler|toLong|details=%s", message), e);
            throw e;
        } finally {
            httpget.releaseConnection();
            httpclient.clearRequestInterceptors();
        }
        Logger.info("HttpHandler get finished.");
        return responseData;
    }

    private static String buildURLWithParams(String url, HashMap<String, String> params) throws UnsupportedEncodingException {
        if (params != null && !params.isEmpty()) {
            String[] paramArray = new String[params.size()];
            int offset = 0;
            for (Entry<String, String> param : params.entrySet()) {
                paramArray[offset] = String.format("%s=%s", encode(param.getKey()), encode(param.getValue()));
                offset++;
            }
            url = url + "?" + StringUtils.join(paramArray, "&");
        }
        return url;
    }

    private static void addHeadersToHTTClient(DefaultHttpClient httpclient, HttpRequest request, final HashMap<String, String> headers) {
        // add headers
        if (headers != null && !headers.isEmpty()) {
            HttpRequestInterceptor httpInterceptor = new HttpRequestInterceptor() {
                @Override
                public void process(HttpRequest httprequest, HttpContext context)
                        throws HttpException, IOException {
                    for (Entry<String, String> header : headers.entrySet())
                        httprequest.addHeader(header.getKey(), header.getValue());
                }
            };
            httpclient.addRequestInterceptor(httpInterceptor);

            for (Entry<String, String> header : headers.entrySet())
                request.addHeader(header.getKey(), header.getValue());
        }
    }

    private static HashMap<String, String> flattenHttpResponse(HttpResponse response) throws Exception {
        HashMap<String, String> data = new HashMap<String, String>();
        if (response == null)
            return data;
        int status = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity);
        data.put(RESPONSE_STATUS, Integer.toString(status));
        data.put(RESPONSE_ENTITY, content);
        return data;
    }

    @SuppressWarnings("deprecation")
    private static DefaultHttpClient wrapClient(HttpClient base) {
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
            sr.register(new Scheme("https", ssf, 4443));
            return new DefaultHttpClient(ccm, base.getParams());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private static String serializeRequestAndResponse(String url, String requestType, String requestBody, HashMap<String, String> params,
                                                      final HashMap<String, String> headers, boolean isHttps, HashMap<String, String> responseData) {
        if (responseData == null)
            responseData = new HashMap<String, String>();

        String headersStr = "";
        if (headers != null)
            for (Entry<String, String> header : headers.entrySet())
                headersStr += "\n\t" + header.getKey() + "=" + header.getValue();

        String paramsStr = "";
        if (params != null)
            for (Entry<String, String> param : params.entrySet())
                paramsStr += "\n\t" + param.getKey() + "=" + param.getValue();

        String httpDetailsPattern = "URL : %s\n" +
                "Http Method : %s\n" +
                "Http Params : %s\n" +
                "Http Headers : %s\n" +
                "Http Request Body: %s \n" +
                "Is Https? : %s \n" +
                "Http Response Status : %s \n" +
                "Http Response Data : %s \n";

        return String.format(httpDetailsPattern, url, requestType, paramsStr, headersStr,
                requestBody, isHttps, responseData.get(RESPONSE_STATUS), responseData.get(RESPONSE_ENTITY));
    }
}
