package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private final String URL = "jdbc:mysql://localhost:3306/mytest";
    private final String USERNAME = "root";
    private final String PASS = "exlibris";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

        public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASS);
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed");
        }
        return connection;
    }

    public Util() {

    }

    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry standardServiceRegistry;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mytest");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "exlibris");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.HBM2DDL_AUTO,"update");
                settings.put(Environment.SHOW_SQL, "true");

                Configuration config = new Configuration();
                config.setProperties(settings);
                config.addAnnotatedClass(User.class);
                SessionFactory sessionFactory = config.buildSessionFactory();
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

