package br.pucpr.projetowebservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    // USE ESTA CHAVE BASE64 VÁLIDA (já codificada)
    private static final String SECRET_KEY = "bWluZmFjaGF2ZXN1cGVyc2VjcmV0YXBhcmFqd3Rwcm9qZXRvMTIzNDU2Nzg5MA==";

    public String generateToken(UserAuthentication userAuth) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userAuth.getRole().name());
        return createToken(claims, userAuth.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Role extractRole(String token) {
        Claims claims = extractAllClaims(token);
        String roleName = claims.get("role", String.class);
        return Role.valueOf(roleName);
    }

    public boolean isTokenValid(String token, UserAuthentication userAuth) {
        String username = extractUsername(token);
        return (username.equals(userAuth.getEmail())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
