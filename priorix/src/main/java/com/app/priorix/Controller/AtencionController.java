package com.app.priorix.Controller;

import com.app.priorix.model.entity.Atencion;
import com.app.priorix.model.entity.Usuario;
import com.app.priorix.model.enumeration.EstadoAtencion;
import com.app.priorix.model.node.NodoPaciente;
import com.app.priorix.Service.AtencionService;
import com.app.priorix.Service.ListaEsperaService;
import com.app.priorix.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medico")
public class AtencionController {

    @Autowired
    private ListaEsperaService listaEsperaService;

    @Autowired
    private AtencionService atencionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/atender/{idTriaje}")
    public String atender(@PathVariable String idTriaje, Authentication auth, Model model) {

        NodoPaciente nodo = listaEsperaService.buscarPorIdTriaje(idTriaje);
        if (nodo == null)
            return "redirect:/medico/lista-espera?error=noencontrado";

        listaEsperaService.eliminar(idTriaje);

        Atencion at = new Atencion();
        at.setPaciente(nodo.getPaciente());
        at.setTriaje(nodo.getTriaje());

        Usuario medico = usuarioService.buscarPorNombreUsuario(auth.getName());
        at.setMedico(medico);

        // Estado inicial
        at.setEstado(EstadoAtencion.EN_PROCESO);

       Atencion atGuardada = atencionService.guardar(at);
model.addAttribute("atencion", atGuardada);

        return "medico/atencion";
    }


    @PostMapping("/atencion/guardar")
    public String guardarAtencion(@ModelAttribute Atencion atencionForm) {

        Atencion atencionBD = atencionService.obtener(atencionForm.getId());
        if (atencionBD == null) {
            return "redirect:/medico/lista-espera?error=notfound";
        }

        atencionBD.setMotivoConsulta(atencionForm.getMotivoConsulta());
        atencionBD.setDiagnostico(atencionForm.getDiagnostico());
        atencionBD.setTratamiento(atencionForm.getTratamiento());
        atencionBD.setObservaciones(atencionForm.getObservaciones());

        // 🔥 ESTADO DEFINITIVO
        atencionBD.setEstado(EstadoAtencion.ATENDIDO);

        atencionService.guardar(atencionBD);

        return "redirect:/medico/lista-espera?success=ok";
    }

}
