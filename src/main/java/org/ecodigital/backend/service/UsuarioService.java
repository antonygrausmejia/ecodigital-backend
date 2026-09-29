package org.ecodigital.backend.service;

import org.ecodigital.backend.model.Usuario;
import org.ecodigital.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario guardar(Usuario usuario) {
        if (usuario.getContrasena() != null && !usuario.getContrasena().startsWith("$2a$")) {
            String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
            usuario.setContrasena(contrasenaEncriptada);
        }
        return repository.save(usuario);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<Usuario> listarTecnicos() {
        return repository.findByRolNombreRol("T\u00e9cnico");
    }
}
