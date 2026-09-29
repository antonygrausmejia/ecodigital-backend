package org.ecodigital.backend.repository;

import org.ecodigital.backend.model.Donante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonanteRepository extends JpaRepository<Donante, Integer> {
}