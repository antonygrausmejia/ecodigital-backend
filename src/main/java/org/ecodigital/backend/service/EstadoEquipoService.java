package org.ecodigital.backend.service;

import org.ecodigital.backend.model.EstadoEquipo;
import org.ecodigital.backend.repository.EstadoEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstadoEquipoService {
    @Autowired
    private EstadoEquipoRepository repository;

    public List<EstadoEquipo> listarTodos() {
        return repository.findAll();
    }

    public EstadoEquipo guardar(EstadoEquipo estado) {
        return repository.save(estado);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}