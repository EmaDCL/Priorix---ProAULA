package com.app.priorix.Service;


import java.util.List;
import java.util.Map;

public interface ReporteService {

    List<Map<String, Object>> generarReporteGeneral();
    List<Map<String, Object>> filtrarReportes(String fechaInicio, String fechaFin, String nivel);
}
