package com.crud.movies.crud.api.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crud.movies.crud.api.dto.UserDTO;
import com.crud.movies.crud.model.Entity.User;
import com.crud.movies.crud.service.Interfaces.UserService;

@RequestScoped
@Path("/v1/user")
public class UserController {

  @Inject
  UserService userService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response save(@Valid UserDTO userDto) {

    User user = User.builder().nome(userDto.getNome()).build();

    return Response.ok(userService.createUser(user)).build();
  }
}
