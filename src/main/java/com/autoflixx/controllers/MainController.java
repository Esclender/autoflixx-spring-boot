package com.autoflixx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/confiteria")
  public String getConfiteriaPage() {
    return "steps/confiteria/index";
  }
}
