package com.autoflixx.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.autoflixx.models.SpotsEntradasModel;

@Repository
public interface ISpotsEntradasRepository extends CrudRepository<SpotsEntradasModel, Integer> {
}
