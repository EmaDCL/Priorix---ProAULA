package com.app.priorix.Service;

import com.app.priorix.model.entity.Triaje;
import java.util.List;

public interface TriajeService {

    Triaje guardar(Triaje triaje);  
    Triaje obtenerPorId(String id);
    List<Triaje> listar();
    List<Triaje> listarPorEnfermero(String idEnfermero);
    void eliminar(String id);
}
