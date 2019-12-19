package io.github.komyagin.dao;

import io.github.komyagin.model.Category;
import io.github.komyagin.model.Person;
import io.github.komyagin.util.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonSqlRepository implements PersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(PersonSqlRepository.class);

    private static final String PERSON_SELECT_ALL_SQL = "SELECT * FROM lab.person";
    private static final String PERSON_INSERT_SQL = "INSERT INTO lab.person (first_name, last_name, email, category) VALUES (?, ?, ?, ?)";
    private static final String PERSON_SELECT_ID_SQL = "SELECT * FROM lab.person WHERE id = ?";
    private static final String PERSON_DELETE_SQL = "DELETE FROM lab.person WHERE id = ?";
    private static final String PERSON_UPDATE_SQL = "UPDATE lab.person SET first_name = ?, last_name = ?, email, category = ? " +
            "WHERE id = ?";

    @Override
    public void addPerson(Person person) {
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(PERSON_INSERT_SQL)) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getCategory().toString());
            if (preparedStatement.executeUpdate() > 0) {
                logger.info("INSERT person to DB is succeed");
            } else {
                logger.warn("INSERT person to DB is not succeed");
            }
            logger.info("SQL Insert success...");
        } catch (SQLException e) {
            logger.warn("SQL Exception Person repository {}", e.getMessage());
        }
    }

    @Override
    public Person getPerson(int id) {
        Connection connection = ConnectionFactory.getConnection();

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(PERSON_SELECT_ID_SQL)) {
                //TODO: Transfer to particular class Convert with static method statementToPerson
                Category category = Category.valueOf((resultSet.getString(5)).toUpperCase());
                Person person = new Person(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), category);
                logger.info("Person found");
                return person;
            }
        } catch (SQLException e) {
            logger.error("Person not found {}", e.getMessage());
        }
        //TODO: not good to return null... I have to return something better
        return null;
    }

    @Override
    public void updatePerson(Person person) {
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(PERSON_UPDATE_SQL)) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getCategory().toString());
            preparedStatement.setInt(5, person.getId());
            if (preparedStatement.executeUpdate() > 0) {
                logger.info("UPDATE person to DB is succeed");
            } else {
                logger.warn("UPDATE person to DB is not succeed");
            }
            logger.info("SQL UPDATE success...");
        } catch (SQLException e) {
            logger.warn("SQL Exception Person repository {}", e.getMessage());
        }
    }

    @Override
    public void removePerson(int id) {
        Connection connection = ConnectionFactory.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(PERSON_DELETE_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            logger.info("Person has been deleted successful");
        } catch (SQLException e) {
            logger.error("Person by id={} not found - {}", id, e.getMessage());
        }
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> list = new ArrayList<>();

        Connection connection = ConnectionFactory.getConnection();

        if (connection != null) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(PERSON_SELECT_ALL_SQL)) {
                    while (resultSet.next()) {
                        //TODO: Transfer to particular class Convert with static method statementToPerson
                        Category category = Category.valueOf((resultSet.getString(5)).toUpperCase());
                        Person person = new Person(resultSet.getInt(1),
                                resultSet.getString(2), resultSet.getString(3),
                                resultSet.getString(4), category);
                        list.add(person);
                    }
                }
            } catch (SQLException e) {
                logger.warn("Query error...");
            }
        }
        return list;
    }

    @Override
    public boolean isExist(int id) {
        return false;
    }
}
