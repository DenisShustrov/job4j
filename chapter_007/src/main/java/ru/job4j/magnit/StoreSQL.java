package ru.job4j.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class StoreSQL.
 *
 * @author dshustrov
 * @version 1
 * @since 22.02.2019
 */
public class StoreSQL implements AutoCloseable {

    private List<Entry> list = new ArrayList<>();

    private final Config config;

    private Entryes entryes = new Entryes();

    private Connection connect;

    public Statement statement;

    public PreparedStatement preparedStatement;

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class);

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void connection() {
        config.init();
        try {
            connect = DriverManager.getConnection(
                    config.get("url"),
                    config.get("username"),
                    config.get("password"));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void generate(int size) throws Exception {
        try {
            connection();
            statement = connect.createStatement();
            statement.execute("CREATE TABLE if not exists 'entry' ('field' INTEGER);");
            statement.execute("DELETE FROM 'entry'");
            for (int i = 0; i < size; i++) {
                preparedStatement = connect.prepareStatement("INSERT INTO 'entry' ('field') VALUES (?)");
                preparedStatement.setInt(1, i);
                preparedStatement.executeUpdate();
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            preparedStatement.close();
            statement.close();
            close();
        }
    }

    public List<Entry> load() throws Exception {
        List<Field> fields = new ArrayList<>();
        try {
            connection();
            preparedStatement = connect.prepareStatement("SELECT * FROM entry");
            ResultSet date = preparedStatement.executeQuery();
            while (date.next()) {
                Field field = new Field(date.getInt("field"));
                fields.add(field);
            }
            list.add(new Entry(fields));
            entryes.setEntry(list);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            preparedStatement.close();
            close();
        }
        return list;
    }

    public Entryes getEntryes() {
        return entryes;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
