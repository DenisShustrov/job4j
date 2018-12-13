package ru.job4j.news;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LevelOfImplTest {
    @Test
    public void thenLevelOfWhenNewList1() {
        LevelOfImpl level = new LevelOfImpl();
        List<Student> list = new ArrayList<>(Arrays.asList(
                new Student("Петров Петр Петрович", 25),
                new Student("Иванов Иван Иванович", 30),
                new Student("Сидоров Евгений Евгеньевич", 2),
                new Student("Андреев Николай Евгеньевич", 10),
                new Student("Пряхин", 100),
                null
        ));
        int result = level.levelOf(list, 10).get(0).getScope();
        assertThat(result, is(100));
    }
    @Test
    public void thenLevelOfWhenNewList2() {
        LevelOfImpl level = new LevelOfImpl();
        List<Student> list = new ArrayList<>(Arrays.asList(
                null,
                new Student("Сидоров Евгений Евгеньевич", 2),
                new Student("Иванов Иван Иванович", 30),
                new Student("Пряхин", 100),
                null
        ));
        String result = level.levelOf(list, 50).get(0).getName();
        assertThat(result, is("Пряхин"));
    }
}
