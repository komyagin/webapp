package io.github.komyagin.controller;

import io.github.komyagin.model.Notice;
import io.github.komyagin.service.NoticeService;

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
import java.util.List;

@Path("/notices")
public class NoticeController {

    NoticeService noticeService = new NoticeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotices() {
        List<Notice> notices = noticeService.getAllNotices();
        return Response.status(200).entity(notices).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotice(@PathParam("id") Integer id) {
        return Response.status(200).entity(noticeService.getNotice(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNotice(Notice notice) {
        noticeService.addNotice(notice);
        return Response.status(200).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNotice(Notice notice) {
        noticeService.updateNotice(notice);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeNotice(@PathParam("id") Integer id) {
        noticeService.removeNotice(id);
        return Response.status(200).build();
    }
}
