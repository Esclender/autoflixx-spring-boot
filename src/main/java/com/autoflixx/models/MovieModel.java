package com.autoflixx.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "movie")
public class MovieModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;
	private String sinopsis;
	private String posterImg; // URL o ruta de la imagen de poster, puede ser opcional
	private String bannerImg; // URL o ruta de la imagen de banner, puede ser opcional
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaPub; // Fecha de ingreso de la película
	private String genero;
	private Boolean disponible;
	private Integer duracion; // Duración de la película en minutos
	private String director;
	private String cast; // Lista de actores principales
	private String trailerUrl;

	// @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	// @JoinColumn(name = "movie_id")
	// private List<SpotsEntradasModel> spotsEntradas = new ArrayList<>();

	// asignar imágenes por defecto si no se especifican
	@PrePersist
	public void setDefaultImages() {
		if (this.posterImg == null || this.posterImg.isEmpty()) {
			this.posterImg = "PEL-07.png";
		}
		if (this.bannerImg == null || this.bannerImg.isEmpty()) {
			this.bannerImg = "BAN-PEL-06.png";
		}
	}

	// Getters and Setters
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

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
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

	public String getTrailerUrl() {
		return trailerUrl;
	}

	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}

	// public List<SpotsEntradasModel> getSpotsEntradas() {
	// return spotsEntradas;
	// }

	// public void setSpotsEntradas(List<SpotsEntradasModel> spotsEntradas) {
	// this.spotsEntradas = spotsEntradas;
	// }

	// public void setSpotsEntradasModels() {
	// List<SpotsEntradasModel> parkingSpots = new ArrayList<>();
	// for (int i = 0; i < 48; i++) {
	// SpotsEntradasModel model = new SpotsEntradasModel(i % 4 + 1, i / 4 + 1);
	// if (Math.random() < 0.5) {
	// model.setAvailable(false);
	// }
	// parkingSpots.add(model);
	// }

	// this.spotsEntradas = parkingSpots;
	// }

	public List<SpotsEntradasModel> getSpotsEntradas() {
		List<SpotsEntradasModel> parkingSpots = new ArrayList<>();
		for (int i = 0; i < 48; i++) {
			SpotsEntradasModel model = new SpotsEntradasModel(i % 4 + 1, i / 4 + 1);
			if (Math.random() < 0.5) {
				model.setAvailable(false);
			}
			parkingSpots.add(model);
		}

		return parkingSpots;
	}
}