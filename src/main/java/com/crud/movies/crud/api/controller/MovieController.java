package com.crud.movies.crud.api.controller;

import javax.inject.Inject;

import com.crud.movies.crud.service.Interfaces.MovieService;

@RequestScoped
@Path("/movies")
public class MovieController {

    @Inject
    MovieService movieService;

}
