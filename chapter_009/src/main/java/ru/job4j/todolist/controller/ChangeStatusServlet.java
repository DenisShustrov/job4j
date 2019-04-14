package ru.job4j.todolist.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.todolist.servise.Validate;
import ru.job4j.todolist.servise.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ChangeStatusServlet extends HttpServlet {

    private static final Logger LOG = LogManager.getLogger(ChangeStatusServlet.class);

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader = req.getReader();
        StringBuilder str = new StringBuilder();
        if (reader != null) {
            str.append(reader.readLine());
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<String> list = mapper.readValue(str.toString(), new TypeReference<List<Object>>() {
            });
            logic.changeStatus(list);
            System.out.println(list);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
