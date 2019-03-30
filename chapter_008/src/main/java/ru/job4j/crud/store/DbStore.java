package ru.job4j.crud.store;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.crud.model.User;

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
             PreparedStatement st = connection.prepareStatement(
                     "INSERT "
                             +
                             "INTO users (id, name_u, login, email, createdate, password, rules, country, region, city) "
                             +
                             "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setTimestamp(5, new Timestamp(user.getCreateDate().getTime()));
            st.setString(6, user.getPassword());
            st.setString(7, user.getRules());
            st.setString(8, user.getCountry());
            st.setString(9, user.getRegion());
            st.setString(10, user.getCity());
            st.executeUpdate();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public boolean replace(User user) {
        boolean result = true;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE users "
                             +
                             "SET id=?, name_u = ?, login = ?,  email = ?, createdate = ?, password = ?, rules = ?, country = ?, region = ?, city = ?"
                             +
                             "WHERE id = ?")) {
            if (findById(user.getId()) == null) {
                result = false;
            } else {
                st.setInt(1, user.getId());
                if (user.getName().equals("")) {
                    st.setString(2, findById(user.getId()).getName());
                } else {
                    st.setString(2, user.getName());
                }
                if (user.getLogin().equals("")) {
                    st.setString(3, findById(user.getId()).getLogin());
                } else {
                    st.setString(3, user.getLogin());
                }
                if (user.getEmail().equals("")) {
                    st.setString(4, findById(user.getId()).getEmail());
                } else {
                    st.setString(4, user.getEmail());
                }
                if (user.getCreateDate() == null) {
                    st.setTimestamp(5, new Timestamp(findById(user.getId()).getCreateDate().getTime()));
                } else {
                    st.setTimestamp(5, new Timestamp(user.getCreateDate().getTime()));
                }
                if (user.getPassword().equals("")) {
                    st.setString(6, findById(user.getId()).getPassword());
                } else {
                    st.setString(6, user.getPassword());
                }
                if (user.getRules() == null || user.getRules().equals("Choose a role")) {
                    st.setString(7, findById(user.getId()).getRules());
                } else {
                    st.setString(7, user.getRules());
                }
                if (user.getCountry().equals("- choose a country -")) {
                    st.setString(8, findById(user.getId()).getCountry());
                } else {
                    st.setString(8, user.getCountry());
                }
                if (user.getRegion().equals("- choose a region -")) {
                    st.setString(9, findById(user.getId()).getRegion());
                } else {
                    st.setString(9, user.getRegion());
                }
                if (user.getCity().equals("- choose a city -")) {
                    st.setString(10, findById(user.getId()).getCity());
                } else {
                    st.setString(10, user.getCity());
                }
                st.setInt(11, user.getId());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public User findById(int id) throws SQLException {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE id=?")) {
            st.setInt(1, id);
            ResultSet date = st.executeQuery();
            while (date.next()) {
                User us = new User(date.getInt("id"),
                        date.getString("name_u"),
                        date.getString("login"),
                        date.getString("email"),
                        date.getString("password"),
                        date.getString("rules"),
                        date.getString("country"),
                        date.getString("region"),
                        date.getString("city")
                );
                us.setCreateDate(date.getTimestamp("createDate"));
                result = us;

            }
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
                User us = new User(date.getInt("id"),
                        date.getString("name_u"),
                        date.getString("login"),
                        date.getString("email"),
                        date.getString("password"),
                        date.getString("rules"),
                        date.getString("country"),
                        date.getString("region"),
                        date.getString("city")
                );
                us.setCreateDate(date.getTimestamp("createDate"));
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
                User us = new User(date.getInt("id"),
                        date.getString("name_u"),
                        date.getString("login"),
                        date.getString("email"),
                        date.getString("password"),
                        date.getString("rules"),
                        date.getString("country"),
                        date.getString("region"),
                        date.getString("city")
                );
                us.setCreateDate(date.getTimestamp("createDate"));
                temp.add(us);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return temp;
    }

    @Override
    public User findUser(String login, String password) {
        User us = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?")) {
            st.setString(1, login);
            st.setString(2, password);
            ResultSet date = st.executeQuery();
            while (date.next()) {
                us = new User(date.getInt("id"),
                        date.getString("name_u"),
                        date.getString("login"),
                        date.getString("email"),
                        date.getString("password"),
                        date.getString("rules"),
                        date.getString("country"),
                        date.getString("region"),
                        date.getString("city")
                );
                us.setCreateDate(date.getTimestamp("createDate"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return us;
    }

    public boolean checkAddUser(String login, String password) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?")) {
            st.setString(1, login);
            st.setString(2, password);
            ResultSet date = st.executeQuery();
            if (!date.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public boolean isConformity(User user) {
        boolean result = false;
        if (checkAddUser(user.getLogin(), user.getPassword())) {
            result = true;
        }
        return result;
    }

    public List<String> getAllCountry() {
        List<String> list = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT name FROM country")) {
            ResultSet date = st.executeQuery();
            while (date.next()) {
                list.add(date.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    public List<String> getAllRegionByCountry(String country) {
        List<String> region = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT name "
                     +
                     "FROM region "
                     +
                     "WHERE country_id = (SELECT id FROM country WHERE name = ?)")) {
            st.setString(1, country);
            ResultSet date = st.executeQuery();
            while (date.next()) {
                region.add(date.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return region;
    }

    public List<String> getAllCitiesByRegion(String region) {
        List<String> cities = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT name "
                     +
                     "FROM city "
                     +
                     "WHERE region_id = (SELECT id FROM region WHERE name = ?)")) {
            st.setString(1, region);
            ResultSet date = st.executeQuery();
            while (date.next()) {
                cities.add(date.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return cities;
    }

    @Override
    public void close() throws Exception {
        SOURCE.close();
    }
}
