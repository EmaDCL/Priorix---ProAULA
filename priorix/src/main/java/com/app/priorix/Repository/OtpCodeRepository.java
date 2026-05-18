package com.app.priorix.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.priorix.model.entity.OtpCode;

@Repository
public interface OtpCodeRepository extends MongoRepository<OtpCode, String> {

    // Este es el método que usamos para validar que el código ingresado coincida con el correo
    Optional<OtpCode> findByEmailAndCode(String email, String code);

    // Te recomiendo agregar este también. Es útil por si el administrador 
    // genera un nuevo código antes de que expire el anterior, así puedes borrar los viejos.
    void deleteByEmail(String email);
}
