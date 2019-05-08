package ru.job4j.carsales.controller;

import ru.job4j.carsales.model.AdvertAuto;
import ru.job4j.carsales.service.ValidateAdvertAuto;
import ru.job4j.carsales.service.ValidateAdvertAutoImp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAdvertAutoServlet extends HttpServlet {

    private final ValidateAdvertAuto validate = ValidateAdvertAutoImp.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        AdvertAuto advertAuto = validate.findAdvertAutoById(Integer.parseInt(req.getParameter("id")));
        validate.deleteAdvertAuto(advertAuto);
        resp.sendRedirect(String.format("%s/adverts-seller.html", req.getContextPath()));
    }
}
