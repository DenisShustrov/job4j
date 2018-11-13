package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    /**
     * * Test add.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() throws Exception {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    /**
     * * Test edit.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() throws Exception {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", 123L));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    /**
     * * Test delete.
     */
    @Test
    public void whenDeleteThenTrackerHasDeleteValue() throws Exception {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name1", "desc1", 1234L));
        Item item2 = tracker.add(new Item("test name2", "desc2", 1235L));
        Input input = new StubInput(new String[]{"3", item1.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name2"));
    }
}
