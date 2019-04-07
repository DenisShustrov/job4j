package ru.job4j.cinema.filter;

import javax.servlet.*;
import java.io.IOException;

public class Filter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/cinemaview/index.html").forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
