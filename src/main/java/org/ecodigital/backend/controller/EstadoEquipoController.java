package org.ecodigital.backend.controller;

import org.ecodigital.backend.model.EstadoEquipo;
import org.ecodigital.backend.service.EstadoEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/estados-equipo")
public class EstadoEquipoController {
    @Autowired
    private EstadoEquipoService service;

    @GetMapping
    public List<EstadoEquipo> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public EstadoEquipo guardar(@Valid @RequestBody EstadoEquipo estado) {
        return service.guardar(estado);
    }

    @PutMapping
    public EstadoEquipo actualizar(@Valid @RequestBody EstadoEquipo estado) {
        return service.guardar(estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}