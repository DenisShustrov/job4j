package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserCreateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersRules ur = new UsersRules();
        req.setAttribute("rules", ur.getList());
        req.getRequestDispatcher("/WEB-INF/view/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        logic.add(new User(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("password"),
                req.getParameter("rules")
        ));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));

    }
}
