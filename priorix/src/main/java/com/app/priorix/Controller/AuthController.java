package com.app.priorix.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {

    @GetMapping("auth/login")
    public String ShowLoginForm() {
        return "auth/login";
    }

    @GetMapping("/")
    public String Raiz() {
        return "redirect:/auth/login";
    }
    
}
