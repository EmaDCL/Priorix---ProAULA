package com.app.priorix.Service.Impl;


import com.app.priorix.Repository.AtencionRepository;
import com.app.priorix.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Override
    public List<Map<String, Object>> generarReporteGeneral() {
        List<Map<String, Object>> reportes = new ArrayList<>();
        atencionRepository.findAll().forEach(a -> {
            Map<String, Object> r = new HashMap<>();
            r.put("paciente", a.getPaciente().getNombre() + " " + a.getPaciente().getApellido());
            r.put("nivel", a.getTriaje().getNivel().getColor());
            r.put("estado", a.getEstado().name());
            r.put("medico", a.getMedico().getUsername());
            r.put("fecha", a.getTriaje().getFechaRegistro());
            reportes.add(r);
        });
        return reportes;
    }

    @Override
    public List<Map<String, Object>> filtrarReportes(String fechaInicio, String fechaFin, String nivel) {
        // Placeholder: se puede añadir filtrado con Query Methods más adelante.
        return generarReporteGeneral();
    }
}
