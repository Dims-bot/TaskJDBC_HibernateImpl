package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabaseex113?autoReconnect=" +
            "true&useSSL=FALSE&useLegacyDatetimeCode=false&serverTimezone=" +
            " UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "rcjPp2014ml";

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
}