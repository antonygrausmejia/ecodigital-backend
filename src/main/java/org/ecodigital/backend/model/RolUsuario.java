package org.ecodigital.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "roles_usuario")
public class RolUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rolId;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 255, message = "El nombre del rol no debe superar 255 caracteres")
    private String nombreRol;

    // Constructores vacíos exigidos por JPA
    public RolUsuario() {
    }

    // Getters y Setters (Para leer y modificar los datos)
    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
