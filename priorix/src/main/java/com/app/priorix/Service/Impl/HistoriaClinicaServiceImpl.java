package com.app.priorix.Service.Impl;


import com.app.priorix.model.entity.HistoriaClinica;
import com.app.priorix.Repository.HistoriaClinicaRepository;
import com.app.priorix.Service.HistoriaClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public HistoriaClinica guardar(HistoriaClinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public List<HistoriaClinica> obtenerPorPaciente(String idPaciente) {
        return historiaClinicaRepository.findByPacienteId(idPaciente);
    }
    @Override
    public List<HistoriaClinica> findAll() {
        return historiaClinicaRepository.findAll();
    }
}
