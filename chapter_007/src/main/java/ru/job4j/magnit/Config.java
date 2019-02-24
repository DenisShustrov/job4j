package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class Config.
 *
 * @author dshustrov
 * @version 1
 * @since 21.02.2019
 */
public class Config {
    private final Properties values = new Properties();

    private static final Logger LOG = LogManager.getLogger(Config.class);

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            values.load(in);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {
        Config config = new Config();
        config.init();
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
