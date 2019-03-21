package ru.job4j.crud;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class UserServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    private final Map<String, Consumer<HttpServletRequest>> acts = new HashMap<>();

    public UserServlet() {
        acts.put("add", this::add);
        acts.put("update", this::update);
        acts.put("delete", this::delete);
    }

    public void update(HttpServletRequest req) {
        this.logic.update(new User(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("password"),
                req.getParameter("rules")));
    }

    public void add(HttpServletRequest req) {
        this.logic.add(new User(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("password"),
                req.getParameter("rules")));
    }

    public void delete(HttpServletRequest req) {
        this.logic.delete(
                Integer.parseInt(req.getParameter("id")));
    }

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

        acts.getOrDefault(action, request -> {
            throw new UnsupportedOperationException(String.format("Action %s is not found", action));
        }).accept(req);
        doGet(req, resp);
    }
}
