package io.github.komyagin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Person. ")
public class Person {

    @ApiModelProperty(notes = "The database generated person ID")
    private int id;
    @ApiModelProperty(notes = "The person first name")
    private String firstName;
    @ApiModelProperty(notes = "The person last name")
    private String lastName;
    @ApiModelProperty(notes = "The person email. Unique")
    private String email;
    @ApiModelProperty(notes = "The peson category. Individual or company")
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

}
