package org.ecodigital.backend.repository;

import org.ecodigital.backend.model.HistorialCambioEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialCambioEstadoRepository extends JpaRepository<HistorialCambioEstado, Integer> {
}