package com.mini.project.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    // Kunci rahasia Kerajaan (Minimal 32 karakter agar aman)
    private final String SECRET_KEY = "IniAdalahKunciRahasiaSangatKuatKerajaanAsura2026";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Nama user di dalam token
                .setIssuedAt(new Date()) // Waktu dibuat
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Expired dalam 24 jam
                .signWith(key, SignatureAlgorithm.HS256) // Tanda tangan kerajaan
                .compact();
    }
}