package com.app.priorix.model.node;

import org.springframework.stereotype.Component;

@Component
public class Lista {

    private NodoPaciente cabeza;

    public NodoPaciente getCabeza() { return cabeza; }

    public void setCabeza(NodoPaciente cabeza) {
        this.cabeza = cabeza;
    }

    public void limpiar() { cabeza = null; }
}
