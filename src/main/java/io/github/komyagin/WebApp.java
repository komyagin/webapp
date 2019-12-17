package io.github.komyagin;

import io.github.komyagin.dao.PersonRepository;
import io.github.komyagin.dao.PersonSqlRepository;
import io.github.komyagin.model.Person;

import java.util.logging.Logger;

public class WebApp {

    public static Logger logger = Logger.getLogger(WebApp.class.getName());

    public static void main(String[] args) {
        PersonRepository personSqlRepository = new PersonSqlRepository();
        System.out.println("Persons: ");
        for (Person person : personSqlRepository.getAllPersons()) {
            System.out.println(person);
        }
    }

}

