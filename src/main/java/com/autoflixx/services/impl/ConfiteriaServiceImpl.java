package com.autoflixx.services.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.autoflixx.models.ConfiteriaModel;
import com.autoflixx.services.IConfiteriaService;

@Service
public class ConfiteriaServiceImpl implements IConfiteriaService{
	
	private List<ConfiteriaModel> productos = null;
	
	public ConfiteriaServiceImpl(){
		 productos = new LinkedList<>();
		 
	try {
	    // Promoción 1
        ConfiteriaModel promo1 = new ConfiteriaModel();
        promo1.setId(1);
        promo1.setNombre("Promo de 2 palomitas + 2 bebidas + M&M's");
        promo1.setDescripcion("Palomitas grandes, dos bebidas gaseosas y un chocolate M&M's.");
        promo1.setPrecio(45.00);
        promo1.setCategoria("Promocion");
        promo1.setDisponible(1);  
        promo1.setImagen("PROM-01.png");

        // Promoción 2
        ConfiteriaModel promo2 = new ConfiteriaModel();
        promo2.setId(2);
        promo2.setNombre("Promo de palomitas + 2 bebidas + nuggets");
        promo2.setDescripcion("Palomitas grandes, dos bebidas gaseosas y una porción de nuggets.");
        promo2.setPrecio(55.00);
        promo2.setCategoria("Promocion");
        promo2.setDisponible(1);  
        promo2.setImagen("PROM-02.PNG");

        // Promoción 3
        ConfiteriaModel promo3 = new ConfiteriaModel();
        promo3.setId(7);
        promo3.setNombre("Promo de 2 palomitas + 2 bebidas");
        promo3.setDescripcion("Dos palomitas grandes y dos bebidas gaseosas.");
        promo3.setPrecio(64.00);
        promo3.setCategoria("Promocion");
        promo3.setDisponible(1);  
        promo3.setImagen("PROM-03.PNG");

        // Promoción 4
        ConfiteriaModel promo4 = new ConfiteriaModel();
        promo4.setId(5);
        promo4.setNombre("Promo de papas fritas (Lay's, Inka y Doritos)");
        promo4.setDescripcion("Paquetes de papas fritas Lay's, Doritos y Papas Ricas.");
        promo4.setPrecio(24.00);
        promo4.setCategoria("Promocion");
        promo4.setDisponible(1);  
        promo4.setImagen("PROM-04.PNG");
        
        // Promoción 5
        ConfiteriaModel promo5 = new ConfiteriaModel();
        promo5.setId(6);
        promo5.setNombre("Promo de palomitas + 1 bebida + Doritos + KitKat");
        promo5.setDescripcion("Palomitas grandes, una bebida gaseosa, un dorito grande y un KitKat.");
        promo5.setPrecio(38.50);
        promo1.setCategoria("Promocion");
        promo5.setDisponible(1);  
        promo5.setImagen("PROM-05.PNG");

        // combo 1
        ConfiteriaModel combo1 = new ConfiteriaModel();
        combo1.setId(3);
        combo1.setNombre("Promo de hamburguesa doble + papas fritas + bebida");
        combo1.setDescripcion("Doble hamburguesa con queso, una porción de papas fritas y una bebida.");
        combo1.setPrecio(38.90);
        combo1.setCategoria("Combo");
        combo1.setDisponible(1);  
        combo1.setImagen("COM-01.PNG");

         // combo 2
        ConfiteriaModel combo2 = new ConfiteriaModel();
        combo2.setId(8);
        combo2.setNombre("Palomitas + 1 bebida");
        combo2.setDescripcion("Palomitas grandes y una bebida gaseosa.");
        combo2.setPrecio(38.50);
        combo2.setCategoria("Combo");
        combo2.setDisponible(1);  
        combo2.setImagen("COM-02.PNG");

        // combo 3
        ConfiteriaModel combo3 = new ConfiteriaModel();
        combo3.setId(4);
        combo3.setNombre("Promo de pizza + 2 bebidas");
        combo3.setDescripcion("Pizza familiar con dos bebidas gaseosas.");
        combo3.setPrecio(43.90);
        combo3.setCategoria("Combo");
        combo3.setDisponible(1);  
        combo3.setImagen("COM-03.PNG");

        // Golosina 01
        ConfiteriaModel golosina1 = new ConfiteriaModel();
        golosina1.setId(9);
        golosina1.setNombre("M&M's");
        golosina1.setDescripcion("Paquete de M&M's.");
        golosina1.setPrecio(8.00);
        golosina1.setCategoria("Golosina");
        golosina1.setDisponible(1);  
        golosina1.setImagen("DUL-01.PNG");

        
        // Agregar productos a la lista
        productos.add(promo1);
        productos.add(promo2);
        productos.add(promo3);
        productos.add(promo4);
        productos.add(promo5);
        productos.add(combo1);
        productos.add(combo2);
        productos.add(combo3);
        productos.add(golosina1);
  

		
	}catch(Exception e) {
		
	}
	}

	@Override
	public List<ConfiteriaModel> getProducts() {
		return productos;
	}
}
