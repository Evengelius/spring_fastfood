package com.main.fastfood.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.*;
import java.time.LocalDate;
import java.util.Date;

@Service
public class JwtProvider {

    private Key key;

    /**
     * The PostConstruct annotation is used on a method that needs to be executed after
     * dependency injection is done to perform any initialization
     * Every time the user signs in, it generate the same key(token) for that same user.
     * And not a new one every time each.
     *
     * The token signature(algorithm) is encoded in based64 (64 for 64 chars) : HS512
     * Jwt needs to decode it first before it applies.
     *
     */
    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }


    // ************************ Token | Generate ************************ //

    public String generateToken(Authentication authentication) {

        // Get the current logged user (userDetails)
        //User principal = (User) authentication.getPrincipal();

        // Build the token
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("Roles", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(14)))
                .signWith(key)
                .compact();
    }

    // ********************** Token | Validation *********************** //

    public boolean validToken(String jwt) {
        /*
         * A claim is a piece of information asserted about a subject
         * A parser is used for reading JWT strings.
         * Here we want to read the token key.
         * Once read, we will retrieve all the information (claims), about the subject.
         */
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        return true;
    }

    // ******************** Retrieve the Subject ********************** //

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key) // Symmetric encryption
                .parseClaimsJws(token) // Retrieve all the claims thanks to the token.
                .getBody(); // Store the information in the payload (the body of the JWT
        return claims.getSubject(); // Return the claim subject
    }
}
