package ru.job4j.carsales.controller;

import ru.job4j.carsales.service.ValidateAdvertAuto;
import ru.job4j.carsales.service.ValidateAdvertAutoImp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeStatusAdvertServlet extends HttpServlet {

    private ValidateAdvertAuto validate = ValidateAdvertAutoImp.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        validate.changeStatus(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(String.format("%s/adverts-seller.html", req.getContextPath()));
    }
}
