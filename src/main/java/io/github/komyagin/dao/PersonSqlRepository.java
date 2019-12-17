package io.github.komyagin.dao;

import io.github.komyagin.WebApp;
import io.github.komyagin.model.Category;
import io.github.komyagin.model.Person;
import io.github.komyagin.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

public class PersonSqlRepository implements PersonRepository {
    @Override
    public void addPerson(Person person) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionFactory.getConnection();

        try {
            String sql = "INSERT INTO lab.person (first_name, last_name, email, category) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getCategory().toString());
            preparedStatement.execute();
            preparedStatement.close();
            WebApp.logger.log(Level.FINE, "SQL Insert success...");
        } catch (SQLException e) {
            WebApp.logger.log(Level.WARNING, "SQL Exception Person rep");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {

            }
        }
    }

    @Override
    public Person getPerson(int id) {
        return null;
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void removePerson(int id) {

    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> list = new ArrayList<>();

        Connection connection = ConnectionFactory.getConnection();

        Statement statement = null;
        ResultSet resultSet = null;

        if(connection != null) {
            try {
                statement = connection.createStatement();
                try {
                    resultSet = statement.executeQuery("SELECT * FROM lab.person");
                    while (resultSet.next()) {
                        //TODO: Transfer to particular class Convert with static method statementToPerson
                        Category category = Category.valueOf((resultSet.getString(5)).toUpperCase());
                        Person person = new Person(resultSet.getInt(1),
                                resultSet.getString(2), resultSet.getString(3),
                                resultSet.getString(4), category);
                        list.add(person);
                    }
                } catch (SQLException e) {
                    WebApp.logger.log(Level.WARNING, "Query error...");
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        statement.close();
                    } catch (SQLException e) {
                        WebApp.logger.log(Level.WARNING, "Cannot close connection...");
                    } catch (NullPointerException e) {
                        e.getStackTrace();
                    }
                }

            } catch (SQLException e) {
                WebApp.logger.log(Level.WARNING, "SQL error!");
            }

        }
        return list;
    }

    @Override
    public boolean isExist(int id) {
        return false;
    }
}
