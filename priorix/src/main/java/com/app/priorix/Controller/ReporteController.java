package com.app.priorix.Controller;

import com.app.priorix.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public String verReportes(Model model) {
        model.addAttribute("reportes", reporteService.generarReporteGeneral());
        return "admin/reportes";
    }

    @GetMapping("/filtrar")
    public String filtrarReportes(@RequestParam String fechaInicio,
                                  @RequestParam String fechaFin,
                                  @RequestParam(required = false) String nivel,
                                  Model model) {
        model.addAttribute("reportes", reporteService.filtrarReportes(fechaInicio, fechaFin, nivel));
        return "admin/reportes";
    }
}
