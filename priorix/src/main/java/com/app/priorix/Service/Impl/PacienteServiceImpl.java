package com.app.priorix.Service.Impl;

import com.app.priorix.model.entity.Paciente;
import com.app.priorix.Repository.PacienteRepository;
import com.app.priorix.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> listar(String filtro) {
        if (filtro != null && !filtro.isEmpty()) {
            return pacienteRepository.findByNombreContainingIgnoreCaseOrDocumentoContainingIgnoreCase(filtro, filtro);
        }
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente obtenerPorId(String id) {
        return pacienteRepository.findById(id).orElse(null);
    }

   @Override
public Paciente guardar(Paciente paciente) {
    // no setear fecha/hora de ingreso en paciente (esa info va en Atencion)
    return pacienteRepository.save(paciente);
}


    @Override
    public void eliminar(String id) {
        pacienteRepository.deleteById(id);
    }
}
