package org.ecodigital.backend.repository;

import org.ecodigital.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // Importante añadir esta línea

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Método que Spring Boot construirá automáticamente para buscar por correo
    Usuario findByCorreo(String correo);

    // NUEVO: Método para buscar usuarios filtrando por el nombre del rol
    // Nota: Esto asume que Usuario tiene una relación con Rol y Rol tiene un campo
    // 'nombreRol'
    List<Usuario> findByRolNombreRol(String nombreRol);
}