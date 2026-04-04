package com.app.priorix.model.entity;


import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "historias_clinicas")
public class HistoriaClinica {

    @Id
    private String id;

    // Resumen del Triaje
    private String sintomasIniciales;
    private Integer nivelDolorInicial;
    private String nivelTriaje;

    // Resumen final (copiados desde Atencion)
    private String motivoConsulta;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;

    private LocalDate fechaAtencion;
    private LocalTime horaAtencion;

  @DocumentReference(lazy = true)
private Paciente paciente;

@DocumentReference(lazy = true)
private Atencion atencion;

@DocumentReference(lazy = true)

private Usuario usuario;

   // @PrePersist
    public void prePersist() {
        if (atencion != null) {
            this.fechaAtencion = atencion.getFechaAtencion();
            this.horaAtencion = atencion.getHoraAtencion();

            this.motivoConsulta = atencion.getMotivoConsulta();
            this.diagnostico = atencion.getDiagnostico();
            this.tratamiento = atencion.getTratamiento();
            this.observaciones = atencion.getObservaciones();
        }
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSintomasIniciales() { return sintomasIniciales; }
    public void setSintomasIniciales(String sintomasIniciales) { this.sintomasIniciales = sintomasIniciales; }

    public Integer getNivelDolorInicial() { return nivelDolorInicial; }
    public void setNivelDolorInicial(Integer nivelDolorInicial) { this.nivelDolorInicial = nivelDolorInicial; }

    public String getNivelTriaje() { return nivelTriaje; }
    public void setNivelTriaje(String nivelTriaje) { this.nivelTriaje = nivelTriaje; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public LocalDate getFechaAtencion() { return fechaAtencion; }
    public void setFechaAtencion(LocalDate fechaAtencion) { this.fechaAtencion = fechaAtencion; }

    public LocalTime getHoraAtencion() { return horaAtencion; }
    public void setHoraAtencion(LocalTime horaAtencion) { this.horaAtencion = horaAtencion; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Atencion getAtencion() { return atencion; }
    public void setAtencion(Atencion atencion) { this.atencion = atencion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
