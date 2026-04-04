package com.app.priorix.Repository;


import com.app.priorix.model.entity.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends MongoRepository<Paciente, String> {

    List<Paciente> findByNombreContainingIgnoreCaseOrDocumentoContainingIgnoreCase(String nombre, String documento);
}
