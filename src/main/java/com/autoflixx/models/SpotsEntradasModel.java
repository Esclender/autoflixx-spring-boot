package com.autoflixx.models;

import jakarta.persistence.*;

@Entity
@Table(name = "spots_entradas")
public class SpotsEntradasModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private int columna;
  private int fila;
  private int price;
  private boolean isAvailable;

  public SpotsEntradasModel() {
  }

  // Constructor
  public SpotsEntradasModel(int columna, int fila, boolean isAvailable) {
    this.columna = columna;
    this.fila = fila;
    this.price = 50;
    this.isAvailable = isAvailable;
  }

  public SpotsEntradasModel(int columna, int fila) {
    this(columna, fila, true);
  }

  // Getters and Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getcolumna() {
    return columna;
  }

  public void setcolumna(int columna) {
    this.columna = columna;
  }

  public int getfila() {
    return fila;
  }

  public void setfila(int fila) {
    this.fila = fila;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
}