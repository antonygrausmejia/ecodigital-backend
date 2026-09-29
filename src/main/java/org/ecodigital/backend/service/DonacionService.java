package org.ecodigital.backend.service;

import org.ecodigital.backend.model.Donacion;
import org.ecodigital.backend.model.Equipo;
import org.ecodigital.backend.repository.DonacionRepository;
import org.ecodigital.backend.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonacionService {

    @Autowired
    private DonacionRepository repository;

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Donacion> listarTodos() {
        return repository.findAll();
    }

    public Donacion guardar(Donacion donacion) {
        return repository.save(donacion);
    }

    public Donacion generarGuia(Donacion donacion) {
        validarEquiposOperativos(donacion);
        return repository.save(donacion);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    private void validarEquiposOperativos(Donacion donacion) {
        if (donacion.getNumeroGuiaRemision() == null || donacion.getNumeroGuiaRemision().isBlank()) {
            throw new IllegalArgumentException("El numero de guia de remision es obligatorio");
        }

        if (donacion.getEquipos() == null || donacion.getEquipos().isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar al menos un equipo");
        }

        List<Equipo> equiposOperativos = new ArrayList<>();

        for (Equipo equipoSeleccionado : donacion.getEquipos()) {
            if (equipoSeleccionado.getEquipoId() == null) {
                throw new IllegalArgumentException("Todos los equipos seleccionados deben tener ID");
            }

            Equipo equipo = equipoRepository.findById(equipoSeleccionado.getEquipoId())
                    .orElseThrow(() -> new IllegalArgumentException("El equipo seleccionado no existe"));

            if (equipo.getEstadoActual() == null
                    || equipo.getEstadoActual().getNombreEstado() == null
                    || !equipo.getEstadoActual().getNombreEstado().equalsIgnoreCase("Operativo")) {
                throw new IllegalArgumentException("Solo se pueden generar guias con equipos operativos");
            }

            equiposOperativos.add(equipo);
        }

        donacion.setEquipos(equiposOperativos);
    }
}
