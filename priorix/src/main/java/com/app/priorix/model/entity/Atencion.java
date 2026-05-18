package com.app.priorix.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.app.priorix.model.enumeration.EstadoAtencion;


@Document(collection = "atenciones")
public class Atencion {

    @Id
    private String id;

   @DocumentReference(lazy = true)
    private Usuario medico;

    @DocumentReference(lazy = true)
private Paciente paciente;

    @DocumentReference(lazy = true)
private Triaje triaje;



@DocumentReference(lazy = true)
private HistoriaClinica historiaClinica;

    private String motivoConsulta;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;

    private LocalDate fechaAtencion;
    private LocalTime horaAtencion;


    private EstadoAtencion estado;

    //@PrePersist
public void prePersist() {
    fechaAtencion = LocalDate.now();
    horaAtencion = LocalTime.now();

    if (estado == null) {
        estado = EstadoAtencion.ATENDIDO; // o ATENDIDO si quieres
    }
}


    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Triaje getTriaje() { return triaje; }
    public void setTriaje(Triaje triaje) { this.triaje = triaje; }

    public Usuario getMedico() { return medico; }
    public void setMedico(Usuario medico) { this.medico = medico; }

    public HistoriaClinica getHistoriaClinica() { return historiaClinica; }
    public void setHistoriaClinica(HistoriaClinica historiaClinica) { this.historiaClinica = historiaClinica; }

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

    public EstadoAtencion getEstado() { return estado; }
    public void setEstado(EstadoAtencion estado) { this.estado = estado; }
}
