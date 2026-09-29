package org.ecodigital.backend.controller;

import org.ecodigital.backend.model.Usuario;
import org.ecodigital.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> listar() {
        return service.listarTodos();
    }

    // NUEVO: Endpoint para listar solo técnicos
    @GetMapping("/tecnicos")
    public List<Usuario> listarTecnicos() {
        return service.listarTecnicos();
    }

    @PostMapping
    public Usuario guardar(@Valid @RequestBody Usuario u) {
        return service.guardar(u);
    }

    @PutMapping
    public Usuario actualizar(@Valid @RequestBody Usuario u) {
        return service.guardar(u);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
