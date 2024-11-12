package com.autoflixx.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.autoflixx.models.CompraModel;

@Repository
public interface ICompraRepository extends CrudRepository<CompraModel, Long> {
}