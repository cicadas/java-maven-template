package template.util;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zhouzh
 *         Date: 10/22/13
 *         Time: 11:56 AM
 */
public class LoggingFilter implements Filter {
    public static final String SESSION_ID = "sessionId";
    public static final String URL = "url";
    public static final String HTTP_METHOD = "httpMethod";
    public static final String IP = "ip";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String sessionId = ((HttpServletRequest) servletRequest).getSession().getId();
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        String httpMethod = ((HttpServletRequest) servletRequest).getMethod();
        String ip = servletRequest.getRemoteAddr();

        try {
            MDC.put(SESSION_ID, sessionId);
            MDC.put(URL, url);
            MDC.put(HTTP_METHOD, httpMethod);
            MDC.put(IP, ip);
            Logger.info("one request started before filter");
            filterChain.doFilter(servletRequest, servletResponse);
            Logger.info("one request finished after filter");
        } finally {
            MDC.remove(SESSION_ID);
            MDC.remove(URL);
            MDC.remove(HTTP_METHOD);
            MDC.remove(IP);
        }
    }

    @Override
    public void destroy() {

    }
}
