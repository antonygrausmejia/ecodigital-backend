package org.ecodigital.backend.repository;

import org.ecodigital.backend.model.TipoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEquipoRepository extends JpaRepository<TipoEquipo, Integer> {
}