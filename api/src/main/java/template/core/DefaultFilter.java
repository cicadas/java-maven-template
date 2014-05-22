package template.core;

import javax.servlet.*;
import java.io.IOException;

/**
 * Only static content calls are matched to DefaultFilter,
 * which simply breaks the filter chain and forwards the request to the DefaultServlet.
 * Other calls ignore DefaultFilter and match to security filter.
 */
public class DefaultFilter implements Filter {
    private RequestDispatcher defaultRequestDispatcher;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        defaultRequestDispatcher.forward(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.defaultRequestDispatcher =
                filterConfig.getServletContext().getNamedDispatcher("default");
    }
}
