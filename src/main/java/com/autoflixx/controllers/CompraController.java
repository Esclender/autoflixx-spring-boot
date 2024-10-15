package com.autoflixx.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.autoflixx.models.CompraModel;
import com.autoflixx.models.ConfiteriaModel;
import com.autoflixx.models.MovieModel;
import com.autoflixx.models.Product;
import com.autoflixx.models.SpotsEntradasModel;
import com.autoflixx.services.IConfiteriaService;
import com.autoflixx.services.IMovieService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.ArrayList;

@Controller
@RequestMapping("/movie")
@SessionAttributes("compraModel")
public class CompraController {

  @Autowired
  private IMovieService movieService;

  @Autowired
  private IConfiteriaService confiteriaService;

  @ModelAttribute("producto")
  public CompraModel ModeloProducto() {
    return new CompraModel();
  }

  @GetMapping("/view/{id}/entradas")
  public String getEntradasPreview(Model model,
      @PathVariable("id") int idMovie) {
    MovieModel movie = movieService.getMovieById(idMovie);
    movie.setSpotsEntradasModels();

    List<SpotsEntradasModel> parkingSpots = movie.getSpotsEntradas();

    int midIndex = parkingSpots.size() / 2;
    List<SpotsEntradasModel> firstHalfParkingSpots = parkingSpots.subList(0, midIndex);
    List<SpotsEntradasModel> secondHalfParkingSpots = parkingSpots.subList(midIndex, parkingSpots.size());

    model.addAttribute("firstHalfParkingSpots", firstHalfParkingSpots);
    model.addAttribute("secondHalfParkingSpots", secondHalfParkingSpots);

    model.addAttribute("movie", movie);
    model.addAttribute("compraModel", new CompraModel(movie));

    return "steps/entradas/index";
  }

  @PostMapping("/view/confiteria")
  public String seleccionarParkingSpot(@ModelAttribute("compraModel") CompraModel compraModel, Model model) {
    SpotsEntradasModel selectedParkingSpot = compraModel.getParkingSpot();
    compraModel.setParkingSpot(selectedParkingSpot);

    List<ConfiteriaModel> product = confiteriaService.getProducts();

    model.addAttribute("product", product);
    model.addAttribute("parkingSpot", compraModel.getParkingSpot());
    model.addAttribute("movie", compraModel.getMovie());
    model.addAttribute("total", compraModel.getTotal());
    return "steps/confiteria/index";
  }

  @GetMapping("/view/{id}/confiteria")
  public String getConfiteriaPage(@PathVariable("id") int idMovie, Model model) {
    List<ConfiteriaModel> product = confiteriaService.getProducts();

    model.addAttribute("product", product);
    return "steps/confiteria/index";
  }

  @PostMapping("/view/pago")
  public String addProduct(
      @RequestParam("productId") String productId,
      @RequestParam("productImage") String productImage,
      @RequestParam("productPrecio") String productPrecio,
      @RequestParam("productNombre") String productNombre,
      @RequestParam("amount") int amount,
      @ModelAttribute("compraModel") CompraModel compraModel,
      Model model) {

    Integer id = Integer.valueOf(productId);
    double precio = Double.valueOf(productPrecio);
    compraModel.setConfiteriaSelection(new ArrayList<Product>());
    compraModel.addProduct(new Product(id, amount, productImage, precio, productNombre));

    model.addAttribute("parkingSpot", compraModel.getParkingSpot());
    model.addAttribute("movie", compraModel.getMovie());
    model.addAttribute("total", compraModel.getTotal());
    model.addAttribute("productos", compraModel.getConfiteriaSelection());

    return "steps/pagos/pago";
  }

  @PostMapping("/view/factura")
  public String getFacturaView(Model model, @ModelAttribute("compraModel") CompraModel compraModel) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM, yyyy", new Locale("es", "ES"));
    String formattedDate = LocalDate.now().format(formatter);

    Random random = new Random();
    int code = random.nextInt(900000) + 100000;

    model.addAttribute("code", code);

    model.addAttribute("parkingSpot", compraModel.getParkingSpot());
    model.addAttribute("movie", compraModel.getMovie());
    model.addAttribute("total", compraModel.getTotal());
    model.addAttribute("productos", compraModel.getConfiteriaSelection());
    model.addAttribute("date", formattedDate);

    compraModel.setConfiteriaSelection(null);
    return "steps/factura/index";
  }
}
