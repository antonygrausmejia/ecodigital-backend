package org.ecodigital.backend.service;

import org.ecodigital.backend.model.RolUsuario;
import org.ecodigital.backend.repository.RolUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolUsuarioService {
    @Autowired
    private RolUsuarioRepository repository;

    public List<RolUsuario> listarTodos() {
        return repository.findAll();
    }

    public RolUsuario guardar(RolUsuario rol) {
        return repository.save(rol);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}