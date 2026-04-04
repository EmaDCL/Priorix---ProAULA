package com.app.priorix.model.entity;

import com.app.priorix.model.enumeration.RolUsuario;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotBlank(message = "El email es obligatorio")
    private String email;

    
    @NotBlank(message = "El rol es obligatorio")
    private RolUsuario rol;

    // Relaciones
    @DocumentReference(lazy = true)
    private List<Triaje> triajesRealizados;

    @DocumentReference(lazy = true)
    private List<Atencion> atencionesRealizadas;

   

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public RolUsuario getRol() { return rol; }
    public void setRol(RolUsuario rol) { this.rol = rol; }

    public List<Triaje> getTriajesRealizados() { return triajesRealizados; }
    public void setTriajesRealizados(List<Triaje> triajesRealizados) { this.triajesRealizados = triajesRealizados; }

    public List<Atencion> getAtencionesRealizadas() { return atencionesRealizadas; }
    public void setAtencionesRealizadas(List<Atencion> atencionesRealizadas) { this.atencionesRealizadas = atencionesRealizadas; }
}