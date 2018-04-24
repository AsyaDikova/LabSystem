package org.softuni.laboratory.core.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.softuni.laboratory.patient.models.entities.Patient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilterPatient extends UsernamePasswordAuthenticationFilter {
    private static final int EXPIRATION_DURATION = 1200000;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilterPatient(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Patient patient = new ObjectMapper()
                    .readValue(request.getInputStream(), Patient.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            patient.getEmail(),
                            patient.getPassword(),
                            new ArrayList<>())
            );
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(((Patient) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DURATION))
                .signWith(SignatureAlgorithm.HS256, JwtSecurityConstants.SECRET.getBytes())
                .compact();

        response.getWriter().append("{\"Authorization\": \"Bearer " + token + "\"}");
        response.setContentType("application/json");
    }
}
