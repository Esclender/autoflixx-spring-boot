package com.autoflixx.services;

import java.util.List;
import java.util.Optional;

import com.autoflixx.models.ConfiteriaModel;

public interface IConfiteriaService {
	List<ConfiteriaModel> getCombos();

	Optional<ConfiteriaModel> getComboById(Integer idCombo);

	// CREATE
	public void crearProductoConfi(ConfiteriaModel confiteria);

	//DELETE
	public void borrarProductoConfi(Integer idConfiteria);

	//encontrar por id
	public boolean buscarProductoPorId(Integer idConfiteria);

	//UPDATE
	// public void editarProductoConfi(ConfiteriaModel confiteria);
}
