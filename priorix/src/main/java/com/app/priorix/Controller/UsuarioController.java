package com.app.priorix.Controller;

import com.app.priorix.model.entity.Usuario;
import com.app.priorix.Service.AuthService;
import com.app.priorix.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    private AuthService authService;

    public UsuarioController(UsuarioService usuarioService, AuthService authService) {
        this.usuarioService = usuarioService;
        this.authService = authService;
    }

    @ModelAttribute("roles")
    public String[] roles() {
        return new String[]{"ROLE_ADMIN", "ROLE_MEDICO", "ROLE_ENFERMERO"};
    }

    @GetMapping
    public String listarUsuarios(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Usuario> usuarios = usuarioService.listar(q);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("query", q);
        return "admin/usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/usuario-form";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setActivo(false); 
        usuarioService.guardar(usuario);
        
        // Ahora 'authService' ya no será null porque se cargó en el constructor
        authService.registrarYEnviarCodigo(usuario.getEmail());
        
        return "redirect:/activar?email=" + usuario.getEmail();
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable String id, Model model) {
        Usuario u = usuarioService.obtenerPorId(id);
        if (u == null) return "redirect:/admin/usuarios?error=notfound";
        model.addAttribute("usuario", u);
        return "admin/usuario-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id) {
        usuarioService.eliminar(id);
        return "redirect:/admin/usuarios";
    }


}
