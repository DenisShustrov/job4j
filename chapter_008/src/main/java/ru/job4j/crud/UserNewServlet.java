package ru.job4j.crud;

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
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");
        if (login == null) {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        } else {
            req.setAttribute("login", login);
            req.setAttribute("password", password);
            req.setAttribute("rules", ValidateService.getInstance().findRules(login, password));
            req.setAttribute("users", ValidateService.getInstance().find());
            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
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
