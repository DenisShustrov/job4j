package ru.job4j.tracker;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Class TrackerSQL.
 *
 * @author dshustrov
 * @version 1
 * @since 17.02.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class);

    private ArrayList<Item> items = new ArrayList<>();

    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

//    public boolean init() {
//        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
//            Properties config = new Properties();
//            config.load(in);
//            Class.forName(config.getProperty("driver-class-name"));
//            this.connection = DriverManager.getConnection(
//                    config.getProperty("url"),
//                    config.getProperty("username"),
//                    config.getProperty("password")
//            );
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//        }
//        return this.connection != null;
//    }


    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO items (name, discription, create_item) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDiscription());
            statement.setTimestamp(3, new Timestamp(item.getCreate()));
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                item.setId(String.valueOf(generatedKeys.getInt(1)));
            }
            //close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE items SET name = ?, discription = ?,  create_item = ? WHERE id = ?")) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDiscription());
            statement.setTimestamp(3, new Timestamp(item.getCreate()));
            statement.setInt(4, Integer.parseInt(id));
            statement.executeUpdate();
            result = true;
            //close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            statement.setInt(1, Integer.parseInt(id));
            statement.executeUpdate();
            result = true;
            //close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public ArrayList<Item> findAll() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM items")) {
            ResultSet date = statement.executeQuery();
            while (date.next()) {
                Item itemTemp = new Item(date.getString("name"), date.getString("discription"), date.getTimestamp("create_item").getTime());
                itemTemp.setId(String.valueOf(date.getInt(1)));
                items.add(itemTemp);
            }
            //close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public ArrayList<Item> findByName(String key) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE name = ?")) {
            statement.setString(1, key);
            ResultSet date = statement.executeQuery();
            while (date.next()) {
                Item itemTemp = new Item(date.getString("name"), date.getString("discription"), date.getTimestamp("create_item").getTime());
                itemTemp.setId(String.valueOf(date.getInt(1)));
                items.add(itemTemp);
            }
            //close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item itemId = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet date = statement.executeQuery();
            date.next();
            itemId = new Item(date.getString("name"), date.getString("discription"), date.getTimestamp("create_item").getTime());
            itemId.setId(String.valueOf(date.getInt(1)));
            //close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return itemId;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
