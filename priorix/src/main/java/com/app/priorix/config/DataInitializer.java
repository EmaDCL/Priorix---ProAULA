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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByNombreUsuario("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombreUsuario("admin");
            admin.setPassword(passwordEncoder.encode("Admin123"));
            admin.setRol(RolUsuario.ROLE_ADMIN);
            admin.setEmail("admin@priorix.com");
            usuarioRepository.save(admin);

            System.out.println("Usuario administrador creado: admin / Admin123 (admin@priorix.com)");
        } else {
            System.out.println(" Usuario administrador ya existe, no se recrea.");
        }
    }
}
