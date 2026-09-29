package org.ecodigital.backend.repository;

import org.ecodigital.backend.model.InstitucionBeneficiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitucionBeneficiariaRepository extends JpaRepository<InstitucionBeneficiaria, Integer> {
}