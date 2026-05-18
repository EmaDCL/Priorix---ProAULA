package com.app.priorix.Service;

import com.app.priorix.model.node.NodoPaciente;

public interface ListaEsperaService {

    void agregar(NodoPaciente nodo);
    NodoPaciente obtenerLista();
    NodoPaciente buscarPorIdTriaje(String idTriaje);
    void eliminar(String idTriaje);
    void limpiar();
}
