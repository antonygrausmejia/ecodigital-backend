package org.ecodigital.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "donacion")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donacion_id")
    private Integer donacionId;

    @ManyToOne
    @JoinColumn(name = "institucion_id", nullable = false)
    @NotNull(message = "La institucion es obligatoria")
    private InstitucionBeneficiaria institucion;

    @ManyToOne
    @JoinColumn(name = "usuario_id_responsable", nullable = false)
    @NotNull(message = "El usuario responsable es obligatorio")
    private Usuario usuarioResponsable;

    @Column(name = "fecha_envio", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha de envio es obligatoria")
    private Date fechaEnvio;

    @Column(name = "numero_guia_remision", unique = true)
    @Size(max = 255, message = "El numero de guia no debe superar 255 caracteres")
    private String numeroGuiaRemision;

    @Column(name = "documento_referencia_guia")
    @Size(max = 255, message = "El documento de referencia no debe superar 255 caracteres")
    private String documentoReferenciaGuia;

    @ManyToMany
    @JoinTable(name = "detalle_donacion", joinColumns = @JoinColumn(name = "donacion_id"), inverseJoinColumns = @JoinColumn(name = "equipo_id"))
    @NotEmpty(message = "Debe seleccionar al menos un equipo")
    private List<Equipo> equipos;

    public Integer getDonacionId() {
        return donacionId;
    }

    public void setDonacionId(Integer donacionId) {
        this.donacionId = donacionId;
    }

    public InstitucionBeneficiaria getInstitucion() {
        return institucion;
    }

    public void setInstitucion(InstitucionBeneficiaria institucion) {
        this.institucion = institucion;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getNumeroGuiaRemision() {
        return numeroGuiaRemision;
    }

    public void setNumeroGuiaRemision(String numeroGuiaRemision) {
        this.numeroGuiaRemision = numeroGuiaRemision;
    }

    public String getDocumentoReferenciaGuia() {
        return documentoReferenciaGuia;
    }

    public void setDocumentoReferenciaGuia(String documentoReferenciaGuia) {
        this.documentoReferenciaGuia = documentoReferenciaGuia;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}
