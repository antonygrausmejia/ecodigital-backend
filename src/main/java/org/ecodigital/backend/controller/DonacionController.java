package org.ecodigital.backend.controller;

import org.ecodigital.backend.model.Donacion;
import org.ecodigital.backend.service.DonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/despachos")
public class DonacionController {

    @Autowired
    private DonacionService service;

    @GetMapping
    public List<Donacion> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Donacion guardar(@Valid @RequestBody Donacion donacion) {
        return service.guardar(donacion);
    }

    @PostMapping("/generar-guia")
    public Donacion generarGuia(@Valid @RequestBody Donacion donacion) {
        return service.generarGuia(donacion);
    }

    @PutMapping
    public Donacion actualizar(@Valid @RequestBody Donacion donacion) {
        return service.guardar(donacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
