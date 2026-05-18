package com.app.priorix.Service.Impl;


import com.app.priorix.model.entity.Atencion;
import com.app.priorix.model.entity.HistoriaClinica;
import com.app.priorix.Repository.AtencionRepository;
import com.app.priorix.Service.AtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtencionServiceImpl implements AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Override
    public Atencion guardar(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    @Override
    public List<Atencion> listar() {
        return atencionRepository.findAll();
    }

    @Override
    public Atencion obtenerPorId(String id) {
        return atencionRepository.findById(id).orElse(null);
    }
   @Override
public Atencion obtener(String id) {
    return atencionRepository.findById(id).orElse(null);
}

}
