package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static Connection connection = null;
    private static final String url = "jdbc:postgresql://localhost:5432/newDB";
    private static final String user = "postgres";
    private static final String password = "2425733";

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            connection = DriverManager.getConnection(url, props);
        }
        return connection;
    }
}
