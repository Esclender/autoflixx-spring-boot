package com.autoflixx.models;

import java.util.List;
import java.util.ArrayList;

public class CompraModel {
  private List<Product> confiteriaSelection;
  private SpotsEntradasModel parkingSpot;
  private MovieModel movie;

  public CompraModel() {
  }

  public CompraModel(MovieModel movie) {
    this.movie = movie;
    this.confiteriaSelection = new ArrayList<Product>();
  }

  // Getters and Setters
  public List<Product> getConfiteriaSelection() {
    return confiteriaSelection;
  }

  public void setConfiteriaSelection(List<Product> confiteriaSelection) {
    this.confiteriaSelection = confiteriaSelection;
  }

  public MovieModel getMovie() {
    return movie;
  }

  public void setMovie(MovieModel movie) {
    this.movie = movie;
  }

  public SpotsEntradasModel getParkingSpot() {
    return parkingSpot;
  }

  public void setParkingSpot(SpotsEntradasModel parkingSpot) {
    this.parkingSpot = parkingSpot;
  }

  public double getTotal() {
    double total = 0;
    if (confiteriaSelection != null) {
      for (Product confiteria : confiteriaSelection)
        total += confiteria.getPrecio() * confiteria.getAmount();
    }
    total += parkingSpot.getPrice();
    return total;
  }

  public void addProduct(Product product) {
    confiteriaSelection.add(product);
  }
}
