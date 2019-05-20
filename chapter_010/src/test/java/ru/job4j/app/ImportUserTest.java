package ru.job4j.app;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.storage.app.ImportUser;
import ru.job4j.storage.model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ImportUserTest {

    @Test
    public void whenAddUserInStorageThenStorageNotEmpty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ImportUser importUser = context.getBean(ImportUser.class);
        importUser.addUser(new User("Den", "Shustrov", 36));
        assertThat(importUser.getStorage().findAllUsers().get(0).getName(), is("Den"));
    }

    @Test
    public void whenAddUserInStorageByAnnotationThenStorageNotEmpty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-annotation.xml");
        ImportUser importUser = context.getBean(ImportUser.class);
        importUser.addUser(new User("Ivan", "Shustrov", 3));
        assertThat(importUser.getStorage().findAllUsers().get(0).getName(), is("Ivan"));
    }
}
