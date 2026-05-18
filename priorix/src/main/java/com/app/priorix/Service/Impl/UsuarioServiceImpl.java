
package com.app.priorix.Service.Impl;


import com.app.priorix.model.entity.Usuario;
import com.app.priorix.model.node.NodoPaciente;
import com.app.priorix.Repository.UsuarioRepository;
import com.app.priorix.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    

    @Override
    public List<Usuario> listar(String filtro) {
        if (filtro != null && !filtro.isEmpty()) {
            return usuarioRepository.findByNombreUsuarioContainingIgnoreCase(filtro);
        }
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(String id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    @Override
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(String id) {
        usuarioRepository.deleteById(id);
    }
    
}

