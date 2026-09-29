package org.ecodigital.backend.repository;

import java.util.List;

import org.ecodigital.backend.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    long countByEstadoActualNombreEstadoIgnoreCase(String nombreEstado);

    List<Equipo> findByEstadoActualNombreEstadoIgnoreCase(String nombreEstado);

    @Query("select e.estadoActual.nombreEstado, count(e) from Equipo e group by e.estadoActual.nombreEstado")
    List<Object[]> contarEquiposPorEstado();
}
