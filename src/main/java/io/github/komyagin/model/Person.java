package io.github.komyagin.model;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Category category;

    public Person(int id, String firstName, String lastName, String email, Category category) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
