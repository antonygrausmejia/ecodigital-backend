package org.ecodigital.backend.service;

import org.ecodigital.backend.model.Equipo;
import org.ecodigital.backend.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {

    private static final String ESTADO_OPERATIVO = "Operativo";

    @Autowired
    private EquipoRepository repository;

    public List<Equipo> listarTodos() {
        return repository.findAll();
    }

    public List<Equipo> listarOperativos() {
        return repository.findByEstadoActualNombreEstadoIgnoreCase(ESTADO_OPERATIVO);
    }

    public Equipo guardar(Equipo equipo) {
        return repository.save(equipo);
    }

    // Buscar por ID
    public Equipo buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // Actualizar (usamos el mismo save de JPA, si el objeto tiene ID, actualiza)
    public Equipo actualizar(Equipo equipo) {
        return repository.save(equipo);
    }

    // Eliminar
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
