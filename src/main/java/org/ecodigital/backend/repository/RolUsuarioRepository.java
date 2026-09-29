package org.ecodigital.backend.repository;

import org.ecodigital.backend.model.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Integer> {
    // JpaRepository ya incluye métodos como save(), findAll(), findById(),
    // deleteById()
}
