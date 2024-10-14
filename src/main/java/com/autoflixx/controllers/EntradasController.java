package com.autoflixx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.autoflixx.models.MovieModel;
import com.autoflixx.services.IMovieService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/movie")
public class EntradasController {

  @Autowired
  private IMovieService service;

  @GetMapping("/view/{id}/entradas")
  public String getEntradasPreview(Model model, @PathVariable("id") int idMovie) {
    MovieModel movie = service.getMovieById(idMovie);

    model.addAttribute("movie", movie);
    model.addAttribute("parkingSpots", 32);

    return "steps/entradas/index";
  }

}
