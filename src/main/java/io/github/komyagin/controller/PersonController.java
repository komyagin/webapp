package io.github.komyagin.controller;

import io.github.komyagin.model.Person;
import io.github.komyagin.service.NoticeService;
import io.github.komyagin.service.PersonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/persons")
public class PersonController {

    private final PersonService personService = new PersonService();
    private final NoticeService noticeService = new NoticeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson() {
        List<Person> people = personService.getAllPersons();
        if(!people.isEmpty()) {
            return Response.status(200).entity(people).build();
        } else {
            return Response.status(404).entity("Persons - Not found").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        Person person = personService.getPerson(id);
        if (person != null) {
            return Response.status(200).entity(person).build();
        } else {
            return Response.status(404).entity("Person by id=" + id + " not found").build();
        }
    }

    @GET
    @Path("/{id}/notices")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsNotices(@PathParam("id") Integer id) {
        List notices = noticeService.getNoticeByPersonId(id);
        if (!notices.isEmpty()) {
            return Response.status(200).entity(notices).build();
        } else {
            return Response.status(404).entity("Person by id " + id + " doesn't have notices").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        if(personService.addPerson(person)) {
            return Response.status(200).entity("Person added").build();
        } else {
            return Response.status(406).entity("Not acceptable").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        if (personService.updatePerson(person)) {
            return Response.status(200).entity("Person updated").build();
        } else {
            return Response.status(406).entity("Not acceptable").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        if (personService.removePerson(id)) {
            return Response.status(204).entity("Person deleted").build();
        } else {
            return Response.status(404).entity("Person not found").build();
        }
    }

}
