package org.ecodigital.backend.repository;

import java.util.List;

import org.ecodigital.backend.model.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Integer> {

    List<Donacion> findAllByOrderByFechaEnvioDesc();
}
