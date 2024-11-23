package com.autoflixx.services;

import java.util.List;
import java.util.Optional;

import com.autoflixx.models.ConfiteriaModel;

public interface IConfiteriaService {
	List<ConfiteriaModel> getCombos();

	Optional<ConfiteriaModel> getComboById(Integer idCombo);

	// CREATE
	
}
