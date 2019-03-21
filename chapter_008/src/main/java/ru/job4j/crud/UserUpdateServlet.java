package ru.job4j.crud;

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
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");
        if (login == null) {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        } else {
            req.setAttribute("login", login);
            req.setAttribute("password", password);
            req.setAttribute("rules_us", ValidateService.getInstance().findRules(login, password));
            req.setAttribute("rules", ur.getList());
            req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String rules = req.getParameter("rules");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        logic.update(new User(
                Integer.parseInt(id),
                name,
                login,
                email,
                password,
                rules
        ));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
