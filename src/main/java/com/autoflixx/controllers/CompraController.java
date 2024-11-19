package com.autoflixx.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Random;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.autoflixx.models.CompraModel;
import com.autoflixx.models.ConfiteriaModel;
import com.autoflixx.models.MovieModel;
import com.autoflixx.models.SpotsEntradasModel;
import com.autoflixx.services.ICompraService;
import com.autoflixx.services.IConfiteriaService;
import com.autoflixx.services.IMovieService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("/movie")
@SessionAttributes("compraModel")
public class CompraController {

  @Autowired
  private IMovieService movieService;

  @Autowired
  private IConfiteriaService confiteriaService;

  @Autowired
  private ICompraService compraService;

  @ModelAttribute("compraModel")
  public CompraModel initializeCompraModel() {
    return new CompraModel();
  }

  @GetMapping("/view/{id}/entradas")
  public String getEntradasPreview(@PathVariable("id") int idMovie,
      @ModelAttribute("compraModel") CompraModel compraModel, Model model) {
    MovieModel movie = movieService.getMovieById(idMovie);
    // movie.setSpotsEntradasModels();

    List<SpotsEntradasModel> parkingSpots = movie.getSpotsEntradas();
    int midIndex = parkingSpots.size() / 2;
    List<SpotsEntradasModel> firstHalfParkingSpots = parkingSpots.subList(0, midIndex);
    List<SpotsEntradasModel> secondHalfParkingSpots = parkingSpots.subList(midIndex, parkingSpots.size());

    model.addAttribute("firstHalfParkingSpots", firstHalfParkingSpots);
    model.addAttribute("secondHalfParkingSpots", secondHalfParkingSpots);
    model.addAttribute("movie", movie);

    compraModel.setMovie(movie);

    return "steps/entradas/index";
  }

  @PostMapping("/view/confiteria")
  public String seleccionarParkingSpot(@ModelAttribute("compraModel") CompraModel compraModel, Model model) {
    List<ConfiteriaModel> combos = confiteriaService.getCombos();

    model.addAttribute("combos", combos);
    model.addAttribute("columna", compraModel.getColumna());
    model.addAttribute("fila", compraModel.getFila());
    model.addAttribute("spotPrice", compraModel.spotPrice());
    model.addAttribute("movie", compraModel.getMovie());
    model.addAttribute("total", compraModel.getTotal());

    return "steps/confiteria/index";
  }

  @GetMapping("/view/{id}/confiteria")
  public String getConfiteriaPage(@PathVariable("id") int idMovie,
      @ModelAttribute("compraModel") CompraModel compraModel, Model model) {
    List<ConfiteriaModel> combos = confiteriaService.getCombos();

    model.addAttribute("combos", combos);
    model.addAttribute("columna", compraModel.getColumna());
    model.addAttribute("fila", compraModel.getFila());
    model.addAttribute("spotPrice", compraModel.spotPrice());
    model.addAttribute("movie", compraModel.getMovie());
    model.addAttribute("total", compraModel.getTotal());

    return "steps/confiteria/index";
  }

  @PostMapping("/view/pago")
  public String addProduct(
      @RequestParam("comboId") String comboId,
      @RequestParam("amount") int comboAmountToBuy,
      @ModelAttribute("compraModel") CompraModel compraModel,
      BindingResult result,
      Model model) {

    if (result.hasErrors()) {
      for (ObjectError error : result.getAllErrors()) {
        System.out.println("Ocurrio un error: " + error.getDefaultMessage());
      }
      return "steps/confiteria/index";
    }

    if (comboAmountToBuy > 0) {
      ConfiteriaModel combo = confiteriaService.getComboById(Integer.parseInt(comboId)).get();
      compraModel.addCombo(combo);
      compraModel.setAmountOfProductsToBuy(comboAmountToBuy);
    }

    model.addAttribute("columna", compraModel.getColumna());
    model.addAttribute("fila", compraModel.getFila());
    model.addAttribute("movie", compraModel.getMovie());
    model.addAttribute("total", compraModel.getTotal());
    model.addAttribute("spotPrice", compraModel.spotPrice());
    model.addAttribute("combosSelected", compraModel.getConfiteriaSelection());//
    model.addAttribute("combosAmountToBuy", compraModel.getAmountOfProductsToBuy());// combosAmountToBuy

    return "steps/pagos/pago";
  }

  @PostMapping("/view/factura")
  public String getFacturaView(
      @ModelAttribute("compraModel") CompraModel compraModel, Model model) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
        "d 'de' MMMM, yyyy",
        Locale.of("es", "ES"));
    String formattedDate = LocalDate.parse("2007-10-03").format(formatter);

    Random random = new Random();
    int code = random.nextInt(900000) + 100000;

    model.addAttribute("code", code);
    model.addAttribute("columna", compraModel.getColumna());
    model.addAttribute("fila", compraModel.getFila());
    model.addAttribute("spotPrice", compraModel.spotPrice());
    model.addAttribute("movie", compraModel.getMovie());
    model.addAttribute("total", compraModel.getTotal());
    model.addAttribute("combos", compraModel.getConfiteriaSelection());
    model.addAttribute("date", formattedDate);

    // Save the compraModel to the database
    compraService.saveCompra(compraModel);

    return "steps/factura/index";
  }

  @InitBinder
  public void initBinder(WebDataBinder webDataBinder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
  }
}
