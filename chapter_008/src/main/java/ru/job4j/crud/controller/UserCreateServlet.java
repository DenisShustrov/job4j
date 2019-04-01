package ru.job4j.crud.controller;

import ru.job4j.crud.model.User;
import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserCreateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        logic.add(new User(
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
