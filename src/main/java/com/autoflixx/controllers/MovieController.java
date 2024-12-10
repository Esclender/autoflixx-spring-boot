package com.autoflixx.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PutMapping;
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
        return "admin/movies/update-movie";
    }

    @PutMapping("/admin/edit-movie/{id}")
public String updateMovie(
        @PathVariable("id") int idMovie,
        @RequestParam("titulo") String tituloN,
        @RequestParam("descripcion") String descripcionN,
        @RequestParam("director") String directorN,
        @RequestParam("genero") String generoN,
        @RequestParam(value = "imagen", required = false) MultipartFile fileN,
        RedirectAttributes redirectAttributes) {

    Optional<MovieModel> movieOg = Optional.ofNullable(service.getMovieById(idMovie));

    if (!movieOg.isPresent()) {
        redirectAttributes.addFlashAttribute("error", "La película no existe.");
        return "redirect:/movie/admin";
    }

    try {
        String imagenNueva = movieOg.get().getPosterImg();

        // Validar si se subió una nueva imagen
        if (fileN != null && !fileN.isEmpty()) {
            String nombreImgOg = fileN.getOriginalFilename();
            if (nombreImgOg == null || nombreImgOg.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("warning", "El archivo de imagen debe tener un nombre válido.");
                return "redirect:/movie/admin/edit/" + idMovie;
            }

            // Eliminar la imagen anterior y guardar la nueva
            deleteImage(movieOg.get().getPosterImg());
            imagenNueva = saveImage(fileN);
        }

        // Actualizar película
        MovieModel movieActualizada = new MovieModel();
        movieActualizada.setId(idMovie);
        movieActualizada.setNombre(tituloN);
        movieActualizada.setSinopsis(descripcionN);
        movieActualizada.setDirector(directorN);
        movieActualizada.setGenero(generoN);
        movieActualizada.setPosterImg(imagenNueva);

        service.updateMovie(movieActualizada);

        redirectAttributes.addFlashAttribute("success", "Película actualizada exitosamente.");
        return "redirect:/movie/admin";

    } catch (IOException e) {
        redirectAttributes.addFlashAttribute("error", "Error al procesar la imagen: " + e.getMessage());
        return "redirect:/movie/admin/edit/" + idMovie;
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error al actualizar la película: " + e.getMessage());
        return "redirect:/movie/admin/edit/" + idMovie;
    }
}

// Guardar Imagen
public String saveImage(MultipartFile file) throws IOException {
    String imageName = file.getOriginalFilename();

    if (imageName == null || imageName.isEmpty()) {
        throw new IOException("El archivo no tiene un nombre válido.");
    }

    Path rutaImg = Paths.get("src/main/resources/static/imgs/movies/" + imageName);
    Files.createDirectories(rutaImg.getParent());
    Files.copy(file.getInputStream(), rutaImg, StandardCopyOption.REPLACE_EXISTING);

    return imageName;
}

// Borrar Imagen
public boolean deleteImage(String nombreImg) {
    if (nombreImg == null || nombreImg.isEmpty()) {
        System.out.println("El nombre de la imagen no es válido.");
        return false;
    }

    Path imgRuta = Paths.get("src/main/resources/static/imgs/movies/" + nombreImg);

    try {
        if (Files.exists(imgRuta)) {
            Files.delete(imgRuta);
            System.out.println("Imagen eliminada con éxito: " + nombreImg);
            return true;
        } else {
            System.out.println("La imagen no existe: " + nombreImg);
            return false;
        }
    } catch (IOException e) {
        System.out.println("Error al intentar eliminar la imagen: " + e.getMessage());
        return false;
    }
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