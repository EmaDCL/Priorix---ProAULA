package com.app.priorix.Service.Impl;

import com.app.priorix.Service.ListaEsperaService;
import com.app.priorix.model.node.Lista;
import com.app.priorix.model.node.NodoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListaEsperaServiceImpl implements ListaEsperaService {

    @Autowired
    private Lista lista;

    @Override
    public void agregar(NodoPaciente nuevo) {

        if (lista.getCabeza() == null) {
            lista.setCabeza(nuevo);
            return;
        }

        NodoPaciente actual = lista.getCabeza();
        NodoPaciente anterior = null;

        while (actual != null && actual.getPrioridad() <= nuevo.getPrioridad()) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (anterior == null) {
            nuevo.setSiguiente(lista.getCabeza());
            lista.setCabeza(nuevo);
        } else {
            anterior.setSiguiente(nuevo);
            nuevo.setSiguiente(actual);
        }
    }

    @Override
    public NodoPaciente obtenerLista() {
        return lista.getCabeza();
    }

    @Override
    public NodoPaciente buscarPorIdTriaje(String id) {
        NodoPaciente actual = lista.getCabeza();
        while (actual != null) {
            if (actual.getIdTriaje().equals(id)) return actual;
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public void eliminar(String id) {
        NodoPaciente actual = lista.getCabeza();
        NodoPaciente anterior = null;

        while (actual != null) {
            if (actual.getIdTriaje().equals(id)) {
                if (anterior == null) {
                    lista.setCabeza(actual.getSiguiente());
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                return;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
    }
    @Override
    public void limpiar() {
        lista.limpiar();
    }

    
}
