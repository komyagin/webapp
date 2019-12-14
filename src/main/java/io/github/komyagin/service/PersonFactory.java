package io.github.komyagin.service;

import io.github.komyagin.WebApp;
import io.github.komyagin.model.Category;
import io.github.komyagin.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PersonFactory {

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
            System.out.println("IOException...");
        }
        if(firstName != null && lastName != null && email != null) {
            System.out.println("Person created successful");
            return new Person(firstName, lastName, email);
        }
        System.out.println("Person not created!");
        return null;
    }

}
