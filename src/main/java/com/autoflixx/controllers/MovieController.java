package com.autoflixx.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.autoflixx.models.MovieModel;
import com.autoflixx.services.IMovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private IMovieService service;

	@GetMapping("/")
	public String getAllMovies(Model model) {
		List<MovieModel> movie = service.getAllMovies();
		model.addAttribute("movie", movie);
		return "home";
	}

	@GetMapping("/view/{id}")
	public String verDetalles(@PathVariable("id") int idMovie, Model model) {
		MovieModel movie = service.getMovieById(idMovie);
		model.addAttribute("movie", movie);
		return "detalles";
	}

	@GetMapping("/admin")
	public String getAllMoviesForAdmin(Model model) {
		List<MovieModel> movies = service.getAllMovies();
		model.addAttribute("movie", movies); // Añadimos la lista de películas
		return "admin"; // Vista para el administrador
	}
}
