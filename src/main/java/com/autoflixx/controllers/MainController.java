package com.autoflixx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email, 
                               @RequestParam("password") String password ) {
       System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        return "redirect:/login";
    }
}
