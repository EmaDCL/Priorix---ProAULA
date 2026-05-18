package com.app.priorix.config;

import com.app.priorix.model.entity.Usuario;
import com.app.priorix.model.enumeration.RolUsuario;
import com.app.priorix.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Crea el usuario administrador inicial si no existe.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    

    @Override
public void run(String... args) {
    if (usuarioRepository.findByNombreUsuario("admin").isEmpty()) {
        Usuario admin = new Usuario();
        admin.setNombreUsuario("admin");
        // ELIMINADA la línea de admin.setPassword(...)
        admin.setRol(RolUsuario.ROLE_ADMIN);
        
        // IMPORTANTE: Pon tu correo real aquí para poder entrar como Admin
        admin.setEmail("adminpriorix@gmail.com"); 
        
        // Lo activamos por defecto para que no necesite código de invitación
        admin.setActivo(true); 
        
        usuarioRepository.save(admin);

        System.out.println("Usuario administrador creado: admin / (adminpriorix@gmail.com)");
    } else {
        System.out.println(" Usuario administrador ya existe, no se recrea.");
    }
}
}
