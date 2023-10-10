package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Util {

    public static Connection getConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "userdb";
        String userName = "root";
        String password = "Crehfnjd1311";
        return getConnection(hostName, dbName, userName, password);
    }

    public static Connection getConnection(String hostName, String dbName,
                                           String userName, String password) throws SQLException {
        String URL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection connect = DriverManager.getConnection(URL, userName, password);
        return connect;
    }

    private static SessionFactory session = null;
    public static SessionFactory getSessionFactory() {
        if (session == null) {
            try {
                Configuration configuration = new Configuration();

                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/userdb");
                configuration.setProperty("hibernate.connection.username", "root");
                configuration.setProperty("hibernate.connection.password", "Crehfnjd1311");
                configuration.addAnnotatedClass(User.class);

                session = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return session;
    }
}
