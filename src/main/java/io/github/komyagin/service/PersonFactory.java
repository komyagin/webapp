package io.github.komyagin.service;

import io.github.komyagin.model.Category;
import io.github.komyagin.model.Person;
import io.github.komyagin.util.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PersonFactory {

    private PersonFactory() {

    }

    private static final Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static Person getPerson() {
        System.out.println("...Adding new person...");
        String firstName = null;
        String lastName = null;
        String email = null;
        String category = null;
        try {
            System.out.print("Enter first name: ");
            firstName = bufferedReader.readLine();
            System.out.print("Enter last name: ");
            lastName = bufferedReader.readLine();
            System.out.print("Enter email: ");
            email = bufferedReader.readLine();
            System.out.print("Enter email: ");
            category = bufferedReader.readLine();
            System.out.println();
        } catch (IOException e) {
            logger.error("IOException");
        }
        if(firstName != null && lastName != null && email != null) {
            logger.info("Person created successful");
            return new Person(1, firstName, lastName, email, Category.valueOf(category));
        }
        logger.warn("Person not created!");
        return null;
    }

}
