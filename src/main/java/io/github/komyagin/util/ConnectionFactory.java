package io.github.komyagin.util;

import io.github.komyagin.WebApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private ConnectionFactory() {}

    private static final String URL = "jdbc:postgresql://localhost/postgres?currentSchema=lab";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qaz1@Wsx";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public static Connection getConnection() {


        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            WebApp.logger.log(Level.FINE,"Connected to the PostgreSQL server successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return connection;
    }
}
