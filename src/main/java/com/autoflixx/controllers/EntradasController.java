package com.autoflixx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/entradas")
public class EntradasController {

  @GetMapping("")
  public String getEntradasPreview() {
    return "steps/entradas/index";
  }

}
