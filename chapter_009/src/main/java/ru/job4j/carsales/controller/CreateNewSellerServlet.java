package ru.job4j.carsales.controller;

import ru.job4j.carsales.model.Seller;
import ru.job4j.carsales.service.ValidateSeller;
import ru.job4j.carsales.service.ValidateSellerImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateNewSellerServlet extends HttpServlet {

    private final ValidateSeller validateSeller = ValidateSellerImp.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("WEB-INF/advertauto/addseller.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        Seller seller = validateSeller.findSeller(
                req.getParameter("login"),
                req.getParameter("password")
        );
        if (seller != null) {
            req.setAttribute("error", "Пользователь уже создан!");
            doGet(req, resp);
        } else {
            validateSeller.addSeller(new Seller(
                    req.getParameter("name"),
                    req.getParameter("login"),
                    req.getParameter("password")));
            resp.sendRedirect(String.format("%s/adverts.html", req.getContextPath()));
        }
    }
}
