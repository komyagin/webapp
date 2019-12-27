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

    public boolean addPerson(Person person) {
        return personRepository.addPerson(person);
    }

    public boolean removePerson(int id) {
        return personRepository.removePerson(id);
    }

    public boolean updatePerson(Person person) {
        return personRepository.updatePerson(person);
    }
}
