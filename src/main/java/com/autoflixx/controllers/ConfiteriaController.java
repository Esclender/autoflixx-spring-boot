package com.autoflixx.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.autoflixx.models.ConfiteriaModel;
import com.autoflixx.services.IConfiteriaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        model.addAttribute("timestamp", System.currentTimeMillis());
        return "admin/confiteria/home-confiteria";
    }

    // Formulario-vista de guardar y actualizar
    @GetMapping("/admin/add-confiteria")
    public String guardarConfiteriaVista() {
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
    /* BindingResult result */) { // al no usar @ModelAtribute no es necesario y al contrario genera error

        // si el archivo esta vacio
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("warning", "Debe seleccionar un archivo.");
            System.out.println("Debes seleccionar una imagen.");
            return "redirect:/confiteria/admin/add-confiteria";
        }

        // otros errores
        // if (result.hasErrors()) {
        // redirectAttributes.addFlashAttribute("error", "Errores en el formulario.");
        // return "redirect:/confiteria/admin/add-confiteria";
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
            return "redirect:/confiteria/admin";

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al guardar la imagen: " + e.getMessage());
            return "redirect:/confiteria/admin/add-confiteria";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al guardar el producto: " + e.getMessage());
            System.out.println("Ocurrió un error al guardar el producto.");
            return "redirect:/confiteria/admin/add-confiteria";
        }
    }

    // Configuraciones en el html y WebConfig para poder hacer el borrado con un DeleteMapping y ya no con GetMapping
    @DeleteMapping("/admin/borrar/{id}")
    public String borrarProducto(@PathVariable("id") Integer idProducto, RedirectAttributes redirectAttributes) {
        Optional<ConfiteriaModel> confiteriaOtp = confiteriaServ.getComboById(idProducto);

        if (confiteriaOtp.isPresent()) {
            deleteImage(confiteriaOtp.get().getImagen());
            confiteriaServ.borrarProductoConfi(idProducto);
            redirectAttributes.addFlashAttribute("success", "Producto borrado exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "El producto seleccionado no existe.");
        }
        return "redirect:/confiteria/admin";
    }

    // Vista formulario editar
    @GetMapping("/admin/editar/{id}")
    public String editarProducto(@PathVariable("id") int idProducto, Model model,
            RedirectAttributes redirectAttributes) {
        Optional<ConfiteriaModel> confiteriaOtp = confiteriaServ.getComboById(idProducto);

        if (!confiteriaOtp.isPresent()) {
            redirectAttributes.addFlashAttribute("warning", "El producto seleccionado no existe.");
        } else {
            ConfiteriaModel confiteria = confiteriaOtp.get();
            model.addAttribute("confiteriaOg", confiteria);
        }
        return "admin/confiteria/edit-confiteria";
    }

    // crear controlador que reciba el formulario de edición con sus respectivas
    // respuestas y alertar para su html
    @PutMapping("admin/edit-product/{id}")
    public String putMethodName(
            @PathVariable("id") Integer idProducto,
            @RequestParam("nombre") String nombreN,
            @RequestParam("descripcion") String descripcionN,
            @RequestParam("precio") double precioN,
            @RequestParam("disponible") Integer disponibleN,
            @RequestParam("categoria") String categoriaN,
            @RequestParam("imagen") MultipartFile fileN,
            RedirectAttributes redirectAttributes) {


        Optional<ConfiteriaModel> productoOg = confiteriaServ.getComboById(idProducto);

        if (!productoOg.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "El producto no existe.");
            return "redirect:/confiteria/admin";
        }

        try {
            String nombreImgNueva = productoOg.get().getImagen();
            
            // Validar si se subió una nueva imagen
            if (!fileN.isEmpty()) {
                String nombreImgOg = fileN.getOriginalFilename();
                if (nombreImgOg == null || nombreImgOg.trim().isEmpty()) {
                    // Si el nombre del archivo es inválido
                    redirectAttributes.addFlashAttribute("warning", "El archivo de imagen debe tener un nombre válido.");
                    return "redirect:/confiteria/admin/editar/" + idProducto;
                }

                // Eliminar la imagen anterior y guardar la nueva
                deleteImage(productoOg.get().getImagen()); // Asume que tienes este método implementado
                nombreImgNueva = saveImage(fileN); // Asume que tienes este método implementado
            }

            // guardar cambios
            ConfiteriaModel productoActualizado = new ConfiteriaModel();
            productoActualizado.setId(idProducto); // Es importante setear el mismo ID
            productoActualizado.setNombre(nombreN);
            productoActualizado.setDescripcion(descripcionN);
            productoActualizado.setPrecio(precioN);
            productoActualizado.setDisponible(disponibleN);
            productoActualizado.setCategoria(categoriaN);
            productoActualizado.setImagen(nombreImgNueva);

            confiteriaServ.crearProductoConfi(productoActualizado);

            // mensaje de exito
            redirectAttributes.addFlashAttribute("success", "Producto actualizado exitosamente.");
            return "redirect:/confiteria/admin";

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error al procesar la imagen: " + e.getMessage());
            return "redirect:/confiteria/admin/editar/" + idProducto;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el producto: " + e.getMessage());
            return "redirect:/confiteria/admin/editar/" + idProducto;
        }
    }

    // GUARDAR IMAGEN
    public String saveImage(MultipartFile file) throws IOException {
        String imageName = file.getOriginalFilename();

        if (imageName == null || imageName.isEmpty()) {
            throw new IOException("El archivo no tiene un nombre válido.");
        }

        Path rutaImg = Paths.get("src/main/resources/static/imgs/confiteria/" + imageName);

        Files.createDirectories(rutaImg.getParent());
        Files.copy(file.getInputStream(), rutaImg, StandardCopyOption.REPLACE_EXISTING);

        // con esto se genera un codigo aleatorio antes de guardar la imagen para
        // previnir duplicados
        // String imageName = UUID.randomUUID().toString() + "_" +
        // file.getOriginalFilename();
        // Path imagePath = Paths.get("src/main/resources/static/imgs/confiteria/" +
        // imageName);

        // Asegurarse de que el directorio exista
        // Files.createDirectories(imagePath.getParent());
        // Files.copy(file.getInputStream(), imagePath,
        // StandardCopyOption.REPLACE_EXISTING);
        return imageName;
    }

    // BORRAR IMAGEN
    public boolean deleteImage(String nombreImg) {
        if (nombreImg == null || nombreImg.isEmpty()) {
            System.out.println("El nombre de la imagen no es válido.");
            return false;
        }

        Path imgRuta = Paths.get("src/main/resources/static/imgs/confiteria/" + nombreImg);

        try {
            // Verifica si el archivo existe antes de intentar eliminarlo
            if (Files.exists(imgRuta)) {
                Files.delete(imgRuta); // Elimina el archivo
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
}
