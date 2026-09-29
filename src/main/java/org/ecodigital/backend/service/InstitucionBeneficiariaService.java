package org.ecodigital.backend.service;

import org.ecodigital.backend.model.InstitucionBeneficiaria;
import org.ecodigital.backend.repository.InstitucionBeneficiariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstitucionBeneficiariaService {
    @Autowired
    private InstitucionBeneficiariaRepository repository;

    public List<InstitucionBeneficiaria> listar() {
        return repository.findAll();
    }

    public InstitucionBeneficiaria guardar(InstitucionBeneficiaria inst) {
        return repository.save(inst);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}