package ru.job4j.carsales.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.carsales.model.AdvertAuto;
import ru.job4j.carsales.model.Seller;
import ru.job4j.carsales.service.ValidateAdvertAuto;
import ru.job4j.carsales.service.ValidateAdvertAutoImp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CreateNewAdvertAutoServlet extends HttpServlet {

    private final ValidateAdvertAuto validate = ValidateAdvertAutoImp.getInstance();
    private String fileName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/advertauto/create.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Object> list = new ArrayList<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        assert items != null;
        for (FileItem item : items) {
            if (item.isFormField()) {
                String value = item.getString("UTF-8");
                list.add(value);
            }
            if (!item.isFormField()) {
                fileName = item.getName();
                byte[] data = fileName.getBytes();
                fileName = new String(data, StandardCharsets.UTF_8);
                String filePath = "C:\\Users\\denis\\projects\\job4j\\chapter_008\\src\\main\\webapp\\fhotocars\\";
                File file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                try {
                    item.write(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        AdvertAuto advertAuto = new AdvertAuto(
                (String) list.get(0),
                (String) list.get(1),
                (String) list.get(2),
                (String) list.get(3),
                (String) list.get(4),
                (String) list.get(5),
                (String) list.get(6),
                Double.parseDouble((String) list.get(7)),
                (String) list.get(8),
                (String) list.get(9),
                Double.parseDouble((String) list.get(10)),
                (String) list.get(11),
                (String) list.get(12)
        );
        advertAuto.setPhotoPath(fileName);
        HttpSession session = request.getSession();
        advertAuto.setSeller((Seller) session.getAttribute("seller"));
        validate.addAdvertAuto(advertAuto);
        response.sendRedirect(String.format("%s/adverts-seller.html", request.getContextPath()));

    }
}
