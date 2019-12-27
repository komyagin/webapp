package io.github.komyagin.service;

import io.github.komyagin.model.Category;
import io.github.komyagin.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlToPerson {

    private SqlToPerson() {};

    public static Person getPerson(ResultSet resultSet) throws SQLException {
        Category category = Category.valueOf((resultSet.getString(5)).toUpperCase());
        return new Person(resultSet.getInt(1),
                resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), category);
    }

    public static List<Person> getAllPerons(ResultSet resultSet) throws SQLException {
        List<Person> list = new ArrayList<>();
        while (resultSet.next()) {
            Category category = Category.valueOf((resultSet.getString(5)).toUpperCase());
            list.add(new Person(resultSet.getInt(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), category));
        }
        return list;
    }
}
