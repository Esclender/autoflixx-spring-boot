package com.autoflixx.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoflixx.models.ConfiteriaModel;
import com.autoflixx.repository.interfaces.IConfiteriaRepository;
import com.autoflixx.services.IConfiteriaService;

@Service
public class ConfiteriaServiceImpl implements IConfiteriaService {

    @Autowired
    private IConfiteriaRepository confiteriaRepository;

    // Obtener todo los productos
    @Override
    public List<ConfiteriaModel> getCombos() {
        return confiteriaRepository.findAll();
    }

    // Obtener un producto por id
    @Override
    public Optional<ConfiteriaModel> getComboById(Integer idCombo) {
        Optional<ConfiteriaModel> combo = confiteriaRepository.findById(idCombo);
        return combo;
    }

    // Guardar producto
    @Override
    public void crearProductoConfi(ConfiteriaModel confiteria){
        confiteriaRepository.save(confiteria);
    }

    //borrar producto
    @Override
    public void borrarProductoConfi(Integer idConfiteria){
        confiteriaRepository.deleteById(idConfiteria);
    }

    @Override
    public boolean buscarProductoPorId(Integer idConfiteria){
        return confiteriaRepository.existsById(idConfiteria);
    }

    // @Override
    // public void editarProductoConfi(ConfiteriaModel confiteria) {
    // confiteriaRepository.save(confiteria);
    // }
}
