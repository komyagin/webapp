package io.github.komyagin;

import io.github.komyagin.dao.PersonSqlRepository;
import io.github.komyagin.model.Person;

import java.util.logging.Logger;

public class WebApp {

    public static Logger logger = Logger.getLogger(WebApp.class.getName());

    public static void main(String[] args) {
        PersonSqlRepository personSqlRepository = new PersonSqlRepository();
        System.out.println("Persons: ");
        for (Person person : personSqlRepository.getAllPersons()) {
            System.out.println(person);
        }
    }

}

