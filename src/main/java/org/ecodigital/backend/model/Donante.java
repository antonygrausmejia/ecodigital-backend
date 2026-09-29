package org.ecodigital.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "donantes")
public class Donante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <--- Esta es la clave
    @Column(name = "donante_id") // Asegúrate de que coincida con tu columna en MySQL
    private Integer donanteId;

    @Column(name = "razon_social", length = 255, nullable = false)
    @NotBlank(message = "La razon social es obligatoria")
    @Size(max = 255, message = "La razon social no debe superar 255 caracteres")
    private String razonSocial;

    @Column(name = "RUC_DNI", length = 20, unique = true)
    @Pattern(regexp = "^(\\d{8}|\\d{11})?$", message = "El RUC/DNI debe tener 8 u 11 digitos")
    private String rucDni;

    @Column(name = "contacto_nombre", length = 255)
    @Size(max = 255, message = "El nombre de contacto no debe superar 255 caracteres")
    private String contactoNombre;

    @Column(length = 255)
    @Size(max = 255, message = "La direccion no debe superar 255 caracteres")
    private String direccion;

    @Column(length = 20)
    @Pattern(regexp = "^[0-9+()\\s-]{6,20}$|^$", message = "Ingrese un telefono valido")
    private String telefono;

    @Column(length = 255)
    @Email(message = "Ingrese un correo valido")
    @Size(max = 255, message = "El correo no debe superar 255 caracteres")
    private String correo;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private Date fechaRegistro;

    public Donante() {
    }

    // --- GETTERS Y SETTERS ---
    public Integer getDonanteId() {
        return donanteId;
    }

    public void setDonanteId(Integer donanteId) {
        this.donanteId = donanteId;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRucDni() {
        return rucDni;
    }

    public void setRucDni(String rucDni) {
        this.rucDni = rucDni;
    }

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
