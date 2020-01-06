package io.github.komyagin.controller;

import com.wordnik.swagger.annotations.*;
import io.github.komyagin.model.Person;
import io.github.komyagin.service.NoticeService;
import io.github.komyagin.service.PersonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/persons")
@Api(value = "persons", description = "Operation with persons")
public class PersonController {

    private final PersonService personService = new PersonService();
    private final NoticeService noticeService = new NoticeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Getting all of the persons from DB created by user",
            response = Person.class,
            responseContainer = "List")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Persons are received"),
                    @ApiResponse(code = 404, message = "DB has no persons"),
            })
    public Response getPerson() {
        List<Person> people = personService.getAllPersons();
        if (!people.isEmpty()) {
            return Response.status(200).entity(people).build();
        } else {
            return Response.status(404).entity("Persons - Not found").build();
        }
    }

    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Get user by its ID",
            notes = "You need to know user's ID")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Person is received by ID"),
                    @ApiResponse(code = 404, message = "Person by ID not found")
            }
    )
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(
            @ApiParam(value = "Person's ID that you need to get user")
            @PathParam("id") Integer id) {
        Person person = personService.getPerson(id);
        if (person != null) {
            return Response.status(200).entity(person).build();
        } else {
            return Response.status(404).entity("Person by id=" + id + " not found").build();
        }
    }

    @GET
    @Path("/{id}/notices")
    @ApiOperation(
            value = "Get user's notices by its ID",
            notes = "You can receive notices only if person added it"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Person's notices are received"),
                    @ApiResponse(code = 404, message = "Person has no notices")
            }
    )
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsNotices(
            @ApiParam(value = "Person's ID that you need to receive its notices")
            @PathParam("id") Integer id) {
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
        if (personService.addPerson(person)) {
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
    @ApiOperation(
            value = "Delete user by its ID",
            notes = "You need to know user's ID to delete user. All person's " +
                    "notices will be deleted."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Person is deleted"),
                    @ApiResponse(code = 404, message = "Person not found")
            }
    )
    @Path("/{id}")
    public Response deletePerson(
            @ApiParam(value = "Person's ID that you need to delete user")
            @PathParam("id") int id) {
        if (personService.removePerson(id)) {
            return Response.status(204).entity("Person deleted").build();
        } else {
            return Response.status(404).entity("Person not found").build();
        }
    }

}
