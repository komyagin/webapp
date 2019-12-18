package io.github.komyagin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConnectionFactory {

    private ConnectionFactory() {}

    private static final String URL = "jdbc:postgresql://localhost/postgres?currentSchema=lab";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qaz1@Wsx";

    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

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
            logger.info("Connected to the PostgreSQL server successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Cannot connect to DB...");
        }
        return connection;
    }
}
