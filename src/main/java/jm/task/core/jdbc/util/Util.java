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

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        return sessionFactory;
    }

}
//    if (sessionFactory == null) {
//        try {
//            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
//
//            Map<String,String> settingsMySql = new HashMap<>();
//            settingsMySql.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
//            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//            settingsMySql.put("hibernate.connection.url", DB_URL);
//            settingsMySql.put("hibernate.connection.username", DB_USER_NAME);
//            settingsMySql.put("hibernate.connection.password", DB_PASSWORD);
//            settingsMySql.put("hibernate.current_session_context_class", "thread");
//            settingsMySql.put("hibernate.show_sql", "true");
//            settingsMySql.put("hibernate.format_sql", "true");
//            settingsMySql.put("hibernate.hbm2ddl.auto", "update");

//            ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(settingsMySql).build();
//            MetadataSources metadataSources = new MetadataSources((org.hibernate.service.ServiceRegistry) serviceRegistry);
//
//            Metadata metadata = metadataSources.buildMetadata();
//
//            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
//            Session session = sessionFactory.getCurrentSession();


//            registryBuilder.applySettings(settingsMySql);
//            registry = registryBuilder.build();
//            MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(User.class);
//
//            sessionFactory = sources.buildMetadata().buildSessionFactory();
//        } catch (Exception e) {
//            System.out.println("SessionFactory creation failed");
//            if (registry != null) {
//                StandardServiceRegistryBuilder.destroy(registry);
//            }
//        }
//    }
//    return sessionFactory;
