package io.github.komyagin.service;

import io.github.komyagin.dao.PersonRepository;
import io.github.komyagin.dao.PersonSqlRepository;
import io.github.komyagin.model.Person;

import java.util.List;

public class PersonService {
    private final PersonRepository personRepository = new PersonSqlRepository();

    public Person getPerson(int id) {
        return personRepository.getPerson(id);
    }

    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    public void addPerson(Person person) {
        personRepository.addPerson(person);
    }

    public void removePerson(int id) {
        personRepository.removePerson(id);
    }

    public void updatePerson(Person person) {
        personRepository.updatePerson(person);
    }
}
