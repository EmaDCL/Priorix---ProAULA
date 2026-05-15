package com.app.priorix.Repository;

import com.app.priorix.model.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    List<Usuario> findByNombreUsuarioContainingIgnoreCase(String filtro);

    Optional<Usuario> findByEmail(String email);
}
