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

    private static final String PERSON_INSERT_SQL = "INSERT INTO lab.person (first_name, last_name, email, category) VALUES (?, ?, ?, ?)";

    @Override
    public void addPerson(Person person) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionFactory.getConnection();

        try {
            preparedStatement = connection.prepareStatement(PERSON_INSERT_SQL);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getCategory().toString());
            if(preparedStatement.executeUpdate() == 1) {
                logger.info("INSERT person to DB is succeed");
            } else {
                logger.warn("INSERT person to DB is not succeed");
            }
            logger.info("SQL Insert success...");
        } catch (SQLException e) {
            logger.warn("SQL Exception Person repository");
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException | NullPointerException e) {
                logger.error(e.toString());
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
                    logger.warn("Query error...");
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        statement.close();
                    } catch (SQLException e) {
                        logger.warn("Cannot close connection...");
                    } catch (NullPointerException e) {
                        logger.error(e.getMessage());
                    }
                }

            } catch (SQLException e) {
                logger.error(e.getMessage());
            }

        }
        return list;
    }

    @Override
    public boolean isExist(int id) {
        return false;
    }
}
