package com.app.priorix.model.node;

import com.app.priorix.model.entity.Paciente;
import com.app.priorix.model.entity.Triaje;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class NodoPaciente {

    private String idTriaje;
    private Paciente paciente;
    private Triaje triaje;

    private Integer prioridad;      // puede ser null
    private Integer nivelDolor;     // puede ser null
    private LocalDate fechaRegistro;       // coincide con Triaje
    private LocalDateTime horaRegistro;    // coincide con Triaje

    private NodoPaciente siguiente;

    // Formateadores útiles para vistas (opcionales)
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public NodoPaciente(Triaje triaje) {
        if (triaje == null) throw new IllegalArgumentException("Triaje no puede ser null");

        this.triaje = triaje;
        this.paciente = triaje.getPaciente();
        this.idTriaje = triaje.getId();

        // Copiamos los valores (pueden ser null)
        this.prioridad = triaje.getPrioridad();
        this.nivelDolor = triaje.getNivelDolor();
        this.fechaRegistro = triaje.getFechaRegistro();
        this.horaRegistro = triaje.getHoraRegistro();
    }

    // ---------- Getters / Setters ----------
    public String getIdTriaje() { return idTriaje; }
    public void setIdTriaje(String idTriaje) { this.idTriaje = idTriaje; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Triaje getTriaje() { return triaje; }
    public void setTriaje(Triaje triaje) { this.triaje = triaje; }

    public Integer getPrioridad() { return prioridad; }
    public void setPrioridad(Integer prioridad) { this.prioridad = prioridad; }

    public Integer getNivelDolor() { return nivelDolor; }
    public void setNivelDolor(Integer nivelDolor) { this.nivelDolor = nivelDolor; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public LocalDateTime getHoraRegistro() { return horaRegistro; }
    public void setHoraRegistro(LocalDateTime horaRegistro) { this.horaRegistro = horaRegistro; }

    public NodoPaciente getSiguiente() { return siguiente; }
    public void setSiguiente(NodoPaciente siguiente) { this.siguiente = siguiente; }

    // ---------- Helper para vistas (si usas thymeleaf) ----------
    // Devuelven valores seguros (no-null) en formato legible
    public String getFechaRegistroStr() {
        return fechaRegistro != null ? fechaRegistro.format(DATE_FMT) : "";
    }

    public String getHoraRegistroStr() {
        return horaRegistro != null ? horaRegistro.format(TIME_FMT) : "";
    }

    // Para evitar problemas al comparar prioridad cuando sea null
    public int prioridadOrMax() {
        return prioridad != null ? prioridad : Integer.MAX_VALUE; // mayor número = menor prioridad
    }

    @Override
    public String toString() {
        return "NodoPaciente{" +
                "idTriaje=" + idTriaje +
                ", paciente=" + (paciente != null ? paciente.getNombre() : "null") +
                ", prioridad=" + prioridad +
                ", nivelDolor=" + nivelDolor +
                ", fechaRegistro=" + fechaRegistro +
                ", horaRegistro=" + horaRegistro +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodoPaciente)) return false;
        NodoPaciente that = (NodoPaciente) o;
        return Objects.equals(idTriaje, that.idTriaje);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idTriaje);
    }
}
