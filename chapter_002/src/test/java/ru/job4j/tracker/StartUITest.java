package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author dshustrov
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final StringBuilder str = new StringBuilder()
            .append("0 : Add new Item.")
            .append(System.lineSeparator())
            .append("1 : Show all items.")
            .append(System.lineSeparator())
            .append("2 : Edit item.")
            .append(System.lineSeparator())
            .append("3 : Delete item.")
            .append(System.lineSeparator())
            .append("4 : Find item by Id.")
            .append(System.lineSeparator())
            .append("5 : Find items by name.")
            .append(System.lineSeparator())
            .append("6 : Exit Program.");

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }
    /**
     *  Test add.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() throws Exception {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    /**
     *  Test edit.
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
        assertThat(tracker.findAll().get(0).getName(), is("test name2"));
    }

    /**
     *  Test showItem.
     */
    @Test
    public void whenChooseTwoThenAllTtems() throws Exception {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name1", "desc1", 1234L));
        Item item2 = tracker.add(new Item("test name2", "desc2", 1235L));
        Input input = new StubInput(new String[]{"1", "6"});
        System.setOut(new PrintStream(out));
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(str)
                                .append(System.lineSeparator())
                                .append("------------ All available items: --------------")
                                .append(System.lineSeparator())
                                .append("Item{id='" + item1.getId() + "', name='test name1', discription='desc1', create=1234}")
                                .append(System.lineSeparator())
                                .append("Item{id='" + item2.getId() + "', name='test name2', discription='desc2', create=1235}")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
    /**
     *  Test findByIdItem.
     */
    @Test
    public void whenEnterIdThenFindId() throws Exception {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name2", "desc2", 1234L));
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        System.setOut(new PrintStream(out));
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(str)
                                .append(System.lineSeparator())
                                .append("------------ Find item by id: --------------")
                                .append(System.lineSeparator())
                                .append("------------ Item with id: " + item.getId() + " " + item.getName())
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
    /**
     *  Test findByNameItem.
     */
    @Test
    public void whenEnterNameItemThenFindItem() throws Exception {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc", 1234L));
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        System.setOut(new PrintStream(out));
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(str)
                                .append(System.lineSeparator())
                                .append("------------ Find item by name:: --------------")
                                .append(System.lineSeparator())
                                .append("test name")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}

