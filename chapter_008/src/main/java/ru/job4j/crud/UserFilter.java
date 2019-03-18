package ru.job4j.crud;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class UserFilter.
 *
 * @author dshustrov
 * @version 1
 * @since 17.03.2019
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().equals("/login")) {
            filterChain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/login", request.getContextPath()));
            } else {
                filterChain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
