package org.ecodigital.backend.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.ecodigital.backend.model.Usuario;
import org.ecodigital.backend.repository.UsuarioRepository;
import org.ecodigital.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String correo = normalize(credenciales.get("correo"));
        String contrasena = credenciales.get("contrasena");

        if (contrasena == null) {
            contrasena = credenciales.get("contrase\u00f1a");
        }

        Map<String, String> errors = validarCredenciales(correo, contrasena);
        if (!errors.isEmpty()) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("message", "Revise los datos enviados");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        Usuario usuario = usuarioRepository.findByCorreo(correo);

        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {

            // --- NUEVO CÓDIGO: EXTRAEMOS EL ROL DEL USUARIO ---
            String nombreRol = "Usuario";
            if (usuario.getRol() != null) {
                nombreRol = usuario.getRol().getNombreRol();
            }

            // --- CÓDIGO MODIFICADO: GENERAMOS EL TOKEN CON EL ROL ---
            String token = jwtUtil.generarToken(correo, nombreRol);

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("token", token);
            response.put("usuario", usuario);
            return ResponseEntity.ok(response);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Credenciales incorrectas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    private Map<String, String> validarCredenciales(String correo, String contrasena) {
        Map<String, String> errors = new LinkedHashMap<>();

        if (correo == null || correo.isBlank()) {
            errors.put("correo", "El correo es obligatorio");
        } else if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.put("correo", "Ingrese un correo valido");
        }

        if (contrasena == null || contrasena.isBlank()) {
            errors.put("contrasena", "La contrasena es obligatoria");
        }

        return errors;
    }

    private String normalize(String value) {
        return value == null ? null : value.trim();
    }
}