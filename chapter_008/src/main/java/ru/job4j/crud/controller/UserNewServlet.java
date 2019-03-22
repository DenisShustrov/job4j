package ru.job4j.crud.controller;

import ru.job4j.crud.model.User;
import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class UserNewServlet.
 *
 * @author dshustrov
 * @version 1
 * @since 06.03.2019
 */
public class UserNewServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        } else {
            req.setAttribute("login", user.getLogin());
            req.setAttribute("password", user.getPassword());
            if (ValidateService.getInstance().findRules(user) == null) {
                session.invalidate();
                resp.sendRedirect(String.format("%s/list", req.getContextPath()));
            } else {
                req.setAttribute("rules", ValidateService.getInstance().findRules(user));
                req.setAttribute("users", ValidateService.getInstance().find());
                req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        logic.delete(Integer.parseInt(id));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));

    }
}
