package io.github.komyagin.util;

import java.nio.file.ProviderNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConnectionFactory {

    private ConnectionFactory() {
    }

    private static final String URL = "jdbc:postgresql://localhost/postgres?currentSchema=lab";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public static Connection getConnection() {


        Connection connection = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Connected to the PostgreSQL server successfully.");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Cannot connect to DB...{}", e.getMessage());
        }

        if (connection == null) {
            throw new ProviderNotFoundException();
        } else {
            return connection;
        }
    }
}
