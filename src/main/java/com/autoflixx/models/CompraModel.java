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
  private List<ConfiteriaModel> confiteriaSelection = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "parking_spot_id")
  private SpotsEntradasModel parkingSpot;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private MovieModel movie;

  public int amountToBuy = 0;

  public CompraModel(MovieModel movie) {
    this.movie = movie;
    this.confiteriaSelection = new ArrayList<>();
  }

  public CompraModel() {
  }

  // Getters and Setters
  public List<ConfiteriaModel> getConfiteriaSelection() {
    return confiteriaSelection;
  }

  public void setConfiteriaSelection(List<ConfiteriaModel> confiteriaSelection) {
    this.confiteriaSelection = confiteriaSelection;
  }

  public MovieModel getMovie() {
    return movie;
  }

  public void setMovie(MovieModel movie) {
    this.movie = movie;
  }

  public SpotsEntradasModel getparkingSpot() {
    return parkingSpot;
  }

  public void setparkingSpot(SpotsEntradasModel parkingSpot) {
    this.parkingSpot = parkingSpot;
  }

  public double getTotal() {
    double total = 0;
    if (confiteriaSelection != null) {
      for (ConfiteriaModel combo : confiteriaSelection)
        total += combo.getPrecio() * amountToBuy;
    }
    total += parkingSpot.getPrice();
    return total;
  }

  public void addCombo(ConfiteriaModel combo) {
    confiteriaSelection.add(combo);
  }
}
