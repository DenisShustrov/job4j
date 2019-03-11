package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("<!DOCTYPE HTML>"
                +
                "<html>"
                +
                "<head>"
                +
                "<meta charset=\"utf-8\">"
                +
                "<title>Create users</title>"
                +
                "<body>"
                +
                "<h3>Create users</h3>"
                +
                "<form action='" + req.getContextPath() + "/create' method='post'>"
                +
                "<table>"
                +
                "<tr><td>id: </td><td><input type='text' name='id'></td></tr>"
                +
                "<tr><td>name: </td><td><input type='text' name='name'></td></tr>"
                +
                "<tr><td>login: </td><td><input type='text' name='login'></td></tr>"
                +
                "<tr><td>email: </td><td><input type='text' name='email'></td></tr>"
                +
                "<tr><td></td><td><input type='submit'></td></tr>"
                +
                "</table>"
                +
                "</form>"
                +
                "</td></tr></table>"
                +
                "</body>"
                +
                "</head>"
                +
                "</html>");
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        logic.add(new User(
                Integer.parseInt(id),
                name,
                login,
                email
        ));
        resp.sendRedirect(req.getContextPath() + "/list");

    }
}
