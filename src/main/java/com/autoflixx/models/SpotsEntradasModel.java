package com.autoflixx.models;

public class SpotsEntradasModel {
  private int column;
  private int row;
  private int price;
  private boolean isAvailable;

  public SpotsEntradasModel() {
  }

  // Constructor
  public SpotsEntradasModel(int column, int row, boolean isAvailable) {
    this.column = column;
    this.row = row;
    this.price = 50;
    this.isAvailable = isAvailable;
  }

  public SpotsEntradasModel(int column, int row) {
    this(column, row, true);
  }

  // Getters and Setters
  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
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