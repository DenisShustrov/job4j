package ru.job4j.carsales.controller;

import ru.job4j.carsales.model.Seller;
import ru.job4j.carsales.service.ValidateSeller;
import ru.job4j.carsales.service.ValidateSellerImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SellerLoginServlet extends HttpServlet {

    private final ValidateSeller validateSeller = ValidateSellerImp.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/advertauto/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Seller seller = validateSeller.findSeller(
                req.getParameter("login"),
                req.getParameter("password")
        );
        if (seller != null) {
            HttpSession session = req.getSession();
            session.setAttribute("seller", seller);
            resp.sendRedirect(String.format("%s/adverts-seller.html", req.getContextPath()));
        } else {
            req.setAttribute("error", "Wrong login or password! Try again.");
            doGet(req, resp);
        }
    }

}
