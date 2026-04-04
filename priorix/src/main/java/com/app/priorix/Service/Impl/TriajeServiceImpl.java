package com.app.priorix.Service.Impl;

import com.app.priorix.Repository.TriajeRepository;
import com.app.priorix.Service.TriajeService;
import com.app.priorix.model.entity.Triaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriajeServiceImpl implements TriajeService {

    @Autowired
    private TriajeRepository triajeRepository;

    @Override
    public Triaje guardar(Triaje triaje) {
        return triajeRepository.save(triaje);
    }

    @Override
    public Triaje obtenerPorId(String id) {
        return triajeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Triaje> listar() {
        return triajeRepository.findAll();
    }

    @Override
    public List<Triaje> listarPorEnfermero(String idEnfermero) {
        return triajeRepository.findByEnfermeroId(idEnfermero);
    }

    @Override
    public void eliminar(String id) {
        triajeRepository.deleteById(id);
    }
}
