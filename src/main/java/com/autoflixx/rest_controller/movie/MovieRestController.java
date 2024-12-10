package com.autoflixx.rest_controller.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoflixx.models.MovieModel;
import com.autoflixx.services.IMovieService;
import com.autoflixx.services.IMovieService;

@RestController
@RequestMapping("/rest-movie")
public class MovieRestController {

    @Autowired
    IMovieService services;

    @GetMapping("/get-all")
    public List<MovieModel> getAllMovies(Model model) {
        return services.getAllMovies();
    }

    @GetMapping("/get-id/{id}")
    public MovieModel getMovieById(@PathVariable("id") int id) {
        return services.getMovieById(id);
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody MovieModel Movie) {
        services.saveMovie(Movie);
    }

    @PutMapping("/edit-movie/{id}")
    public void editMovie(@PathVariable("id") int id, @RequestBody MovieModel Movie) {
        Movie.setId(id);
        services.saveMovie(Movie);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrarProducto(@PathVariable("id") Integer id, @RequestBody MovieModel Movie) {
        Movie.setId(id);
        services.deleteMovie(id);;

    }
}
