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

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "compra_id")
  private List<Product> confiteriaSelection = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "parking_spot_id")
  private SpotsEntradasModel parkingSpot;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private MovieModel movie;

  public CompraModel(MovieModel movie) {
    this.movie = movie;
    this.confiteriaSelection = new ArrayList<>();
  }

  public CompraModel() {
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
