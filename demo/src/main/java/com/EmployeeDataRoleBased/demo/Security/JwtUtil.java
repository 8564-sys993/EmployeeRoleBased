package com.EmployeeDataRoleBased.demo.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private String SECRET = "MySuperSecretKeyForJWTThatIsLongEnough123!";
    private Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    private long EXPIRY = 1000 * 60 * 60; 
    public String generateToken(Long employeeId, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(employeeId))
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY))
                .signWith(key)
                .compact();
    }

    public Long getEmployeeId(String token) {
        String subject = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return Long.valueOf(subject);
    }

    public String getRole(String token) {
        return (String) Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}