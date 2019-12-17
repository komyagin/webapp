package io.github.komyagin.service;

import io.github.komyagin.WebApp;
import io.github.komyagin.model.Category;
import io.github.komyagin.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class PersonFactory {

    private PersonFactory() {

    }

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static Person getPerson() {
        System.out.println("...Adding new person...");
        String firstName = null;
        String lastName = null;
        String email = null;
        Category category = null;
        try {
            System.out.print("Enter first name: ");
            firstName = bufferedReader.readLine();
            System.out.print("Enter last name: ");
            lastName = bufferedReader.readLine();
            System.out.print("Enter email: ");
            email = bufferedReader.readLine();
            System.out.println();
        } catch (IOException e) {
            WebApp.logger.log(Level.SEVERE, "IOException...");
        }
        if(firstName != null && lastName != null && email != null) {
            WebApp.logger.log(Level.FINE, "Person created successful");
            return new Person(1, firstName, lastName, email, Category.COMPUTERS);
        }
        WebApp.logger.log(Level.WARNING, "Person not created!");
        return null;
    }

}
