package io.github.komyagin.controller;

import io.github.komyagin.dao.PersonRepository;
import io.github.komyagin.dao.PersonSqlRepository;
import io.github.komyagin.model.Person;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/persons")
public class PersonController {

    private final PersonRepository personRepository = new PersonSqlRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson() {
        List<Person> people = personRepository.getAllPersons();
        return Response.status(200).entity(people).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") Integer id) {
        return personRepository.getAllPersons().get(id - 1);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(Person person) {
        personRepository.addPerson(person);
        return Response.status(200).entity("Person added").build();
    }

}
