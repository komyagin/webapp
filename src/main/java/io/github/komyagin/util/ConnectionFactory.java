package io.github.komyagin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.ProviderNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private ConnectionFactory() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public static Connection getConnection() {

        Properties properties = new Properties();
        try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Properties file not found {}", e);
        }


        final String URL = properties.getProperty("db.url");
        final String USER = properties.getProperty("db.user");
        final String PASSWORD = properties.getProperty("db.password");
        final String DRIVER_CLASS_NAME = properties.getProperty("db.driver");

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
