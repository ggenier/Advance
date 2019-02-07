package com.advancecst.advance.rest;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.advancecst.advance.model.TrainingCenter;
import com.advancecst.advance.repository.TrainingCenterRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/trainingcenter")
@Api("TrainingCenter")
public class TrainingCenterEndpoint {
    @Inject
    private TrainingCenterRepository trainingCenterRepository;

    // GET
    // http://127.0.0.1:8080/advance-back/api/trainingcenter/getTrainingCenterById/1
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTrainingCenterById/{id}")
    @ApiOperation(value = "Retun a traning center given an Id", response = TrainingCenter.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Training center found"),
            @ApiResponse(code = 400, message = "Invalid ID"),
            @ApiResponse(code = 404, message = "Training center not found") })
    public Response getEmployeeByHRId(@PathParam("id") Long id) {
        TrainingCenter trainingCenterFind = trainingCenterRepository.find(id);

        if (trainingCenterFind == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.ok(trainingCenterFind).build();
    }

    // GET
    // http://127.0.0.1:8080/advance-back/api/trainingcenter/getAllTrainingCenters
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllTrainingCenters")
    @ApiOperation(value = "Return list of training center", response = TrainingCenter.class, responseContainer = "List")
    @ApiResponses({ @ApiResponse(code = 200, message = "List of employees found"),
            @ApiResponse(code = 404, message = "List of employees not found"),
            @ApiResponse(code = 204, message = "There is no employees") })
    public Response getAllTrainingCenters() {
        List<TrainingCenter> allTrainingCenterFound = trainingCenterRepository.findAll();

        if (allTrainingCenterFound.size() == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok(allTrainingCenterFound).build();
        }
    }

    // POST
    // http://127.0.0.1:8080/advance-back/api/trainingcenter/createTrainingCenter
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createTrainingCenter")
    @ApiOperation(value = "Create a training center")
    @ApiResponses({ @ApiResponse(code = 201, message = "Training center created"),
            @ApiResponse(code = 415, message = "Format invalid") })
    public Response createTrainingCenter(TrainingCenter trainingCenter, @Context UriInfo uriInfo) {
        trainingCenter = trainingCenterRepository.create(trainingCenter);
        URI createdUri = uriInfo.getBaseUriBuilder().path(trainingCenter.getId().toString()).build();

        return Response.created(createdUri).build();
    }

    // DELETE
    // http://127.0.0.1:8080/advance-back/api/trainingcenter/deleteTrainingCenterById/1
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/deleteTrainingCenterById/{id :\\d+}")
    @ApiOperation(value = "Delete a training center given ID")
    @ApiResponses({ @ApiResponse(code = 204, message = "Training center deleted"),
            @ApiResponse(code = 400, message = "Invalid training center ID"),
            @ApiResponse(code = 500, message = "Training center not found") })
    public Response deleteTrainingCenterById(@PathParam("id") Long id) {
        trainingCenterRepository.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // GET
    // http://127.0.0.1:8080/advance-back/api/trainingcenter/countTrainingCenter
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/countTrainingCenter")
    @ApiOperation(value = "Number of training center", response = Long.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "Number of training center found"),
            @ApiResponse(code = 204, message = "No training center found") })
    public Response countTrainingCenter() {
        Long countTrainingCenter = trainingCenterRepository.countAll();

        if (countTrainingCenter == 0)
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.ok(countTrainingCenter).build();
    }

}