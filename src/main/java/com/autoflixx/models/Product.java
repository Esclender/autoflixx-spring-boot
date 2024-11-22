// package com.autoflixx.models;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "product")
// public class Product {

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Integer productId;

// private String nombre;
// private Double precio;
// private Integer amount;
// private String imagen;

// // Constructor, getters, and setters...

// public Product() {
// }

// public Product(int amount, String imagen, double precio, String nombre) {
// this.amount = amount;
// this.imagen = imagen;
// this.precio = precio;
// this.nombre = nombre;
// }

// public String getNombre() {
// return nombre;
// }

// public void setNombre(String nombre) {
// this.nombre = nombre;
// }

// public Integer getProductId() {
// return productId;
// }

// public void setProductId(Integer productId) {
// this.productId = productId;
// }

// public int getAmount() {
// return amount;
// }

// public void setAmount(int amount) {
// this.amount = amount;
// }

// public void setPrecio(double precio) {
// this.precio = precio;
// }

// public double getPrecio() {
// return precio;
// }

// public String getImagen() {
// return imagen;
// }

// public void setImagen(String productoImagen) {
// this.imagen = productoImagen;
// }
// }