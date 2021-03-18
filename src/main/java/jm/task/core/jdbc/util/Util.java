package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//import org.hibernate.mapping.Map;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db113?autoReconnect=" +
            "true&useSSL=FALSE&useLegacyDatetimeCode=false&serverTimezone=" +
            " UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "rcjPp2014ml";
    private static SessionFactory sessionFactory = null;
    private Util(){

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
            System.out.println("The connection to the database is installed.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("The connection to the database is not installed.");
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.setProperty(Environment.USER, DB_USER_NAME);
        properties.setProperty(Environment.PASS, DB_PASSWORD);
        properties.setProperty(Environment.URL, DB_URL);

        Configuration configuration = new Configuration();
        configuration.setProperties(properties);

        configuration.addAnnotatedClass(User.class);

        return configuration.buildSessionFactory();
    }
        return sessionFactory;
    }
}


