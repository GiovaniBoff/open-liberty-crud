package com.crud.movies.crud.api.controller;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.crud.movies.crud.api.dto.UserDTO;
import com.crud.movies.crud.api.helpers.ResponseBodyFormat;
import com.crud.movies.crud.model.Entity.User;
import com.crud.movies.crud.service.Interfaces.UserService;
import com.crud.movies.crud.service.errors.ServiceRuleException;

@RequestScoped
@Path("/v1/user")
public class UserController {

  @Inject
  private UserService userService;

  @Inject
  private ValidatorFactory validatorFactory;

  @Inject
  private ResponseBodyFormat responseBodyFormat;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response save(final UserDTO userDto) throws ServiceRuleException {

    Validator validator = validatorFactory.getValidator();
    Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDto);

    if (!violations.isEmpty()) {

      violations.stream().forEach((error) -> {
        responseBodyFormat.addError(error.getMessageTemplate());
      });
      return Response.status(Status.BAD_REQUEST).entity(responseBodyFormat).build();

    }

    User user = User.builder().nome(userDto.getNome()).email(userDto.getEmail()).senha(userDto.getSenha()).build();
    userService.createUser(user);
    responseBodyFormat.setData(user);
    return Response.status(Status.OK).entity(responseBodyFormat).build();

  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response index() {
    List<User> users = userService.listUsers();
    return Response.ok(users).build();
  }
}
