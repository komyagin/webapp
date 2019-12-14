package io.github.komyagin.dao;

import io.github.komyagin.model.Person;
import io.github.komyagin.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonSqlRepository implements PersonRepository {
    @Override
    public void addPerson(Person person) {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionFactory.getConnection();

        try {
            String sql = "INSERT INTO lab.person (first_name, last_name, email, category) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement("SET SCHEMA 'lab'");
            preparedStatement.addBatch();
            preparedStatement = connection.prepareStatement(sql);
            System.out.println(person);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, "default");
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("SQL Insert success?!");
        } catch (SQLException e) {
            System.out.println("SQL Exception Person rep");
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
        return null;
    }

    @Override
    public boolean isExist(int id) {
        return false;
    }
}
