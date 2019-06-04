package ru.job4j.newcarsales.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.newcarsales.model.AdvertAuto;
import ru.job4j.newcarsales.model.Role;
import ru.job4j.newcarsales.model.Seller;
import ru.job4j.newcarsales.servise.ValidateAdvertAutoImp;
import ru.job4j.newcarsales.servise.ValidateSellerImp;
import ru.job4j.newcarsales.utils.DispatchPattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class CarSalesController {

    private final ValidateSellerImp validateSellerImp;

    private final ValidateAdvertAutoImp validateAdvertAutoImp;

    private final ServletContext servletContext;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Integer id;

    private DispatchPattern dispatch = new DispatchPattern().init();

    @Autowired
    public CarSalesController(final ValidateSellerImp validateSellerImp,
                              final ValidateAdvertAutoImp validateAdvertAutoImp,
                              final ServletContext servletContext,
                              final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.validateSellerImp = validateSellerImp;
        this.validateAdvertAutoImp = validateAdvertAutoImp;
        this.servletContext = servletContext;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "adverts.html", method = RequestMethod.GET)
    public String showAllAdvertAuto(ModelMap model) {
        model.addAttribute("adverts", validateAdvertAutoImp.findAllAdvertAuto());
        return "index";
    }

    @RequestMapping(value = "/adverts-seller.html", method = RequestMethod.GET)
    public String showAdvertAutoSellerServlet(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = validateSellerImp.findSellerByName(auth.getName());
        System.out.println(auth.getPrincipal().toString());
        model.addAttribute("adverts", validateAdvertAutoImp.findAdvertAutoBySellerId(seller.getId()));
        return "adverts";
    }

    @RequestMapping(value = "/add-advert.html", method = RequestMethod.GET)
    public String createNewAdvertAutoServletGet() {
        return "create";
    }

    @RequestMapping(value = "/add-advert.html", method = RequestMethod.POST)
    public String createNewAdvertAutoServletPost(HttpServletRequest request) throws UnsupportedEncodingException {
        String fileName = null;
        List<Object> list = new ArrayList<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for (FileItem item : items) {
            if (item.isFormField()) {
                String value = item.getString("UTF-8");
                list.add(value);
            }
            if (!item.isFormField()) {
                fileName = item.getName();
                byte[] data = fileName.getBytes();
                fileName = new String(data, StandardCharsets.UTF_8);
                String filePath = "C:\\Users\\denis\\projects\\job4j\\chapter_010\\src\\main\\webapp\\fhotocars\\";
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
        System.out.println(fileName);
        advertAuto.setPhotoPath(fileName);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = validateSellerImp.findSellerByName(auth.getName());
        advertAuto.setSeller(seller);
        System.out.println(advertAuto);
        validateAdvertAutoImp.addAdvertAuto(advertAuto);
        return "redirect: adverts-seller.html";
    }

    @RequestMapping(value = "/update.html", method = RequestMethod.GET)
    public String updateAdvertAutoServletGet(@RequestParam("id") String idA) {
        id = Integer.parseInt(idA);
        return "update";
    }

    @RequestMapping(value = "/update.html", method = RequestMethod.POST)
    public String updateAdvertAutoServletPost(HttpServletRequest request) throws UnsupportedEncodingException {
        String fileName = null;
        AdvertAuto advertAuto = validateAdvertAutoImp.findAdvertAutoById(id);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for (FileItem item : items) {
            if (item.isFormField()) {
                dispatch.sent(advertAuto, item);
            }
            if (!item.isFormField()) {
                fileName = item.getName();
                byte[] data = fileName.getBytes();
                fileName = new String(data, StandardCharsets.UTF_8);
                String filePath = "C:\\Users\\denis\\projects\\job4j\\chapter_010\\src\\main\\webapp\\fhotocars\\";
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
        validateAdvertAutoImp.updateAdvertAuto(advertAuto);
        return "redirect: adverts-seller.html";
    }

    @RequestMapping(value = "/delete.html", method = RequestMethod.POST)
    public String deleteAdvertAutoServlet(@RequestParam("id") String idA) {
        AdvertAuto advertAuto = validateAdvertAutoImp.findAdvertAutoById(Integer.parseInt(idA));
        validateAdvertAutoImp.deleteAdvertAuto(advertAuto);
        return "redirect: adverts-seller.html";
    }

    @RequestMapping(value = "/change.html", method = RequestMethod.POST)
    public String changeStatusAdvertServlet(@RequestParam("id") String idA) {
        validateAdvertAutoImp.changeStatus(Integer.parseInt(idA));
        return "redirect: adverts-seller.html";
    }

    @RequestMapping(value = "/filter.html", method = RequestMethod.POST)
    public String advertAutoFilterPost(@RequestParam("filter") String param,
                                       @RequestParam(value = "mark", required = false) String mark,
                                       Model model) throws UnsupportedEncodingException {
        StringBuilder ret = new StringBuilder();
        switch (param) {
            case "показать за последний день":
                model.addAttribute("title", "Автомобили за последний день");
                model.addAttribute("adverts", validateAdvertAutoImp.findAdvertAutoAddLastDay());
                ret.append("filter");
                break;
            case "показать с фото":
                model.addAttribute("title", "Автомобили c фото");
                model.addAttribute("adverts", validateAdvertAutoImp.findAdvertAutoWithPhoto());
                ret.append("filter");
                break;
            case "показать определенной марки":
                if (mark == null) {
                    ret.append("choose");
                } else {
                    model.addAttribute("title", "Автомобили марки " + mark);
                    model.addAttribute("adverts", validateAdvertAutoImp.findAdvertAutoAddByMark(mark));
                    ret.append("filter");
                }
                break;
            case "выбрать":
                ret.append("redirect: adverts.html");
                break;
            default:

        }
        return ret.toString();
    }

    @RequestMapping(value = "/filter.html", method = RequestMethod.GET)
    public String advertAutoFilterGet() {
        return "redirect: adverts.html";
    }

    @RequestMapping(value = "add-seller.html", method = RequestMethod.GET)
    public String createNewSellerServletGet() {
        return "addseller";
    }

    @RequestMapping(value = "add-seller.html", method = RequestMethod.POST)
    public String createNewSellerServletPost(@RequestParam("name") String name,
                                             @RequestParam("login") String login,
                                             @RequestParam("password") String password, ModelMap model) {

        Seller seller = validateSellerImp.findSeller(login, password);
        if (seller != null) {
            model.addAttribute("error", "Пользователь уже создан!");
            return "addseller";
        } else {
            Seller sellerAdd = new Seller(name, login, bCryptPasswordEncoder.encode(password));
            sellerAdd.setActive(true);
            sellerAdd.setRoles(Collections.singleton(Role.USER));
            validateSellerImp.addSeller(sellerAdd);
            return "redirect: adverts.html";
        }
    }
}
