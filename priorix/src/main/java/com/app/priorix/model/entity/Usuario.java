package com.app.priorix.model.entity;

import com.app.priorix.model.enumeration.RolUsuario;

import jakarta.validation.constraints.NotBlank;

import java.util.Collection;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "usuarios")
public class Usuario implements UserDetails{

    @Id
    private String id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

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

    public String getUsername() { return nombreUsuario; }
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
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
    return List.of(new SimpleGrantedAuthority("ROLE_" + this.rol.name()));
}
    @Override
    public boolean isAccountNonExpired() {
        // ¿Para qué sirve?: Indica si la cuenta del usuario ha expirado.
        // ¿Por qué retornar true?: Para que la cuenta esté siempre vigente a menos que implementes lógica de expiración.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // ¿Para qué sirve?: Indica si el usuario está bloqueado (ej. por intentos fallidos).
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // ¿Para qué sirve?: Indica si la contraseña ha caducado.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // ¿Para qué sirve?: Indica si el usuario está habilitado/activo en el sistema.
        return true;
    }

}