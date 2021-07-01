package com.crud.movies.crud.service.Interfaces;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

import com.crud.movies.crud.model.Entity.Movie;

public interface MovieService {
    Movie createMovie(Movie movie);

    List<Movie> listMovies();

    Optional<Movie> searchMovieByName(String name);

    Movie updateMovie(Movie movie);
}
