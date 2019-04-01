package ru.job4j.crud.filter;

import ru.job4j.crud.model.UsersRules;
import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserFilterCreate implements Filter {

    private final Validate logic = ValidateService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getMethod().equals("GET")) {
            UsersRules ur = new UsersRules();
            req.setAttribute("rules", ur.getList());
            req.setAttribute("country", logic.getAllCountry());
            req.getRequestDispatcher("/WEB-INF/view/create.jsp").forward(req, resp);
        } else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
