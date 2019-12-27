package io.github.komyagin.controller;

import io.github.komyagin.dao.PersonRepository;
import io.github.komyagin.dao.PersonSqlRepository;
import io.github.komyagin.model.Person;
import io.github.komyagin.service.NoticeService;
import io.github.komyagin.service.PersonService;

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

    private final PersonService personService = new PersonService();
    private final NoticeService noticeService = new NoticeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson() {
        List<Person> people = personService.getAllPersons();
        return Response.status(200).entity(people).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        return Response.status(200).entity(personService.getPerson(id)).build();
    }

    @GET
    @Path("/{id}/notices")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsNotices(@PathParam("id") Integer id) {
        return Response.status(200).entity(noticeService.getNoticeByPersonId(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        personService.addPerson(person);
        return Response.status(200).entity("Person added").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        personService.updatePerson(person);
        return Response.status(200).entity("Person updated").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        personService.removePerson(id);
        return Response.status(200).entity("Person deleted").build();
    }

}
