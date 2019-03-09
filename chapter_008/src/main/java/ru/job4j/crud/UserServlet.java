package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.append(logic.findAll());
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");

        switch (action) {
            case "add":
                String id = req.getParameter("id");
                String name = req.getParameter("name");
                String login = req.getParameter("login");
                String email = req.getParameter("email");
                PrintWriter pwAdd = resp.getWriter();
                String user = logic.add(new User(Integer.parseInt(id), name, login, email));
                pwAdd.append(user);
                pwAdd.flush();
                pwAdd.close();
                break;

            case "update":
                id = req.getParameter("id");
                name = req.getParameter("name");
                login = req.getParameter("login");
                email = req.getParameter("email");
                PrintWriter pwUp = resp.getWriter();
                String update = logic.update(new User(Integer.parseInt(id), name, login, email));
                pwUp.append(update);
                pwUp.flush();
                pwUp.close();
                break;

            case "delete":
                id = req.getParameter("id");
                PrintWriter pwDel = resp.getWriter();
                String delete = logic.delete(Integer.parseInt(id));
                pwDel.append(delete);
                pwDel.flush();
                pwDel.close();
                break;

            default:
        }
    }
}
