package ru.job4j.carsales.filter;

import ru.job4j.carsales.service.ValidateAdvertAuto;
import ru.job4j.carsales.service.ValidateAdvertAutoImp;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdvertAutoFilter implements Filter {

    private final ValidateAdvertAuto validate = ValidateAdvertAutoImp.getInstance();

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        String param = request.getParameter("filter");
        switch (param) {
            case "показать за последний день":
                request.setAttribute("title", "Автомобили за последний день");
                request.setAttribute("adverts", validate.findAdvertAutoAddLastDay());
                request.getRequestDispatcher("/WEB-INF/advertauto/filter.jsp").forward(request, response);
                break;
            case "показать с фото":
                request.setAttribute("title", "Автомобили c фото");
                request.setAttribute("adverts", validate.findAdvertAutoWithPhoto());
                request.getRequestDispatcher("/WEB-INF/advertauto/filter.jsp").forward(request, response);
                break;
            case "показать определенной марки":
                String mark = request.getParameter("mark");
                if (mark == null) {
                    request.getRequestDispatcher("/WEB-INF/advertauto/choose.jsp").forward(request, response);
                } else {
                    request.setAttribute("title", "Автомобили марки " + mark);
                    request.setAttribute("adverts", validate.findAdvertAutoAddByMark(mark));
                    request.getRequestDispatcher("/WEB-INF/advertauto/filter.jsp").forward(request, response);
                }
                break;
            case "выбрать":
                response.sendRedirect(String.format("%s/adverts.html", request.getContextPath()));
                break;
            default:
        }
    }

    @Override
    public void destroy() {

    }
}
