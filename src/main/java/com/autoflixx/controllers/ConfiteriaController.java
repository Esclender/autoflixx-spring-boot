package com.autoflixx.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.autoflixx.models.ConfiteriaModel;
import com.autoflixx.services.IConfiteriaService;

@Controller
@RequestMapping("/confiteria")
public class ConfiteriaController {

    @Autowired
    private IConfiteriaService service;
 
	  @GetMapping("/")
	  public String getConfiteriaPage(Model model) {
		  List<ConfiteriaModel> product = service.getProducts();
		  
		  // Aquí imprimes en la consola del servidor los productos obtenidos
	      System.out.println("Productos obtenidos desde el backend:");
	        product.forEach(p -> System.out.println(p.toString()));
		  model.addAttribute("product", product);
		  return "steps/confiteria/index";
	  }
  
}
