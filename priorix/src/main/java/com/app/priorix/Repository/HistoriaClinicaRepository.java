package com.app.priorix.Repository;

import com.app.priorix.model.entity.HistoriaClinica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoriaClinicaRepository extends MongoRepository<HistoriaClinica, String> {

    // Listar todas las historias de un paciente
    List<HistoriaClinica> findByPacienteId(String idPaciente);

    // Filtrar historias por paciente y fecha de atención
    List<HistoriaClinica> findByPacienteIdAndFechaAtencion(String idPaciente, LocalDate fechaAtencion);

    // Filtrar por rango de fechas de atención
    List<HistoriaClinica> findByFechaAtencionBetween(LocalDate inicio, LocalDate fin);
}
