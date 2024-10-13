package com.autoflixx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/factura")
public class FacturaController {

  @GetMapping("")
  public String getFacturaView() {
    return "steps/factura/index";
  }

}
