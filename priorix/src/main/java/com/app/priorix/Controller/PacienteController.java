package com.app.priorix.Controller;

import com.app.priorix.model.entity.Paciente;
import com.app.priorix.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listar(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Paciente> pacientes = pacienteService.listar(q);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("query", q);
        return "admin/pacientes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "admin/paciente-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Paciente paciente) {
        pacienteService.guardar(paciente);
        return "redirect:/admin/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        Paciente p = pacienteService.obtenerPorId(id);
        if (p == null) return "redirect:/admin/pacientes?error=notfound";
        model.addAttribute("paciente", p);
        return "admin/paciente-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        pacienteService.eliminar(id);
        return "redirect:/admin/pacientes";
    }
}
