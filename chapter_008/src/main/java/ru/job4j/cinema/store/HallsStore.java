package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.cinema.model.Visitor;
import ru.job4j.crud.store.DbStore;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Class HallsStore.
 *
 * @author dshustrov
 * @version 1
 * @since 01.04.2019
 */
public class HallsStore implements AutoCloseable, Store {

    private static final Logger LOG = LogManager.getLogger(DbStore.class);

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final HallsStore INSTANCE = new HallsStore();

    private HallsStore() {
        try (InputStream in = HallsStore.class.getClassLoader().getResourceAsStream("halls.properties")) {
            Properties config = new Properties();
            assert in != null;
            config.load(in);
            System.out.println(config.getProperty("driver-class-name"));
            SOURCE.setDriverClassName(config.getProperty("driver-class-name"));
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        String crtb = "CREATE TABLE if not exists halls (\n"
                +
                "id serial PRIMARY KEY,\n"
                +
                "range INTEGER,\n"
                +
                "place INTEGER,\n"
                +
                "status VARCHAR(200),\n"
                +
                "representation VARCHAR(200)\n"
                +
                ")";
        String ins = "INSERT INTO halls (range, place, status, representation) VALUES \n"
                +
                "(1, 1, 'free', 'Ряд 1, Место 1'), \n"
                +
                "(1, 2, 'free', 'Ряд 1, Место 2'), \n"
                +
                "(1, 3, 'free', 'Ряд 1, Место 3'), \n"
                +
                "(2, 4, 'free', 'Ряд 2, Место 4'), \n"
                +
                "(2, 5, 'free', 'Ряд 2, Место 5'), \n"
                +
                "(2, 6, 'free', 'Ряд 2, Место 6'), \n"
                +
                "(3, 7, 'free', 'Ряд 3, Место 7'), \n"
                +
                "(3, 8, 'free', 'Ряд 3, Место 8'), \n"
                +
                "(3, 9, 'free', 'Ряд 3, Место 9')";
        String crtbac = "CREATE TABLE if not exists accounts (\n"
                +
                "user_id serial PRIMARY KEY,\n"
                +
                "user_name character varying(200),\n"
                +
                "phone character varying(200),\n"
                +
                "place character varying(200)\n"
                +
                ")";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement create = connection.prepareStatement(crtb);
             PreparedStatement insert = connection.prepareStatement(ins);
             PreparedStatement createac = connection.prepareStatement(crtbac)) {
            create.executeUpdate();
            createac.executeUpdate();
            insert.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    public static HallsStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void close() throws Exception {
        SOURCE.close();
    }

    public Map<String, String> getAllHalls() {
        Map<String, String> map = new LinkedHashMap<>();
        String request = "SELECT representation, status FROM halls where range = ? ORDER BY id";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(request)) {
            ResultSet date;
            for (int i = 1; i < 4; i++) {
                st.setInt(1, i);
                date = st.executeQuery();
                while (date.next()) {
                    map.put(date.getString("representation"), date.getString("status"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return map;
    }

    public boolean checkPlace(String place) {
        boolean result = false;
        String request = "SELECT status FROM halls where representation = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(request)) {
            st.setString(1, place);
            ResultSet date = st.executeQuery();
            while (date.next()) {
                String representation = date.getString("status");
                if (representation.equals("free")) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public void addVisitor(Visitor visitor) {
        String insert = "INSERT INTO accounts (user_name, phone, place) VALUES (?, ?, ?)";
        String update = "UPDATE halls SET status = 'bought' WHERE representation = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement in = connection.prepareStatement(insert);
             PreparedStatement up = connection.prepareStatement(update)) {
            connection.setAutoCommit(false);
            in.setString(1, visitor.getUsername());
            in.setString(2, visitor.getPhone());
            in.setString(3, visitor.getPlace());
            in.executeUpdate();
            if (checkPlace(visitor.getPlace())) {
                up.setString(1, visitor.getPlace());
                up.executeUpdate();
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
