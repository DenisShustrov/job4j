package ru.job4j.crud.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;
import ru.job4j.crud.service.ValidateStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTest {

    @Test
    public void whenAddUserThenStoreIt() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("Petr Arsentev");
        when(req.getParameter("login")).thenReturn("petr");
        when(req.getParameter("email")).thenReturn("@");
        when(req.getParameter("password")).thenReturn("123");
        when(req.getParameter("rules")).thenReturn("USER");
        when(req.getParameter("country")).thenReturn("Russia");
        when(req.getParameter("region")).thenReturn("Moscow");
        when(req.getParameter("city")).thenReturn("Moscow");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.find().iterator().next().getName(), is("Petr Arsentev"));
    }

    @Test
    public void whenAddAndDeleteUserThenStoreIt() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("Petr Arsentev");
        when(req.getParameter("login")).thenReturn("petr");
        when(req.getParameter("email")).thenReturn("@");
        when(req.getParameter("password")).thenReturn("123");
        when(req.getParameter("rules")).thenReturn("USER");
        when(req.getParameter("country")).thenReturn("Russia");
        when(req.getParameter("region")).thenReturn("Moscow");
        when(req.getParameter("city")).thenReturn("Moscow");
        new UserCreateServlet().doPost(req, resp);
        when(req.getParameter("id")).thenReturn("1");
        new UserNewServlet().doPost(req, resp);
        assertThat(validate.find().size(), is(0));
    }

    @Test
    public void whenUpdateUserThenStoreIt() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("Petr Arsentev");
        when(req.getParameter("login")).thenReturn("petr");
        when(req.getParameter("email")).thenReturn("@");
        when(req.getParameter("password")).thenReturn("123");
        when(req.getParameter("rules")).thenReturn("USER");
        when(req.getParameter("country")).thenReturn("Russia");
        when(req.getParameter("region")).thenReturn("Moscow");
        when(req.getParameter("city")).thenReturn("Moscow");
        new UserCreateServlet().doPost(req, resp);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("Denis");
        when(req.getParameter("login")).thenReturn("den");
        when(req.getParameter("email")).thenReturn("@");
        when(req.getParameter("password")).thenReturn("124");
        when(req.getParameter("rules")).thenReturn("ADMINISTRATOR");
        when(req.getParameter("country")).thenReturn("Russia1");
        when(req.getParameter("region")).thenReturn("Moscow2");
        when(req.getParameter("city")).thenReturn("Moscow3");
        new UserUpdateServlet().doPost(req, resp);
        assertThat(validate.find().get(0).getName(), is("Denis"));
    }
}
