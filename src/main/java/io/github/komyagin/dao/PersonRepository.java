package io.github.komyagin.dao;

import io.github.komyagin.model.Person;

import java.util.List;

public interface PersonRepository {
    boolean addPerson(Person person);
    Person getPerson(int id);
    boolean updatePerson(Person person);
    boolean removePerson(int id);
    List<Person> getAllPersons();
}
