package template.test.util;

import org.apache.commons.lang3.StringEscapeUtils;
import template.util.Logger;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Set;

/**
 * @author zhouzh
 *         Time: 5:31 PM
 */
public class CurlRequestFilter implements ClientRequestFilter {
    static final String CURL = "curl";
    static final String AVOID_CERT = " -k ";
    static final String HEAD = " -H ";
    static final String METHOD = " -X ";
    static final String DEFS = " -d ";
    static final String COLON = "\"";


    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        StringBuilder cmd = new StringBuilder();
        cmd.append("\n\n");

        cmd.append(CURL + AVOID_CERT);
        cmd.append("\"").append(requestContext.getUri().toString()).append("\"");

        // Add HTTP method
        cmd.append(METHOD);
        cmd.append(requestContext.getMethod().toUpperCase());

        // Add json for PUT/POST
        if (requestContext.getMethod().equalsIgnoreCase(HttpMethod.PUT) || requestContext.getMethod().equalsIgnoreCase(HttpMethod.POST)) {
            cmd.append(DEFS);
            cmd.append("\"").append(StringEscapeUtils.escapeJava(requestContext.getEntity().toString())).append("\"");
        }

        // Add headers
        MultivaluedMap headers = requestContext.getHeaders();
        Set keys = headers.keySet();
        for (Object key : keys) {
            if (headers.get(key).toString().length() > 2) { // make sure it is not an empty field
                cmd.append(HEAD);
                cmd.append(COLON).append(key)
                        .append(": ")
                        .append(headers.get(key).toString().substring(1, headers.get(key).toString().length() - 1))  // remove []
                        .append(COLON);
            }
        }

        cmd.append("\n\n");
        Logger.info(cmd.toString());

    }
}
