package io.github.komyagin.controller;

import io.github.komyagin.model.Notice;
import io.github.komyagin.service.NoticeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/notices")
public class NoticeController {

    private final NoticeService noticeService = new NoticeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotices() {
        List<Notice> notices = noticeService.getAllNotices();
        if (!notices.isEmpty()) {
            return Response.status(200).entity(notices).build();
        } else {
            return Response.status(404).entity("Notices not found").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotice(@PathParam("id") Integer id) {
        Notice notice = noticeService.getNotice(id);
        if (notice != null) {
            return Response.status(200).entity(notice).build();
        } else {
            return Response.status(404).entity("Notice by id=" + id + " not found").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNotice(Notice notice) {
        if (noticeService.addNotice(notice)) {
            return Response.status(200).entity("Notice added").build();
        } else {
            return Response.status(406).entity("Not acceptable").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNotice(Notice notice) {
        if (noticeService.updateNotice(notice)) {
            return Response.status(200).build();
        } else {
            return Response.status(404).entity("Not updated").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeNotice(@PathParam("id") Integer id) {
        if (noticeService.removeNotice(id)) {
            return Response.status(204).entity("Person deleted").build();
        } else {
            return Response.status(404).entity("Person by id " + id + " not found").build();
        }
    }
}
