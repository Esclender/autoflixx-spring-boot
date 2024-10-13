package com.autoflixx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PagoController {
    @GetMapping("/pago")
    public String mostrarPagos() {
        return "/steps/pagos/pago";
    }
    
}
