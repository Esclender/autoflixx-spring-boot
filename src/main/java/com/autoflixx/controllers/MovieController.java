package com.autoflixx.controllers;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        System.out.println("Movie: " + movie);
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
        List<MovieModel> movie = service.getAllMovies();
        model.addAttribute("movies", movie);
        System.out.println("Movie: " + movie);
        return "admin/home"; // Vista para el administrador
    }

    @GetMapping("/admin/add-movie")
    public String saveMovie(Model model) {
        List<MovieModel> movie = service.getAllMovies();
        model.addAttribute("movie", movie);
        System.out.println("Movie: " + movie);
        return "admin/movies/add-movie"; // Vista para el administrador
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteMovie(@PathVariable("id") int idMovie, Model model) {
        service.deleteMovie(idMovie);
        return "redirect:/movie/admin";
    }

    @GetMapping("/admin/update/{id}")
    public String updateMovieForm(@PathVariable("id") int idMovie, Model model) {
        MovieModel movie = service.getMovieById(idMovie);
        model.addAttribute("movie", movie);
        return "admin/movies/update";
    }

    @PostMapping("/admin/update/{id}")
    public String updateMovie(@PathVariable("id") int idMovie, @ModelAttribute("movie") MovieModel movie,
            // @RequestParam(value = "posterImg", required = false) MultipartFile posterImg,
            // @RequestParam(value = "bannerImg", required = false) MultipartFile bannerImg,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "admin/update-movie";
        } else {
            System.out.println("Movie: " + movie);
        }

        // Handle file uploads
        // if (posterImg != null && !posterImg.isEmpty()) {
        // // Save the poster image file
        // String posterImgPath = saveFile(posterImg);
        // movie.setPosterImg(posterImgPath);
        // } else if (movie.getPosterImg() == null || movie.getPosterImg().isEmpty()) {
        // movie.setPosterImg("empty-image.png");
        // }

        // if (bannerImg != null && !bannerImg.isEmpty()) {
        // // Save the banner image file
        // String bannerImgPath = saveFile(bannerImg);
        // movie.setBannerImg(bannerImgPath);
        // } else if (movie.getBannerImg() == null || movie.getBannerImg().isEmpty()) {
        // movie.setBannerImg("empty-image.png");
        // }

        // Parse the date string to a Date object if it's not null
        if (movie.getFechaPub() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String fechaPubString = formatter.format(movie.getFechaPub()); // Convert Date to String
                Date date = formatter.parse(fechaPubString); // Parse the String back to Date
                movie.setFechaPub(date); // Assuming fechaPub is a Date field in MovieModel
            } catch (ParseException e) {
                e.printStackTrace();
                result.rejectValue("fechaPubString", "error.movie", "Formato de fecha inválido");
                return "admin/update-movie";
            }
        }

        movie.setId(idMovie); // Set the ID of the movie to the one from the path variable
        service.updateMovie(movie);
        redirectAttributes.addFlashAttribute("msg", "Película actualizada con éxito");
        return "redirect:/movie/admin";
    }

    @GetMapping("/admin/add")
    public String addMovieForm(Model model) {
        model.addAttribute("movie", new MovieModel());
        return "admin/add-movie";
    }

    @PostMapping("/admin/add")
    public String saveMovie(@ModelAttribute("movie") MovieModel movie, BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio un error: " + error.getDefaultMessage());
            }
            return "admin/add-movie";
        } else {
            System.out.println("Movie: " + movie);
        }

        // Parse the date string to a Date object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String fechaPubString = formatter.format(movie.getFechaPub()); // Convert Date to String
            Date date = formatter.parse(fechaPubString); // Parse the String back to Date
            movie.setFechaPub(date); // Assuming fechaPub is a Date field in MovieModel
        } catch (ParseException e) {
            e.printStackTrace();
            result.rejectValue("fechaPubString", "error.movie", "Formato de fecha inválido");
            return "admin/addMovie";
        }

        service.saveMovie(movie);
        redirectAttributes.addFlashAttribute("msg", "Película guardada con éxito");
        return "redirect:/movie/admin";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    private String saveFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String filePath = "path/to/save/directory/" + fileName;
        try {
            file.transferTo(new File(filePath)); // Guardar archivo físicamente
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

}