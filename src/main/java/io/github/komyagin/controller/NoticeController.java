package io.github.komyagin.controller;

import io.github.komyagin.model.Notice;
import io.github.komyagin.service.NoticeService;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/notices")
@Api(value = "notices", description = "Operation with person notices in database")
public class NoticeController {

    private final NoticeService noticeService = new NoticeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Getting all of the notices from DB created by user",
            response = Notice.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notices are received"),
            @ApiResponse(code = 404, message = "DB has no notices"),
    })
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
    @ApiOperation(value = "Get notice by its ID", response = Notice.class, notes = "You need to know notice ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notice is received by ID"),
            @ApiResponse(code = 404, message = "Notice by ID not found")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotice(
            @ApiParam(value = "Notice ID that you need to get notice")
            @PathParam("id") Integer id) {
        Notice notice = noticeService.getNotice(id);
        if (notice != null) {
            return Response.status(200).entity(notice).build();
        } else {
            return Response.status(404).entity("Notice by id=" + id + " not found").build();
        }
    }

    @POST
    @ApiOperation(value = "Add notice")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notice added"),
            @ApiResponse(code = 406, message = "Not acceptable")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNotice(@ApiParam(value = "Notice object tah needs to be added to database", required = true)
                                      Notice notice) {
        if (noticeService.addNotice(notice)) {
            return Response.status(200).entity("Notice added").build();
        } else {
            return Response.status(406).entity("Not acceptable").build();
        }
    }

    @PUT
    @ApiOperation(value = "Update notice")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notice added"),
            @ApiResponse(code = 406, message = "Not acceptable")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateNotice(@ApiParam(value = "Notice object that needs to be added to database", required = true)
                                         Notice notice) {
        if (noticeService.updateNotice(notice)) {
            return Response.status(200).build();
        } else {
            return Response.status(404).entity("Not updated").build();
        }
    }

    @DELETE
    @ApiOperation(value = "Delete notice by its ID", notes = "You need to know notice ID to delete user. ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notice is deleted"),
            @ApiResponse(code = 404, message = "Notice not found")
    })
    @Path("/{id}")
    public Response removeNotice(@PathParam("id") Integer id) {
        if (noticeService.removeNotice(id)) {
            return Response.status(204).entity("Notice is deleted").build();
        } else {
            return Response.status(404).entity("Notice by id " + id + " not found").build();
        }
    }
}
