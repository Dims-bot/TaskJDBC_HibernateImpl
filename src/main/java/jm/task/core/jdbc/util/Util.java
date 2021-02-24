package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabaseex113?autoReconnect=" +
            "true&useSSL=FALSE&useLegacyDatetimeCode=false&serverTimezone=" +
            " UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "rcjPp2014ml";

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    // реализуйте настройку соеденения с БД
public Connection getConnection() {
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

public SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
        try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            Map<String,String> settingsMySql = new HashMap<>();
            settingsMySql.put("hibernate.connection.driver_class", "com.")
        }
    }
}
}