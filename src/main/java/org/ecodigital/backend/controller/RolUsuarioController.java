package org.ecodigital.backend.controller;

import org.ecodigital.backend.model.RolUsuario;
import org.ecodigital.backend.service.RolUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolUsuarioController {
    @Autowired
    private RolUsuarioService service;

    @GetMapping
    public List<RolUsuario> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public RolUsuario guardar(@Valid @RequestBody RolUsuario rol) {
        return service.guardar(rol);
    }

    @PutMapping
    public RolUsuario actualizar(@Valid @RequestBody RolUsuario rol) {
        return service.guardar(rol);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}