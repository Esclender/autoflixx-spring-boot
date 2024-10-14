package com.autoflixx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("/")
  public String redirectHome() {
    return "redirect:/movie/";
  }

  @GetMapping("/login")
  public String redirectLogin() {
    return "login";
  }
}
