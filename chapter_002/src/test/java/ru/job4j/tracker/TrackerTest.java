package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class TrackerTest {
    /**
     * * Test add.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }
    /**
     * * Test replace.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * * Test delete.
     */
    @Test
    public void whenDeleteItemThenNewItems() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test1", "testDescription", 123L),
                new Item("test2", "testDescription2", 1234L),
        };
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.delete(items[1].getId());
        assertThat(tracker.findById(items[1].getId()), is(nullValue()));
    }

    /**
     * * Test findAll.
     */
    @Test
    public void whenAddItemsThenFindAllItems() {
        Tracker tracker = new Tracker();
        Item[] items = {new Item("test1", "testDescription", 123L),
                new Item("test2", "testDescription2", 1234L),
        };
        tracker.add(items[0]);
        tracker.add(items[1]);
        int result = tracker.findAll().length;
        assertThat(result, is(2));
    }

    /**
     * * Test findByName.
     */
    @Test
    public void whenFindNameItemThenAddNewItems() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        Item next = new Item("test2", "testDescription2", 1234L);
        tracker.add(previous);
        tracker.add(next);
        Item[] result = tracker.findByName("test2");
        assertThat(result[0].getName(), is("test2"));
    }

    /**
     * * Test findById.
     */
    @Test
    public void whenIdThenItem() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        Item next = new Item("test2", "testDescription2", 1234L);
        tracker.add(previous);
        tracker.add(next);
        assertThat(tracker.findById(previous.getId()), is(previous));
    }

}
