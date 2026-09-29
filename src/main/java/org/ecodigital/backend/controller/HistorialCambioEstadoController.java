package org.ecodigital.backend.controller;

import org.ecodigital.backend.model.HistorialCambioEstado;
import org.ecodigital.backend.service.HistorialCambioEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/historial-cambios")
public class HistorialCambioEstadoController {

    @Autowired
    private HistorialCambioEstadoService service;

    @GetMapping
    public List<HistorialCambioEstado> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public HistorialCambioEstado guardar(@Valid @RequestBody HistorialCambioEstado historial) {
        return service.guardar(historial);
    }

    @PutMapping
    public HistorialCambioEstado actualizar(@Valid @RequestBody HistorialCambioEstado historial) {
        return service.guardar(historial);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}