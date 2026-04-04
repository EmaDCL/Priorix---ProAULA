
package com.app.priorix.Service;

import com.app.priorix.model.entity.Usuario;
import com.app.priorix.model.node.NodoPaciente;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listar(String filtro);
    Usuario obtenerPorId(String id);
    Usuario buscarPorNombreUsuario(String nombreUsuario); 
    Usuario guardar(Usuario usuario);
    void eliminar(String id);
}