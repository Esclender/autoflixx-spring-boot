package com.autoflixx.rest_controller.confiteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.autoflixx.models.ConfiteriaModel;
import com.autoflixx.services.IConfiteriaService;

@RestController
@RequestMapping("/rest-confiteria")
public class ConfiteriaRestController {

    @Autowired
    IConfiteriaService services;

    @GetMapping("/get-all")
    public List<ConfiteriaModel> getAllConfiterias(Model model) {
        return services.getCombos();
    }

     @GetMapping("/get/{id}")
    public ConfiteriaModel getConfiteriaById(@PathVariable("id") int id) {
        return services.getComboById(id).orElse(null);
    }

    @PostMapping("/add")
    public void addConfiteria(@RequestBody ConfiteriaModel confiteria) {
        services.crearProductoConfi(confiteria);
    }

    @PutMapping("/edit-combos/{id}")
    public void editConfiteria(@PathVariable("id") int id, @RequestBody ConfiteriaModel confiteria) {
        confiteria.setId(id);
        services.crearProductoConfi(confiteria);
    }

    @DeleteMapping("/borrar/{id}")
    public void borrarProducto(@PathVariable("id") Integer id, @RequestBody ConfiteriaModel confiteria) {
        confiteria.setId(id);
        services.borrarProductoConfi(id);

    }  
}
