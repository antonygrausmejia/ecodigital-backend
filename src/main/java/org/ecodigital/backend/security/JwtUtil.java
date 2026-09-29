package org.ecodigital.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Llave secreta para firmar los tokens (debe ser larga para cumplir con el
    // estándar de seguridad)
    private static final String SECRET_KEY = "EcoDigitalSecretKeyParaGenerarTokensSeguros2026";

    // Tiempo de validez del token en milisegundos (Ejemplo: 10 horas)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    // Genera la clave de firma a partir de nuestro SECRET_KEY
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Método Modificado: Ahora recibe el correo Y el rol del usuario
    public String generarToken(String correo, String rol) {
        return Jwts.builder()
                .setSubject(correo)
                .claim("role", rol) // ¡AQUÍ INYECTAMOS EL ROL AL TOKEN!
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Método para leer el correo que viene dentro del token
    public String extraerCorreo(String token) {
        return extraerClaim(token, Claims::getSubject);
    }

    // Método para comprobar si el token es correcto y le pertenece al usuario
    public boolean validarToken(String token, String correoUsuario) {
        final String correoToken = extraerCorreo(token);
        return (correoToken.equals(correoUsuario) && !tokenExpirado(token));
    }

    // Revisa si el token ya venció
    private boolean tokenExpirado(String token) {
        return extraerExpiracion(token).before(new Date());
    }

    private Date extraerExpiracion(String token) {
        return extraerClaim(token, Claims::getExpiration);
    }

    private <T> T extraerClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}