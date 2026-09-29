package org.ecodigital.backend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ecodigital.backend.model.Donacion;
import org.ecodigital.backend.repository.DonacionRepository;
import org.ecodigital.backend.repository.DonanteRepository;
import org.ecodigital.backend.repository.EquipoRepository;
import org.ecodigital.backend.repository.HistorialCambioEstadoRepository;
import org.ecodigital.backend.repository.InstitucionBeneficiariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private static final String ESTADO_OPERATIVO = "Operativo";
    private static final String ESTADO_EN_REVISION = "En revision";
    private static final String ESTADO_EN_ESPERA_REPUESTO = "En espera de repuesto";
    private static final String ESTADO_DESCARTADO = "Descartado";

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private DonanteRepository donanteRepository;

    @Autowired
    private InstitucionBeneficiariaRepository institucionRepository;

    @Autowired
    private DonacionRepository donacionRepository;

    @Autowired
    private HistorialCambioEstadoRepository historialRepository;

    public Map<String, Object> obtenerResumen() {
        Map<String, Object> resumen = new LinkedHashMap<>();
        resumen.put("totalEquipos", equipoRepository.count());
        resumen.put("totalDonantes", donanteRepository.count());
        resumen.put("totalInstituciones", institucionRepository.count());
        resumen.put("totalDespachos", donacionRepository.count());
        resumen.put("totalCambiosTecnicos", historialRepository.count());
        resumen.put("equiposOperativos", contarPorEstado(ESTADO_OPERATIVO));
        resumen.put("equiposEnRevision", contarPorEstado(ESTADO_EN_REVISION));
        resumen.put("equiposEnEsperaRepuesto", contarPorEstado(ESTADO_EN_ESPERA_REPUESTO));
        resumen.put("equiposDescartados", contarPorEstado(ESTADO_DESCARTADO));
        return resumen;
    }

    public List<Map<String, Object>> obtenerEquiposPorEstado() {
        return equipoRepository.contarEquiposPorEstado().stream()
                .map(row -> {
                    Map<String, Object> item = new LinkedHashMap<>();
                    item.put("estado", row[0]);
                    item.put("cantidad", row[1]);
                    return item;
                })
                .toList();
    }

    public List<Donacion> obtenerDonacionesEntregadas() {
        return donacionRepository.findAllByOrderByFechaEnvioDesc();
    }

    private long contarPorEstado(String estado) {
        return equipoRepository.countByEstadoActualNombreEstadoIgnoreCase(estado);
    }
}
