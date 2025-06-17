package com.locador.api.controller;

import com.locador.api.dto.basics.AuthRequest;
import com.locador.api.dto.basics.AuthResponse;
import com.locador.api.security.impl.AuthDetailsServiceImpl;
import com.locador.api.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtService jwtService;
    @Autowired
    private AuthDetailsServiceImpl authDetailsServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest) {
        try {
            AuthResponse response = authDetailsServiceImpl.save(authRequest); // ← token já vem pronto
            return ResponseEntity.ok(response);

        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
