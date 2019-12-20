package io.github.komyagin.controller;

import io.github.komyagin.dao.PersonRepository;
import io.github.komyagin.dao.PersonSqlRepository;
import io.github.komyagin.model.Person;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    public Response getPerson(@PathParam("id") Integer id) {
        return Response.status(200).entity(personRepository.getAllPersons().get(id - 1)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        personRepository.addPerson(person);
        return Response.status(200).entity("Person added").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        personRepository.updatePerson(person);
        return Response.status(200).entity("Person updated").build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePerson(@PathParam("id") int id) {
        personRepository.removePerson(id);
        return Response.status(200).entity("Person deleted").build();
    }

}
