package com.app.priorix.model.entity;


import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "pacientes")
public class Paciente {

    @Id
    private String id;

    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;
    private LocalDate fechaNacimiento;

   
    @DocumentReference(lazy = true)
private List<Triaje> triajes;

@DocumentReference(lazy = true)
private List<Atencion> atenciones;

@DocumentReference(lazy = true)
private HistoriaClinica historiaClinica;

    @Transient
    public Integer getEdad() {
        if (fechaNacimiento == null) return null;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }


    // Getters y Setters 

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public List<Triaje> getTriajes() { return triajes; }
    public void setTriajes(List<Triaje> triajes) { this.triajes = triajes; }

    public List<Atencion> getAtenciones() { return atenciones; }
    public void setAtenciones(List<Atencion> atenciones) { this.atenciones = atenciones; }

   public HistoriaClinica getHistoriaClinica() {
    return historiaClinica;
}

public void setHistoriaClinica(HistoriaClinica historiaClinica) {
    this.historiaClinica = historiaClinica;
}
}