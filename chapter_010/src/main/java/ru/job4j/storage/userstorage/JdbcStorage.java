package ru.job4j.storage.userstorage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.job4j.storage.model.User;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class JdbcStorage implements Storage, AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(JdbcStorage.class);

    private Connection connection;

    public JdbcStorage() {
        init();
    }

    private void init() {
        try (InputStream in = JdbcStorage.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(Objects.requireNonNull(in));
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }


    @Override
    public User add(User user) {
        try (PreparedStatement statement = connection
                .prepareStatement("INSERT INTO users (name_user, lastname_user, years_user) "
                        +
                        "VALUES (?, ?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getYears());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM users")) {
            ResultSet date = statement.executeQuery();
            while (date.next()) {
                User user = new User(date.getString("name_user"),
                        date.getString("lastname_user"),
                        Integer.parseInt(date.getString("years_user")));
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                close();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return users;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
