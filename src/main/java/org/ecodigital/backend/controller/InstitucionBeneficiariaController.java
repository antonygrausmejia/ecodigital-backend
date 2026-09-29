package org.ecodigital.backend.controller;

import org.ecodigital.backend.model.InstitucionBeneficiaria;
import org.ecodigital.backend.service.InstitucionBeneficiariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/instituciones")
@CrossOrigin(origins = "http://localhost:4200")
public class InstitucionBeneficiariaController {
    @Autowired
    private InstitucionBeneficiariaService service;

    @GetMapping
    public List<InstitucionBeneficiaria> listar() {
        return service.listar();
    }

    @PostMapping
    public InstitucionBeneficiaria guardar(@Valid @RequestBody InstitucionBeneficiaria inst) {
        return service.guardar(inst);
    }

    @PutMapping
    public InstitucionBeneficiaria actualizar(@Valid @RequestBody InstitucionBeneficiaria inst) {
        return service.guardar(inst);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}