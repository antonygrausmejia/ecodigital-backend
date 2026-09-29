package org.ecodigital.backend.service;

import org.ecodigital.backend.model.Equipo;
import org.ecodigital.backend.model.HistorialCambioEstado;
import org.ecodigital.backend.repository.EquipoRepository;
import org.ecodigital.backend.repository.HistorialCambioEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistorialCambioEstadoService {

    @Autowired
    private HistorialCambioEstadoRepository repository;

    @Autowired
    private EquipoRepository equipoRepository;

    public List<HistorialCambioEstado> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public HistorialCambioEstado guardar(HistorialCambioEstado historial) {
        // 1. Guardamos el registro del historial primero
        HistorialCambioEstado guardado = repository.save(historial);

        // 2. Si el historial tiene equipo y un nuevo estado, actualizamos el equipo
        if (historial.getEquipo() != null && historial.getEstadoNuevo() != null) {
            Equipo equipo = equipoRepository.findById(historial.getEquipo().getEquipoId())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

            // Actualizamos el estado actual del equipo
            equipo.setEstadoActual(historial.getEstadoNuevo());

            // Guardamos el equipo para persistir el cambio
            equipoRepository.save(equipo);
        }

        return guardado;
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}