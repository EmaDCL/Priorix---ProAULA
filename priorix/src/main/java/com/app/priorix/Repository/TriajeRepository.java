package com.app.priorix.Repository;

import com.app.priorix.model.entity.Triaje;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TriajeRepository extends MongoRepository<Triaje, String> {

    // Necesario para listar los triajes de un enfermero
    List<Triaje> findByEnfermeroId(String idEnfermero);
}
