package ru.job4j.crud;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class DbStore.
 *
 * @author dshustrov
 * @version 1
 * @since 13.03.2019
 */
public class DbStore implements Store<User>, AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(DbStore.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    private DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("1477556");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users (id, name_u, login, email, createdate) values (?, ?, ?, ?, ?)")) {
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setTimestamp(5, new Timestamp(user.getCreateDate()));
            st.executeUpdate();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public boolean replace(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE users SET id=?, name_u = ?, login = ?,  email = ?, createdate = ? WHERE id = ?")) {
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setTimestamp(5, new Timestamp(user.getCreateDate()));
            st.setInt(6, user.getId());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> storage = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet date = st.executeQuery();
            while (date.next()) {
                User us = new User(date.getInt("id"), date.getString("name_u"), date.getString("login"), date.getString("email"));
                us.setCreateDate(date.getTimestamp("createDate").getTime());
                storage.add(us);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return storage;
    }

    @Override
    public ArrayList<User> findByName(String key) {
        ArrayList<User> temp = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE name=?")) {
            st.setString(1, key);
            ResultSet date = st.executeQuery();
            while (date.next()) {
                User us = new User(date.getInt("id"), date.getString("name_u"), date.getString("login"), date.getString("email"));
                us.setCreateDate(date.getTimestamp("createDate").getTime());
                temp.add(us);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return temp;
    }

    @Override
    public User findById(int id) {
        User us = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet date = st.executeQuery();
            us = new User(date.getInt("id"), date.getString("name_u"), date.getString("login"), date.getString("email"));
            us.setCreateDate(date.getTimestamp("createDate").getTime());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return us;
    }

    public static void main(String[] args) {
        System.out.println(new User(1, "64 ", "45", "645").toString());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(new User(2, "64 ", "45", "645").toString());
    }

    @Override
    public void close() throws Exception {
        SOURCE.close();
    }
}