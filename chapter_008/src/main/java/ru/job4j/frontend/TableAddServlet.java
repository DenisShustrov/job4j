package ru.job4j.frontend;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class TableAddServlet extends HttpServlet {

    private final Map<String, String> map = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), "ISO-8859-1"), true);
//        Этот код не работает, что я только не делал!!! Данные не добавляются в мапу!!!
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(map);
        pw.append(map.get("str"));
        pw.flush();
        map.clear();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder str = new StringBuilder();
        if (reader != null) {
            str.append(reader.readLine());
        }
        assert reader != null;
        reader.close();
//        ObjectMapper mapper = new ObjectMapper();
//        map = mapper.readValue(str.toString(), new TypeReference<Map<String, String>>() {
//        });
        map.put("str", str.toString());
    }

}
