package com.app.priorix.Service;


import com.app.priorix.model.entity.HistoriaClinica;
import java.util.List;

public interface HistoriaClinicaService {

    HistoriaClinica guardar(HistoriaClinica historiaClinica);
    List<HistoriaClinica> obtenerPorPaciente(String idPaciente);
    List<HistoriaClinica> findAll();

}
