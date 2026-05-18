package com.app.priorix.Controller;

import com.app.priorix.model.entity.Atencion;
import com.app.priorix.model.entity.HistoriaClinica;
import com.app.priorix.Service.AtencionService;
import com.app.priorix.Service.HistoriaClinicaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medico/historia")
public class HistoriaClinicaController {

    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    @Autowired
    private AtencionService atencionService;

    @GetMapping("/crear/{idAtencion}")
    public String crear(@PathVariable String idAtencion, Model model) {
        Atencion at = atencionService.obtener(idAtencion);
        if (at == null) return "redirect:/medico/lista-espera";

        HistoriaClinica h = new HistoriaClinica();
        h.setPaciente(at.getPaciente());
        h.setAtencion(at);

        model.addAttribute("historia", h);
        return "medico/historia";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute HistoriaClinica historia) {
        historiaClinicaService.guardar(historia);
        return "redirect:/medico/historias?save=ok";
    }
}
