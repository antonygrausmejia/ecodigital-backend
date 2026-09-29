package org.ecodigital.backend.service;

import org.ecodigital.backend.model.TipoEquipo;
import org.ecodigital.backend.repository.TipoEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoEquipoService {

    @Autowired
    private TipoEquipoRepository repository;

    public List<TipoEquipo> listarTodos() {
        return repository.findAll();
    }

    public TipoEquipo guardar(TipoEquipo tipoEquipo) {
        return repository.save(tipoEquipo);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}