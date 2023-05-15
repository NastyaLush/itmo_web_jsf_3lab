package com.example.demo.main.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;

public class Jwt {
    private final byte[] key = "i hope it will enough i'm so tired".getBytes();

    public String createToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
    }
    public String decodeToken(String jwt){
        String subject;
        if(jwt==null) return null;
        try {
            subject = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody().getSubject();
            System.out.println(subject);
            return subject;
        }
        catch (SignatureException | MalformedJwtException e) {
            return null;
        }
    }
}
