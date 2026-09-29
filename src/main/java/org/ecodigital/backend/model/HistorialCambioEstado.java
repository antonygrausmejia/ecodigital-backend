package org.ecodigital.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "historial_cambios_estado") // Mapeado exactamente a tu DB
public class HistorialCambioEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historial_id")
    private Integer historialId;

    @ManyToOne
    @JoinColumn(name = "equipo_id", nullable = false)
    @NotNull(message = "El equipo es obligatorio")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "estado_anterior_id")
    private EstadoEquipo estadoAnterior;

    @ManyToOne
    @JoinColumn(name = "estado_nuevo_id", nullable = false)
    @NotNull(message = "El estado nuevo es obligatorio")
    private EstadoEquipo estadoNuevo;

    @Column(name = "fecha_cambio", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio = new Date(); // Registra la fecha actual automáticamente

    @ManyToOne
    @JoinColumn(name = "usuario_id_responsable", nullable = false)
    @NotNull(message = "El usuario responsable es obligatorio")
    private Usuario usuarioResponsable;

    @Size(max = 255, message = "Las observaciones no deben superar 255 caracteres")
    private String observaciones;

    // Getters y Setters
    public Integer getHistorialId() {
        return historialId;
    }

    public void setHistorialId(Integer historialId) {
        this.historialId = historialId;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public EstadoEquipo getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(EstadoEquipo estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoEquipo getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(EstadoEquipo estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
