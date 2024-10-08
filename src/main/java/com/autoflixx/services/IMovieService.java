package com.autoflixx.services;

import java.util.List;

import com.autoflixx.models.MovieModel;

public interface IMovieService {
    List<MovieModel> getAllMovies();
    
    MovieModel getMovieById(Integer idMovie);

}
