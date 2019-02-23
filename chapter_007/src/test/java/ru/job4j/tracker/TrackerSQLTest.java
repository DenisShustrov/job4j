package ru.job4j.tracker;

import org.junit.Test;

import java.sql.SQLException;

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
    @Test
    public void checkConnection() {
        try (TrackerSQL sql = new TrackerSQL();) {
            assertThat(sql.init(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
