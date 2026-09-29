package org.ecodigital.backend.service;

import org.ecodigital.backend.model.Donante;
import org.ecodigital.backend.repository.DonanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DonanteService {
    @Autowired
    private DonanteRepository repository;

    public List<Donante> listar() {
        return repository.findAll();
    }

    public Donante guardar(Donante donante) {
        return repository.save(donante);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}