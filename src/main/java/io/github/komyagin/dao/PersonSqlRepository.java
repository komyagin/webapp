package io.github.komyagin.dao;

import io.github.komyagin.model.Person;
import io.github.komyagin.service.SqlToPerson;
import io.github.komyagin.util.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonSqlRepository implements PersonRepository {

    private static final Logger logger = LoggerFactory.getLogger(PersonSqlRepository.class);

    private static final String PERSON_SELECT_ALL_SQL = "SELECT * FROM lab.person";
    private static final String PERSON_INSERT_SQL = "INSERT INTO lab.person (first_name, last_name, email, category) " +
            "VALUES (?, ?, ?, ?)";
    private static final String PERSON_SELECT_BY_ID_SQL = "SELECT * FROM lab.person WHERE id = ?";
    private static final String PERSON_DELETE_SQL = "DELETE FROM lab.person WHERE id = ?";
    private static final String PERSON_UPDATE_SQL = "UPDATE lab.person SET first_name = ?, last_name = ?, email = ?," +
            " category = ? WHERE id = ?";

    private static final String SQL_ERROR = "SQL Exception Person repository {}";

    @Override
    public boolean addPerson(Person person) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PERSON_INSERT_SQL)) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getCategory().toString());
            if (preparedStatement.executeUpdate() > 0) {
                logger.info("INSERT person to DB is succeed");
                return true;
            } else {
                logger.info("SQL Insert is not succeed...");
                return false;
            }
        } catch (SQLException e) {
            logger.error(SQL_ERROR, e);
            return false;
        }
    }

    @Override
    public Person getPerson(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(PERSON_SELECT_BY_ID_SQL);
             ) {
            preparedStatement.setInt(1, id);
            return SqlToPerson.getPerson(preparedStatement.executeQuery());
        } catch (SQLException e) {
            logger.error(SQL_ERROR, e);
        }
        return null;
    }

    @Override
    public boolean updatePerson(Person person) {


        if (getPerson(person.getId()) != null) {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(PERSON_UPDATE_SQL)) {
                preparedStatement.setString(1, person.getFirstName());
                preparedStatement.setString(2, person.getLastName());
                preparedStatement.setString(3, person.getEmail());
                preparedStatement.setString(4, person.getCategory().toString());
                preparedStatement.setInt(5, person.getId());
                if (preparedStatement.executeUpdate() > 0) {
                    logger.info("UPDATE person to DB is succeed");
                    return true;
                }
            } catch (SQLException e) {
                logger.warn(SQL_ERROR, e);
                return false;
            }
        } else {
            logger.error("Person by id={} not found. Update not possible", person.getId());
            return false;
        }
        return false;
    }

    @Override
    public boolean removePerson(int id) {
        if (getPerson(id) != null) {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(PERSON_DELETE_SQL)) {
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                logger.info("Person has been deleted successful");
                return true;
            } catch (SQLException e) {
                logger.error(SQL_ERROR, e);
                return false;
            }
        } else {
            logger.error("Person by id={} not found...", id);
        }
        return false;
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> list = new ArrayList<>();


        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(PERSON_SELECT_ALL_SQL)) {
            list = SqlToPerson.getAllPerons(resultSet);
            logger.info("All persons are found");
        } catch (SQLException e) {
            logger.error(SQL_ERROR, e);
        }
        return list;
    }
}
