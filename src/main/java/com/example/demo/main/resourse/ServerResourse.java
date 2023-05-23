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
import com.example.demo.main.util.Jwt;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Date;
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
    private final Jwt jwt = new Jwt();
    private long script_start;
    @GET
    @Path("/list")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getResults(@HeaderParam("Auth_Token") String token) {
        String login = jwt.decodeToken(token);
        System.out.println(login);
        return createResponse(Response.Status.OK, Response.Status.OK.getStatusCode(), "results retrieved", "", Map.of("results", resultService.getResult(login)));
    }

    @POST
    @Path("/save")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response saveResult(@HeaderParam("Auth_Token") String token, RowResult result) {
        System.out.println("SAVE METHOD");
        script_start = new Date().getTime();
        String login = jwt.decodeToken(token);
        System.out.println(login);
//        RowResult result = new RowResult(1,1,1);
        return createResponse(Response.Status.CREATED, Response.Status.CREATED.getStatusCode(), "result created", "", Map.of("result", resultService.saveResult(Check.getResult(result), login, script_start)));

    }

    @POST
    @Path("/auth")
    public Response auth(User user) {
        Status status = userService.authorised(user);
        switch (status) {
            case SUCCESS : {
                return createResponse(Response.Status.CREATED, Response.Status.CREATED.getStatusCode(), "user successfully auth", String.valueOf(status), jwt.createToken(user.getLogin()));
            }
            case WRONG_PASSWORD : {
                return createResponse(Response.Status.FORBIDDEN, Response.Status.FORBIDDEN.getStatusCode(), "wrong password", String.valueOf(status));
            }
            case THIS_LOGIN_IS_NOT_EXIST : {
                return createResponse(Response.Status.FORBIDDEN, Response.Status.FORBIDDEN.getStatusCode(), "this login is not exist", String.valueOf(status));
            }
            default : {
                return createResponse(Response.Status.BAD_REQUEST, Response.Status.BAD_REQUEST.getStatusCode(), "this status doesn't exist", String.valueOf(status));
            }
        }
    }

    @POST
    @Path("/register")
    public Response register(User user) {
        Status status = userService.register(user);
        switch (status) {
            case SUCCESS : {
                return createResponse(Response.Status.CREATED, Response.Status.CREATED.getStatusCode(), "user successfully registered", String.valueOf(status), jwt.createToken(user.getLogin()));
            }
            case THIS_LOGIN_ALREADY_EXIST : {
                return createResponse(Response.Status.FORBIDDEN, Response.Status.FORBIDDEN.getStatusCode(), "this login already exist", String.valueOf(status));
            }
            default : {
                return createResponse(Response.Status.BAD_REQUEST, Response.Status.BAD_REQUEST.getStatusCode(), "this status doesn't exist", String.valueOf(status));
            }
        }
    }

    private String createJson(MyResponse response){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String json;
        try {
            json = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);
        return json;
    }
    private Response createResponse(Response.Status status, int statusCode, String msg, String devMsg, Map<?, ?> data){
        return Response.ok(createJson(new MyResponse(status, statusCode, msg, devMsg, data)), MediaType.APPLICATION_JSON).build();
    }
    private Response createResponse(Response.Status status, int statusCode, String msg, String devMsg, String token){
        return Response.ok(createJson(new MyResponse(status, statusCode, msg, devMsg)), MediaType.APPLICATION_JSON).header("Auth_Token", token).build();
    }
    private Response createResponse(Response.Status status, int statusCode, String msg, String devMsg){
        return Response.ok(createJson(new MyResponse(status, statusCode, msg, devMsg)), MediaType.APPLICATION_JSON).build();
    }
}
