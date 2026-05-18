package com.app.priorix.Controller;

import com.app.priorix.Service.AuthVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActivacionController {

    @Autowired
    private AuthVerificationService authVerificationService;

    // Muestra la página web
    @GetMapping("/activar")
    public String mostrarFormulario() {
        return "activar"; // Busca el archivo activar.html
    }

    // Procesa los datos cuando le dan a "Activar Cuenta"
    @PostMapping("/activar")
    public String procesarActivacion(@RequestParam String email, @RequestParam String code, Model model) {
        try {
            authVerificationService.activarCuenta(email, code);
            // Si todo sale bien, lo manda al login con un mensaje de éxito
            return "redirect:/login?activado"; 
        } catch (RuntimeException e) {
            // Si el código es falso o expiró, recarga la página mostrando el error
            model.addAttribute("error", e.getMessage());
            return "activar";
        }
    }
}
