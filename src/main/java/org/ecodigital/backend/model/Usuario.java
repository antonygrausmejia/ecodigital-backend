package org.ecodigital.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @Column(length = 255, nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no debe superar 255 caracteres")
    private String nombre;

    @Column(length = 255, nullable = false, unique = true)
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Ingrese un correo valido")
    @Size(max = 255, message = "El correo no debe superar 255 caracteres")
    private String correo;

    @JsonProperty("contrasena")
    @Column(name = "contrase\u00f1a", nullable = false)
    @NotBlank(message = "La contrasena es obligatoria")
    @Size(min = 6, max = 255, message = "La contrasena debe tener entre 6 y 255 caracteres")
    private String contrasena;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;

    public Usuario() {
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }
}
