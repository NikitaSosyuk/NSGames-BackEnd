package com.itis.nsgames.demo.security.token.jwt;

import com.itis.nsgames.demo.security.token.ApplicationUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public boolean validate(String token) {
        try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            val expirationDate = claims.getBody().getExpiration();
            return expirationDate.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String createToken(String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        Date now = new Date();
        val expire = now.getTime() + 60 * 60 * 24 * 30;

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(expire))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuth(String token) {
        String email = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        val user = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }
}
