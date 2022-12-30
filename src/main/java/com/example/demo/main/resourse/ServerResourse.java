package com.example.demo.main.resourse;


import com.example.demo.main.MyResponse;
import com.example.demo.main.auth.Status;
import com.example.demo.main.auth.User;
import com.example.demo.main.auth.UserRepository;
import com.example.demo.main.auth.UserServiceImpl;
import com.example.demo.main.data.ResultRepository;
import com.example.demo.main.data.ResultServiceImpl;
import com.example.demo.main.data.RowResult;
import com.example.demo.main.util.Check;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;



@RequestScoped
@Path("/server")
@Produces("application/json")
@Consumes("application/json")
public class ServerResourse {
    private final ResultRepository resultRepository = new ResultRepository();
    private final UserRepository userRepository = new UserRepository();
    private final ResultServiceImpl resultService = new ResultServiceImpl(resultRepository);
    private final UserServiceImpl userService = new UserServiceImpl(userRepository);
    @GET
    @Path("/list")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getResults() {

        return createResponse(Response.Status.OK, Response.Status.OK.getStatusCode(), "results retrieved", "", Map.of("results", resultService.getResult()));


    }

    @GET
    @Path("/save")
    public Response saveResult(RowResult result) {
        return createResponse(Response.Status.CREATED, Response.Status.CREATED.getStatusCode(), "result created", "", Map.of("result", resultService.saveResult(Check.getResult(result))));


    }

    @GET
    @Path("/auth")
    public Response auth(User user) {
        Status status = userService.authorised(user);
        switch (status) {
            case SUCCESS -> {
                return createResponse(Response.Status.CREATED, Response.Status.CREATED.getStatusCode(), "user successfully auth", String.valueOf(status));

            }
            case WRONG_PASSWORD -> {
                return createResponse(Response.Status.FORBIDDEN, Response.Status.FORBIDDEN.getStatusCode(), "wrong password", String.valueOf(status));
            }
            case THIS_LOGIN_IS_NOT_EXIST -> {
                return createResponse(Response.Status.FORBIDDEN, Response.Status.FORBIDDEN.getStatusCode(), "this login is not exist", String.valueOf(status));
            }
            default -> {
                return createResponse(Response.Status.BAD_REQUEST, Response.Status.BAD_REQUEST.getStatusCode(), "this status doesn't exist", String.valueOf(status));
            }
        }
    }

    @GET
    @Path("/register")
    public Response register(User user) {
        Status status = userService.register(user);
        switch (status) {
            case SUCCESS -> {
                return createResponse(Response.Status.CREATED, Response.Status.CREATED.getStatusCode(), "user successfully registered", String.valueOf(status));
            }
            case THIS_LOGIN_ALREADY_EXIST -> {
                return createResponse(Response.Status.FORBIDDEN, Response.Status.FORBIDDEN.getStatusCode(), "this login already exist", String.valueOf(status));
            }
            default -> {
                return createResponse(Response.Status.BAD_REQUEST, Response.Status.BAD_REQUEST.getStatusCode(), "this status doesn't exist", String.valueOf(status));
            }
        }
    }
    private Response createResponse(Response.Status status, int statusCode, String msg, String devMsg, Map<?, ?> data){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(new MyResponse(status, statusCode, msg, devMsg, data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);

        return Response.ok(json, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
    }
    private Response createResponse(Response.Status status, int statusCode, String msg, String devMsg){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(new MyResponse(status, statusCode, msg, devMsg));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);

        return Response.ok(json, MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
    }
}
