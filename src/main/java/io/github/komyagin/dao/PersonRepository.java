package io.github.komyagin.dao;

import io.github.komyagin.model.Person;

import java.util.List;

public interface PersonRepository {
    void addPerson(Person person);
    Person getPerson(int id);
    void updatePerson(Person person);
    void removePerson(int id);
    List<Person> getAllPersons();
    boolean isExist(int id);
}
