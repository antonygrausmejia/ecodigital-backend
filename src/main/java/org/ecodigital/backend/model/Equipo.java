package org.ecodigital.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipo_id")
    private Integer equipoId;

    @Column(name = "numero_serie", length = 100, nullable = false, unique = true)
    @NotBlank(message = "El numero de serie es obligatorio")
    @Size(max = 100, message = "El numero de serie no debe superar 100 caracteres")
    private String numeroSerie;

    @ManyToOne
    @JoinColumn(name = "tipo_equipo_id", nullable = false)
    @NotNull(message = "El tipo de equipo es obligatorio")
    private TipoEquipo tipoEquipo;

    @Column(length = 100)
    @Size(max = 100, message = "La marca no debe superar 100 caracteres")
    private String marca;

    @Column(length = 100)
    @Size(max = 100, message = "El modelo no debe superar 100 caracteres")
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "estado_actual_id", nullable = false)
    @NotNull(message = "El estado actual es obligatorio")
    private EstadoEquipo estadoActual;

    @ManyToOne
    @JoinColumn(name = "donante_id")
    private Donante donante;

    // AQUI ESTÁ EL CAMBIO: Renombrado a usuarioResponsable para coincidir con
    // Angular
    @ManyToOne
    @JoinColumn(name = "usuario_id_tecnico_actual")
    private Usuario usuarioResponsable;

    @Column(name = "fecha_recepcion", nullable = false)
    @NotNull(message = "La fecha de recepcion es obligatoria")
    private Date fechaRecepcion;

    @Column(name = "fecha_actualizacion", insertable = false, updatable = false)
    private Date fechaActualizacion;

    @Column(name = "fecha_donacion")
    private Date fechaDonacion;

    public Equipo() {
    }

    // --- GETTERS Y SETTERS ---
    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipo tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public EstadoEquipo getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoEquipo estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Donante getDonante() {
        return donante;
    }

    public void setDonante(Donante donante) {
        this.donante = donante;
    }

    // Nuevos métodos para usuarioResponsable
    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaDonacion() {
        return fechaDonacion;
    }

    public void setFechaDonacion(Date fechaDonacion) {
        this.fechaDonacion = fechaDonacion;
    }
}
