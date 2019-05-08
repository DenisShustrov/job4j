package ru.job4j.carsales.controller;

import ru.job4j.carsales.service.ValidateAdvertAuto;
import ru.job4j.carsales.service.ValidateAdvertAutoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAllAdvertAutoServlet extends HttpServlet {

    private final ValidateAdvertAuto validateAdvertAuto = ValidateAdvertAutoImp.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("adverts", validateAdvertAuto.findAllAdvertAuto());
        req.getRequestDispatcher("WEB-INF/advertauto/index.jsp").forward(req, resp);

    }
}
