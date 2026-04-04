
package com.app.priorix.Service;


import com.app.priorix.model.entity.Paciente;
import java.util.List;

public interface PacienteService {

    List<Paciente> listar(String filtro);
    Paciente obtenerPorId(String id);
    Paciente guardar(Paciente paciente);
    void eliminar(String id);
}
