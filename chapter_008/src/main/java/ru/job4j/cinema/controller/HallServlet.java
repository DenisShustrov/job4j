package ru.job4j.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.cinema.model.Visitor;
import ru.job4j.cinema.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HallServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        Map<String, String> halls = logic.getAllHalls();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(),
                StandardCharsets.UTF_8), true);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(halls);
        System.out.println(json);
        pw.append(json);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("phone"));
        System.out.println(req.getParameter("place"));
        logic.addVisitor(new Visitor(
                req.getParameter("username"),
                req.getParameter("phone"),
                req.getParameter("place"))
        );
        resp.sendRedirect(String.format("%s/cinema", req.getContextPath()));
    }
}
