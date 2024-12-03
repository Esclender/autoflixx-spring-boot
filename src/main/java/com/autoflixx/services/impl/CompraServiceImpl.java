package com.autoflixx.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoflixx.models.CompraModel;
import com.autoflixx.repository.interfaces.ICompraRepository;
import com.autoflixx.services.ICompraService;

@Service
public class CompraServiceImpl implements ICompraService {

  @Autowired
  private ICompraRepository compraRepository;

  public CompraModel saveCompra(CompraModel compra) {
    return compraRepository.save(compra);
  }

}
