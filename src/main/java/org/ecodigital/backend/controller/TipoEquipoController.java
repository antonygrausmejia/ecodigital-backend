package org.ecodigital.backend.controller;

import org.ecodigital.backend.model.TipoEquipo;
import org.ecodigital.backend.service.TipoEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-equipo")
public class TipoEquipoController {

    @Autowired
    private TipoEquipoService service;

    @GetMapping
    public List<TipoEquipo> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public TipoEquipo guardar(@Valid @RequestBody TipoEquipo tipoEquipo) {
        return service.guardar(tipoEquipo);
    }

    @PutMapping
    public TipoEquipo actualizar(@Valid @RequestBody TipoEquipo tipoEquipo) {
        return service.guardar(tipoEquipo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
