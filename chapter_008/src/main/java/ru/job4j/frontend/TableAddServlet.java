package ru.job4j.frontend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class TableAddServlet extends HttpServlet {

    private final List<Object> list = new ArrayList<>();

    private static final Logger LOG = LogManager.getLogger(TableAddServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(), StandardCharsets.ISO_8859_1), true);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        pw.append(json);
        pw.flush();
        list.clear();
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
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Object> temp = mapper.readValue(str.toString(), new TypeReference<List<Object>>() {
            });
            list.addAll(temp);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
