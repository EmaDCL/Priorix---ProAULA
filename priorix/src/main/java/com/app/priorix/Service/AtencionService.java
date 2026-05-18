
package com.app.priorix.Service;


import com.app.priorix.model.entity.Atencion;
import java.util.List;

public interface AtencionService {

    Atencion guardar(Atencion atencion);
    List<Atencion> listar();
    Atencion obtenerPorId(String id);
    Atencion obtener(String id);
}
