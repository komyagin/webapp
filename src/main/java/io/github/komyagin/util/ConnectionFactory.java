package io.github.komyagin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private ConnectionFactory() {}

    private static final String URL = "jdbc:postgresql://localhost/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qaz1@Wsx";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public static Connection getConnection() {
        Logger logger = Logger.getLogger(ConnectionFactory.class.getName());
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.warning("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "ERROR!");
        }
        return connection;
    }
}
