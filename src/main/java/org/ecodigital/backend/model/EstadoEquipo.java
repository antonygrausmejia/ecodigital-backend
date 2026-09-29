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
@Table(name = "estados_equipo")
public class EstadoEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer estadoId;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre del estado es obligatorio")
    @Size(max = 255, message = "El nombre del estado no debe superar 255 caracteres")
    private String nombreEstado;

    public EstadoEquipo() {
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
