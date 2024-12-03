package com.autoflixx.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autoflixx.models.CompraModel;

@Repository
public interface ICompraRepository extends JpaRepository<CompraModel, Long> {
}
