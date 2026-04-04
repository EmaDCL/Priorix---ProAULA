package com.app.priorix.model.entity;

import com.app.priorix.model.enumeration.NivelTriaje;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "triajes")
public class Triaje {

    @Id
    private String id;

    @DocumentReference(lazy = true)
    private Paciente paciente;

    @DocumentReference(lazy = true)
    private Usuario enfermero;

    @DocumentReference(lazy = true)
    private Atencion atencion;

    @DocumentReference(lazy = true)
    private HistoriaClinica historiaClinica;

    
    @NotBlank(message = "Los síntomas son obligatorios")
    private NivelTriaje nivel;

    private String sintomas;

    @Field("nivel_dolor")
    private Integer nivelDolor;

    private LocalDate fechaRegistro;
    private LocalDateTime horaRegistro;

    @Field("prioridad")
    private Integer prioridad;

    // ==============================
    //      MÉTODOS DE LÓGICA
    // ==============================

    // Asigna nivel según el dolor reportado
    public void clasificarPorDolor() {
        if (nivelDolor == null) return;

        if (nivelDolor >= 8) nivel = NivelTriaje.NIVEL_1_ROJO;
        else if (nivelDolor >= 6) nivel = NivelTriaje.NIVEL_2_NARANJA;
        else if (nivelDolor >= 4) nivel = NivelTriaje.NIVEL_3_AMARILLO;
        else if (nivelDolor >= 2) nivel = NivelTriaje.NIVEL_4_AZUL;
        else nivel = NivelTriaje.NIVEL_5_VERDE;
    }

    // Convierte nivel de triaje en prioridad numérica para la cola
    public void calcularPrioridad() {
        if (nivel == null) return;

        switch (nivel) {
            case NIVEL_1_ROJO -> prioridad = 1;
            case NIVEL_2_NARANJA -> prioridad = 2;
            case NIVEL_3_AMARILLO -> prioridad = 3;
            case NIVEL_4_AZUL -> prioridad = 4;
            case NIVEL_5_VERDE -> prioridad = 5;
        }
    }

    //@PrePersist
    public void prePersist() {
        if (this.fechaRegistro == null) this.fechaRegistro = LocalDate.now();
        if (this.horaRegistro == null) this.horaRegistro = LocalDateTime.now();

        // Primero clasifica por dolor
        clasificarPorDolor();

        // Luego genera prioridad
        calcularPrioridad();
    }

    // ==============================
    //      GETTERS & SETTERS
    // ==============================

    public String getId() { return id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Usuario getEnfermero() { return enfermero; }
    public void setEnfermero(Usuario enfermero) { this.enfermero = enfermero; }

    public Atencion getAtencion() { return atencion; }
    public void setAtencion(Atencion atencion) { this.atencion = atencion; }

    public HistoriaClinica getHistoriaClinica() { return historiaClinica; }
    public void setHistoriaClinica(HistoriaClinica historiaClinica) { this.historiaClinica = historiaClinica; }

    public NivelTriaje getNivel() { return nivel; }
    public void setNivel(NivelTriaje nivel) { this.nivel = nivel; }

    public String getSintomas() { return sintomas; }
    public void setSintomas(String sintomas) { this.sintomas = sintomas; }

    public Integer getNivelDolor() { return nivelDolor; }
    public void setNivelDolor(Integer nivelDolor) { this.nivelDolor = nivelDolor; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public LocalDateTime getHoraRegistro() { return horaRegistro; }
    public void setHoraRegistro(LocalDateTime horaRegistro) { this.horaRegistro = horaRegistro; }

    public Integer getPrioridad() { return prioridad; }
    public void setPrioridad(Integer prioridad) { this.prioridad = prioridad; }
}
