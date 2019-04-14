package ru.job4j.todolist.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Class FilterItemServlet.
 *
 * @author dshustrov
 * @version 1
 * @since 09.04.2019
 */
public class FilterItemServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.getRequestDispatcher("/WEB-INF/todolist/index.html").forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
