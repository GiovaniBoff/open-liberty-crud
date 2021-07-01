package com.crud.movies.crud.service;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.crud.movies.crud.model.Entity.Movie;
import com.crud.movies.crud.model.Repository.DAO.MovieDAO;
import com.crud.movies.crud.service.Interfaces.MovieService;

@Stateless
public class MovieServiceImpl implements MovieService {

    @Inject
    MovieDAO movieDAO;

    @Override
    public Movie createMovie(Movie movie) {

        return movieDAO.create(movie);
    }

    @Override
    public List<Movie> listMovies() {

        return movieDAO.read();
    }

    @Override
    public Optional<Movie> searchMovieByName(String name) {

        return movieDAO.find("titulo", name);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        // TODO Auto-generated method stub
        return null;
    }

}
