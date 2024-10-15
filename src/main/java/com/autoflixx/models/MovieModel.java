package com.autoflixx.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieModel {

	private Integer id;
	private String nombre;
	private String sinopsis;
	private String posterImg;
	private String bannerImg; // URL o ruta de la imagen de banner, puede ser opcional
	private Date fechaPub; // Fecha de ingreso de la película
	private String genero;
	private boolean disponible;
	private Integer duracion; // Duración de la película en minutos
	private String director;
	private String cast; // Lista de actores principales
	private List<SpotsEntradasModel> spotsEntradas;
	private String trailerUrl;

	public String getTrailerUrl() {
		return trailerUrl;
	}

	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getPosterImg() {
		return posterImg;
	}

	public void setPosterImg(String posterImg) {
		this.posterImg = posterImg;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public Date getFechaPub() {
		return fechaPub;
	}

	public void setFechaPub(Date fechaPub) {
		this.fechaPub = fechaPub;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public List<SpotsEntradasModel> getSpotsEntradas() {
		return spotsEntradas;
	}

	public void setSpotsEntradasModels() {
		List<SpotsEntradasModel> parkingSpots = new ArrayList<>();
		for (int i = 0; i < 48; i++) {
			SpotsEntradasModel model = new SpotsEntradasModel(i % 4 + 1, i / 4 + 1);
			if (Math.random() < 0.5) {
				model.setAvailable(false);
			}
			parkingSpots.add(model);
		}

		this.spotsEntradas = parkingSpots;
	}

}
