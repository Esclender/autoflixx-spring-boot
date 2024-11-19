package com.autoflixx.models;

import java.util.List;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "compra")
public class CompraModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "confiteria_id")
  private ConfiteriaModel confiteriaSelection;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private MovieModel movie;

  private int columna; // Added columna directly here
  private int fila; // Added fila directly here

  private int cantidad;

  public CompraModel(MovieModel movie) {
    this.movie = movie;
    // this.confiteriaSelection = new ArrayList<>();
  }

  public CompraModel() {
  }

  // Getters and Setters
  // public List<ConfiteriaModel> getConfiteriaSelection() {
  // return confiteriaSelection;
  // }

  // public void setConfiteriaSelection(List<ConfiteriaModel> confiteriaSelection)
  // {
  // this.confiteriaSelection = confiteriaSelection;
  // }

  public ConfiteriaModel getConfiteriaSelection() {
    return confiteriaSelection;
  }

  public MovieModel getMovie() {
    return movie;
  }

  public void setMovie(MovieModel movie) {
    this.movie = movie;
  }

  public int getColumna() {
    return columna;
  }

  public void setColumna(int columna) {
    this.columna = columna;
  }

  public int getFila() {
    return fila;
  }

  public void setFila(int fila) {
    this.fila = fila;
  }

  public int spotPrice() {
    return 50;
  }

  public double getTotal() {
    double total = 0;
    if (confiteriaSelection != null) {
      // for (ConfiteriaModel combo : confiteriaSelection)
      // total += combo.getPrecio() * cantidad;
      total += confiteriaSelection.getPrecio() * cantidad;
    }
    total += this.spotPrice(); // entrada price
    return total;
  }

  public void setAmountOfProductsToBuy(int cantidad) {
    this.cantidad = cantidad;
  }

  public int getAmountOfProductsToBuy() {
    return cantidad;
  }

  public void addCombo(ConfiteriaModel combo) {
    // confiteriaSelection.add(combo);
    confiteriaSelection = combo;
  }
}
