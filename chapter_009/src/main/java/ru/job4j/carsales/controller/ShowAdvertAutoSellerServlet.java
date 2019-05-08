package ru.job4j.carsales.controller;

import ru.job4j.carsales.model.Seller;
import ru.job4j.carsales.service.ValidateAdvertAuto;
import ru.job4j.carsales.service.ValidateAdvertAutoImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAdvertAutoSellerServlet extends HttpServlet {

    private final ValidateAdvertAuto validateAdvertAuto = ValidateAdvertAutoImp.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        Seller seller = (Seller) session.getAttribute("seller");
        req.setAttribute("adverts", validateAdvertAuto.findAdvertAutoBySellerId(seller.getId()));
        req.getRequestDispatcher("/WEB-INF/advertauto/adverts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
