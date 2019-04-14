package ru.job4j.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.servise.Validate;
import ru.job4j.todolist.servise.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ShowItemNotDoneServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json; charset=UTF-8");
        List<Item> items = logic.findAllItemWhenDoneTrue();
        ObjectMapper om = new ObjectMapper();
        String strJson = om.writeValueAsString(items);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(),
                StandardCharsets.UTF_8), true);
        pw.append(strJson);
        pw.flush();
    }
}
