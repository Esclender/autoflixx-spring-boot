package com.autoflixx.repository.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.autoflixx.models.ConfiteriaModel;

@Repository
public interface IConfiteriaRepository extends CrudRepository<ConfiteriaModel, Integer> {
}