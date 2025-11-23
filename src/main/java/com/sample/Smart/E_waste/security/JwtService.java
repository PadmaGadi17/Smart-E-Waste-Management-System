package com.sample.Smart.E_waste.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtService {

    // TODO: replace with a long random 64+ char secret and keep it private
    private final Key key = Keys.hmacShaKeyFor(
            "CHANGE_ME_TO_A_LONG_RANDOM_SECRET_KEY_1234567890_ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes()
    );

    public String generateToken(String username, String role) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // e.g. ROLE_USER or ROLE_ADMIN
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 24 * 60 * 60 * 1000)) // 24h
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        Object r = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("role");
        return r == null ? null : r.toString();
    }
}

