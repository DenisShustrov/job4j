package ru.job4j.carsales.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.carsales.model.AdvertAuto;
import ru.job4j.carsales.service.ValidateAdvertAuto;
import ru.job4j.carsales.service.ValidateAdvertAutoImp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UpdateAdvertAutoServlet extends HttpServlet {

    private final ValidateAdvertAuto validate = ValidateAdvertAutoImp.getInstance();
    private String fileName;
    private Integer id;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        id = Integer.parseInt(req.getParameter("id"));
        req.getRequestDispatcher("/WEB-INF/advertauto/update.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdvertAuto advertAuto = validate.findAdvertAutoById(id);
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
                String fieldName = item.getFieldName();
                if (fieldName.equals("mark") & !value.equals("")) {
                    advertAuto.setMark(value);
                }
                if (fieldName.equals("model") & !value.equals("")) {
                    advertAuto.setModel(value);
                }
                if (fieldName.equals("years") & !value.equals("")) {
                    advertAuto.setYears(value);
                }
                if (fieldName.equals("bodyType") & !value.equals("")) {
                    advertAuto.setBodyType(value);
                }
                if (fieldName.equals("engineType") & !value.equals("")) {
                    advertAuto.setEngineType(value);
                }
                if (fieldName.equals("drive") & !value.equals("")) {
                    advertAuto.setDrive(value);
                }
                if (fieldName.equals("transmission") & !value.equals("")) {
                    advertAuto.setTransmission(value);
                }
                if (fieldName.equals("mileage") & !value.equals("")) {
                    advertAuto.setMileage(Double.parseDouble(value));
                }
                if (fieldName.equals("condition") & !value.equals("")) {
                    advertAuto.setCondition(value);
                }
                if (fieldName.equals("description") & !value.equals("")) {
                    advertAuto.setDescription(value);
                }
                if (fieldName.equals("price") & !value.equals("")) {
                    advertAuto.setPrice(Double.parseDouble(value));
                }
                if (fieldName.equals("nameSeller") & !value.equals("")) {
                    advertAuto.setNameSeller(value);
                }
                if (fieldName.equals("phoneNumber") & !value.equals("")) {
                    advertAuto.setPhoneNumber(value);
                }
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
        if (fileName != null) {
            advertAuto.setPhotoPath(fileName);
        }
        validate.updateAdvertAuto(advertAuto);
        response.sendRedirect(String.format("%s/adverts-seller.html", request.getContextPath()));
    }
}
