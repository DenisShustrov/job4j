package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class TrackerSQLTest.
 *
 * @author dshustrov
 * @version 1
 * @since 18.02.2019
 */
public class TrackerSQLTest {
    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc", 189L));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void findAllItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc", 189L));
            tracker.add(new Item("name2", "desc", 189L));
            tracker.add(new Item("name3", "desc", 189L));
            assertThat(tracker.findAll().size(), is(3));
        }
    }

    @Test
    public void deleteItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("name", "desc", 189L)).getId();
            assertThat(tracker.delete(id), is(true));
        }
    }

    @Test
    public void replaceItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String id = tracker.add(new Item("name", "desc", 189L)).getId();
            boolean result = tracker.replace(id, new Item("name2", "desc", 189L));
            assertThat(result, is(true));
        }
    }

}