package org.ecodigital.backend.controller;

import org.ecodigital.backend.model.Donante;
import org.ecodigital.backend.service.DonanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/donantes")
@CrossOrigin(origins = "http://localhost:4200")
public class DonanteController {
    @Autowired
    private DonanteService service;

    @GetMapping
    public List<Donante> listar() {
        return service.listar();
    }

    @PostMapping
    public Donante guardar(@Valid @RequestBody Donante donante) {
        return service.guardar(donante);
    }

    @PutMapping
    public Donante actualizar(@Valid @RequestBody Donante donante) {
        return service.guardar(donante);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}