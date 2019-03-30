package ru.job4j.crud.controller;

import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserAddRegion extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        String country = req.getParameter("country_id");
        List<String> list = logic.getAllRegionByCountry(country);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(),
                StandardCharsets.UTF_8), true);
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (String s : list) {
            str.append("{\"name\": ");
            str.append("\"").append(s);
            str.append("\"}, ");
        }
        str.deleteCharAt(str.length() - 1);
        str.deleteCharAt(str.length() - 1);
        str.append("]");
        pw.append(str.toString());
        pw.flush();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }
}
