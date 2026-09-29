package org.ecodigital.backend.controller;

import java.util.List;
import java.util.Map;

import org.ecodigital.backend.model.Donacion;
import org.ecodigital.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping("/resumen")
    public Map<String, Object> obtenerResumen() {
        return service.obtenerResumen();
    }

    @GetMapping("/equipos-por-estado")
    public List<Map<String, Object>> obtenerEquiposPorEstado() {
        return service.obtenerEquiposPorEstado();
    }

    @GetMapping("/donaciones-entregadas")
    public List<Donacion> obtenerDonacionesEntregadas() {
        return service.obtenerDonacionesEntregadas();
    }
}
