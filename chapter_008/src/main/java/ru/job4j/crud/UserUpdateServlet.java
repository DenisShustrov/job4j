package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        logic.update(new User(
                Integer.parseInt(id),
                name,
                login,
                email
        ));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
