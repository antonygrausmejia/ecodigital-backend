package org.ecodigital.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipos_equipo") // CORREGIDO: Ahora coincide con el plural de tu BD
public class TipoEquipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_id") // CORREGIDO: Coincide con tu BD
    private Integer tipoId;

    @Column(name = "nombre_tipo", nullable = false, unique = true) // CORREGIDO: Coincide con tu BD
    @NotBlank(message = "El nombre del tipo de equipo es obligatorio")
    @Size(max = 255, message = "El nombre del tipo de equipo no debe superar 255 caracteres")
    private String nombreTipo;

    // Getters y Setters
    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
}
