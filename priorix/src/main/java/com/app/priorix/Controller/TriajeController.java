package com.app.priorix.Controller;

import com.app.priorix.model.entity.Paciente;
import com.app.priorix.model.entity.Triaje;
import com.app.priorix.model.entity.Usuario;
import com.app.priorix.model.node.NodoPaciente;
import com.app.priorix.Service.TriajeService;
import com.app.priorix.Service.PacienteService;
import com.app.priorix.Service.UsuarioService;
import com.app.priorix.Service.ListaEsperaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/enfermero")
public class TriajeController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TriajeService triajeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ListaEsperaService listaEspera;

    @GetMapping("/triaje")
    public String mostrarTriaje(@RequestParam(required = false) String q, Model model) {
        model.addAttribute("pacientes", pacienteService.listar(q));
        model.addAttribute("triaje", new Triaje());
        return "enfermero/triaje";
    }

    @PostMapping("/triaje/guardar")
public String guardar(@ModelAttribute Triaje triaje, Authentication auth) {

    // 1. Recuperar el paciente completo de la BD (ajusta el nombre del método según tu PacienteService)
    Paciente pacienteCompleto = pacienteService.obtenerPorId(triaje.getPaciente().getId());
    triaje.setPaciente(pacienteCompleto);

    // 2. Ejecutar manualmente tu método para asignar fechas, nivel y prioridad
    triaje.prePersist();

    // 3. Asignar enfermero
    Usuario enf = usuarioService.buscarPorNombreUsuario(auth.getName());
    triaje.setEnfermero(enf);

    // 4. Guardar triaje
    triajeService.guardar(triaje);

    // 5. Crear nodo y enviarlo a lista de espera
    NodoPaciente nodo = new NodoPaciente(triaje);
    listaEspera.agregar(nodo);

    return "redirect:/enfermero/triaje?success=ok";
}



    @GetMapping("/triajes-registrados")
    public String triajes(Authentication auth, Model model) {
        Usuario enf = usuarioService.buscarPorNombreUsuario(auth.getName());
        model.addAttribute("triajes", triajeService.listarPorEnfermero(enf.getId()));
        return "enfermero/triaje";
    }
}
