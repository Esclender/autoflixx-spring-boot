package com.autoflixx.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.autoflixx.models.ConfiteriaModel;
import com.autoflixx.models.MovieModel;
import com.autoflixx.services.IConfiteriaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/confiteria")
public class ConfiteriaController {

    @Autowired
    IConfiteriaService confiteriaServ;
    
    // Ver menu principal y lista de productos-confiteria
    @GetMapping("/admin")
    public String getAllMoviesForAdmin(Model model) {
        List<ConfiteriaModel> confiterias = confiteriaServ.getCombos();
        model.addAttribute("confiterias", confiterias);
        return "admin/confiteria/home-confiteria";
    }

    //Formulario-vista de guardar y actualizar 
    @GetMapping("/admin/add-confiteria")
    public String guardarConfiteriaVista(Model model) {
        return "admin/confiteria/add-confiteria";
    }
    
    @PostMapping("/admin/add-product")
    public String GuardarProductoConfi(@RequestBody ConfiteriaModel conteria) {
        //confiteriaServ.crearProductoConfi();
        return "";
    }
    
}
