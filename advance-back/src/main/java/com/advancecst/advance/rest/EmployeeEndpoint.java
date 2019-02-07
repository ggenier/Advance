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

import com.advancecst.advance.model.Employee;
import com.advancecst.advance.repository.EmployeeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/employees")
@Api("Employee")
public class EmployeeEndpoint {
   @Inject
   private EmployeeRepository employeeRepository;

   // GET http://127.0.0.1:8080/advance-back/api/employees/getEmployeeByHRId/SAL6
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/getEmployeeByHRId/{idEmployee}")
   @ApiOperation(value = "Retun an employee given an HR Id", response = Employee.class)
   @ApiResponses({ @ApiResponse(code = 200, message = "Employee found"),
         @ApiResponse(code = 400, message = "Invalid ID"), @ApiResponse(code = 404, message = "Employee not found") })
   public Response getEmployeeByHRId(@PathParam("idEmployee") String idEmployee) {
      Employee employeeTrouve = employeeRepository.findByHRId(idEmployee);

      if (employeeTrouve == null)
         return Response.status(Response.Status.NO_CONTENT).build();

      return Response.ok(employeeTrouve).build();
   }

   // GET
   // http://127.0.0.1:8080/advance-back/api/employees/getEmployeeByTechnicalId/1
   //
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/getEmployeeByTechnicalId/{id : \\d+}")
   @ApiOperation(value = "Return employee given by is Technical ID", response = Employee.class)
   @ApiResponses({ @ApiResponse(code = 200, message = "Employee found"),
         @ApiResponse(code = 400, message = "Invalid ID"), @ApiResponse(code = 404, message = "Employee not found")

   })
   public Response getEmployeeByTechnicalId(@PathParam("id") Long id) {
      Employee employeeTrouve = employeeRepository.findByTechnicalId(id);

      if (employeeTrouve == null)
         return Response.status(Response.Status.NO_CONTENT).build();

      return Response.ok(employeeTrouve).build();
   }

   // GET http://127.0.0.1:8080/advance-back/api/employees/getAllEmployees
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/getAllEmployees")
   @ApiOperation(value = "Return list of employees", response = Employee.class, responseContainer = "List")
   @ApiResponses({ @ApiResponse(code = 200, message = "List of employees found"),
         @ApiResponse(code = 404, message = "List of employees not found") })
   public Response getAllEmployees() {
      List<Employee> allEmployees = employeeRepository.findAll();

      if (allEmployees.size() == 0) {
         return Response.status(Response.Status.NO_CONTENT).build();
      } else {
         return Response.ok(allEmployees).build();
      }
   }

   // POST http://127.0.0.1:8080/advance-back/api/employees/createEmployee
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/createEmployee")
   @ApiOperation(value = "Create an employee")
   @ApiResponses({ @ApiResponse(code = 201, message = "Employee created"),
         @ApiResponse(code = 415, message = "Format invalid") })
   public Response createEmployee(Employee employee, @Context UriInfo uriInfo) {
      employee = employeeRepository.create(employee);
      URI createdUri = uriInfo.getBaseUriBuilder().path(employee.getId().toString()).build();

      return Response.created(createdUri).build();
   }

   // DELETE
   // http://127.0.0.1:8080/advance-back/api/employees/deleteEmployeeByHRId/SAL6
   //
   //
   @DELETE
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/deleteEmployeeByHRId/{idEmployee}")
   @ApiOperation(value = "Delete an employee given Human Resources ID")
   @ApiResponses({ @ApiResponse(code = 204, message = "Employee deleted"),
         @ApiResponse(code = 400, message = "Invalid employee ID"),
         @ApiResponse(code = 500, message = "Employee not found") })
   public Response deleteEmployeeByHRId(@PathParam("idEmployee") String idEmployee) {
      // employeeRepository.deleteByHRId(idEmployee);
      employeeRepository.deleteByHRId(idEmployee);

      return Response.status(Response.Status.NO_CONTENT).build();
   }

   // DELETE
   // http://127.0.0.1:8080/advance-back/api/employees/deleteEmployeeByTechnicalId/1
   @DELETE
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/deleteEmployeeByTechnicalId/{id :\\d+}")
   @ApiOperation(value = "Delete an employee given technical ID")
   @ApiResponses({ @ApiResponse(code = 204, message = "Employee deleted"),
         @ApiResponse(code = 400, message = "Invalid employee ID"),
         @ApiResponse(code = 500, message = "Employee not found") })
   public Response deleteEmployeeByTechnicalId(@PathParam("id") Long id) {
      employeeRepository.deleteByTechnicalId(id);
      return Response.status(Response.Status.NO_CONTENT).build();
   }

   // GET http://127.0.0.1:8080/advance-back/api/employees/countEmployees
   @GET
   @Produces(MediaType.TEXT_PLAIN)
   @Path("/countEmployees")
   @ApiOperation(value = "Number of employees", response = Long.class)
   @ApiResponses({ @ApiResponse(code = 200, message = "Number of employees found"),
         @ApiResponse(code = 204, message = "No employee found") })
   public Response countEmployees() {
      Long countEmployee = employeeRepository.countAll();

      if (countEmployee == 0)
         return Response.status(Response.Status.NO_CONTENT).build();

      return Response.ok(countEmployee).build();
   }

}