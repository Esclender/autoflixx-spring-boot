package com.autoflixx.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.autoflixx.models.ConfiteriaModel;
import com.autoflixx.services.IConfiteriaService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/confiteria")
public class ConfiteriaController {

    @Autowired
    IConfiteriaService confiteriaServ;

    // Guardar imagen en el servidor
    public String saveImage(MultipartFile file) throws IOException {
        String imageName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path imagePath = Paths.get("src/main/resources/static/imgs/confiteria/" + imageName);

        // Asegurarse de que el directorio exista
        Files.createDirectories(imagePath.getParent());
        Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        return imageName;
    }

    // Ver menu principal y lista de productos-confiteria
    @GetMapping("/admin")
    public String getAllMoviesForAdmin(Model model) {
        List<ConfiteriaModel> confiterias = confiteriaServ.getCombos();
        model.addAttribute("confiterias", confiterias);
        return "admin/confiteria/home-confiteria";
    }

    // Formulario-vista de guardar y actualizar
    @GetMapping("/admin/add-confiteria")
    public String guardarConfiteriaVista(Model model) {
        return "admin/confiteria/add-confiteria";
    }

    // Recibir formulario
    @PostMapping("/admin/add-product")
    public String GuardarProductoConfi(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") double precio,
            @RequestParam("disponible") Integer disponible,
            @RequestParam("categoria") String categoria,
            @RequestParam("imagen") MultipartFile file,
            RedirectAttributes redirectAttributes
            /*BindingResult result*/) { // al no usar @ModelAtribute no es necesario y al contrario genera error

        // si el archivo esta vacio
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Debe seleccionar un archivo.");
            System.out.println("Debe seleccionar un archivo.");
            return "redirect:/confiteria/admin/add-confiteria";
        }

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Debe seleccionar un archivo.");
            System.out.println("Debe seleccionar un archivo.");
            return "redirect:/confiteria/admin/add-confiteria";
        }

        // otros errores
        // if (result.hasErrors()) {
        //     redirectAttributes.addFlashAttribute("error", "Errores en el formulario.");
        //     return "redirect:/confiteria/admin/add-confiteria";
        // }

        try {
            // Guardar imagen
            String imageName = saveImage(file);
            ConfiteriaModel confiteria = new ConfiteriaModel();
            
            confiteria.setNombre(nombre);
            confiteria.setDescripcion(descripcion);
            confiteria.setPrecio(precio);
            confiteria.setDisponible(disponible);
            confiteria.setCategoria(categoria);
            confiteria.setImagen(imageName);

            // Guardar producto
            confiteriaServ.crearProductoConfi(confiteria);
            
            redirectAttributes.addFlashAttribute("success", "Producto creado exitosamente.");
        System.out.println("Producto creado exitosamente.");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al guardar la imagen: " + e.getMessage());
            return "redirect:/confiteria/admin/add-confiteria";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al guardar el producto: " + e.getMessage());
            System.out.println("Ocurrió un error al guardar el producto.");
        }

        return "redirect:/confiteria/admin/add-confiteria";
    }
}
