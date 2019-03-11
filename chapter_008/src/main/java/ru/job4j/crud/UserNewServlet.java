package ru.job4j.crud;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserNewServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        StringBuilder sb = new StringBuilder();
        for (User us : logic.find()) {
            sb.append("<tr align='center'><td>")
                    .append(us.getId())
                    .append("</td>")
                    .append("<td>")
                    .append(us.getName())
                    .append("</td>")
                    .append("<td>")
                    .append(us.getLogin())
                    .append("</td>")
                    .append("<td>")
                    .append(us.getEmail())
                    .append("</td>")
                    .append("<td><form action='")
                    .append(req.getContextPath())
                    .append("/edit' method='get'>")
                    .append("<input type='submit' value='update'></form></td>")
                    .append("<td><form action='")
                    .append(req.getContextPath())
                    .append("/list' method='post'>")
                    .append(" <input type='hidden' name='id' value=")
                    .append(us.getId()).append("><input type='submit' value='delete'></form></td>")
                    .append("<tr>");
        }
        pw.println("<!DOCTYPE HTML>"
                +
                "<html>"
                +
                "<head>"
                +
                "<meta charset=\"utf-8\">"
                +
                "<title>Table with all users</title>"
                +
                "<body>"
                +
                "<table width=\"600px\" border=\"2\" cellpadding=\"4\" cellspacing=\"0\">"
                +
                "<caption>Table with all users</caption>"
                +
                "<tr><th>id</th><th>name</th><th>login</th><th>email</th><th>update</th><th>delete</th></tr>"
                +
                sb
                +
                "</table>"
                +
                "<br>"
                +
                "<table><tr><td>"
                +
                "<form action='" + req.getContextPath() + "/create' method='get'>"
                +
                "<input type=\"submit\" value=\"crate user\">"
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
        logic.delete(Integer.parseInt(id));
        doGet(req, resp);

    }
}
