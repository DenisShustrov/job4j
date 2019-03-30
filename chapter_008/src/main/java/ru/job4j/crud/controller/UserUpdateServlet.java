package ru.job4j.crud.controller;

import ru.job4j.crud.model.User;
import ru.job4j.crud.model.UsersRules;
import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersRules ur = new UsersRules();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        } else {
            req.setAttribute("login", user.getLogin());
            req.setAttribute("password", user.getPassword());
            req.setAttribute("rules_us", ValidateService.getInstance().findRules(user));
            req.setAttribute("rules", ur.getList());
            req.setAttribute("country", logic.getAllCountry());
            req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        logic.update(new User(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("password"),
                req.getParameter("rules"),
                req.getParameter("country"),
                req.getParameter("region"),
                req.getParameter("city")
        ));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
