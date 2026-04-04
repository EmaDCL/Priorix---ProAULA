package com.app.priorix.Controller;

import com.app.priorix.Service.ListaEsperaService;
import com.app.priorix.model.node.NodoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/medico")
public class ListaEsperaController {

    @Autowired
    private ListaEsperaService listaEsperaService;

    @GetMapping("/lista-espera")
    public String mostrarLista(Model model) {
        model.addAttribute("lista", listaEsperaService.obtenerLista());
        return "medico/lista-espera";
    }
}
