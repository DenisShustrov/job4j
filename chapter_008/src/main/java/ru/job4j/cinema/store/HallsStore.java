package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.cinema.model.Visitor;
import ru.job4j.crud.store.DbStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

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
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("1477556");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
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
        String insert = "INSERT INTO accounts (username, phone, place) VALUES (?, ?, ?)";
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
