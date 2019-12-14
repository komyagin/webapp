package io.github.komyagin;

import io.github.komyagin.dao.PersonSqlRepository;
import io.github.komyagin.model.Category;
import io.github.komyagin.model.Person;
import io.github.komyagin.service.PersonFactory;
import io.github.komyagin.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebApp {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(WebApp.class.getName());

        Connection connection = ConnectionFactory.getConnection();

        Statement statement = null;
        ResultSet resultSet = null;

        PersonSqlRepository personSqlRepository = new PersonSqlRepository();

        personSqlRepository.addPerson(new Person("Max", "Pain", "max.pain@ya.ru"));

        if(connection != null) {
            try {
                statement = connection.createStatement();
                try {
                    resultSet = statement.executeQuery("SELECT * FROM lab.person");
                    while (resultSet.next()) {
                        System.out.println("Persons: ");
                        System.out.print(resultSet.getInt(1) + " ");
                        System.out.print(resultSet.getString(2) + " ");
                        System.out.print(resultSet.getString(3) + " ");
                        System.out.print(resultSet.getString(4) + " ");
                        System.out.print(resultSet.getString(5) + " ");
                    }


                } catch (SQLException e) {
                    logger.log(Level.WARNING, "Query error...");
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        statement.close();
                    } catch (SQLException e) {
                        logger.log(Level.WARNING, "Cannot close connection...");
                    } catch (NullPointerException e) {
                        e.getStackTrace();
                    }
                }

            } catch (SQLException e) {
                logger.log(Level.WARNING, "SQL error!");
            }

        }

    }
}
