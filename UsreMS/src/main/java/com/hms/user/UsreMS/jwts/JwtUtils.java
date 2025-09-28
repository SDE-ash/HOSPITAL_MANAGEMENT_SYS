package com.hms.user.UsreMS.jwts;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {


    /// this is the required secret key 
    @Value("${jwt.secret}")
    private String secret;


    //this is the time duration
    @Value("${jwt.expirationMs}")
    private Long expirationMs;

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    //genrateing token using the key
    public String generateToken(UserDetails userdetails){
        Date now  = new Date();
        Date exp = new Date(now.getTime()+expirationMs);
        
        return Jwts.builder()
        .setSubject(userdetails.getUsername())
        .claim("roles", userdetails.getAuthorities())
        .setIssuedAt(now)
        .setExpiration(exp)
        .signWith(getSignKey(),SignatureAlgorithm.HS256)
        .compact();
    }


    public String extractUsername(String token){
        return parseClaims(token).getSubject();
    }

    public Date extractExpiration(String token){
        return parseClaims(token).getExpiration();
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    //check if the jwt is valid expiration time
    public boolean isRokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    ///valifdate email against  token and  userdetails
    public boolean validaetToken(String token, UserDetails userDetails){
        final String username =  extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isRokenExpired(token));
    }

    


}
