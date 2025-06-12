package com.locador.api.security.impl;

import com.locador.api.dto.basics.AuthRequest;
import com.locador.api.dto.basics.AuthResponse;
import com.locador.api.model.basics.User;
import com.locador.api.repository.basics.Role;
import com.locador.api.repository.basics.UserRepository;
import com.locador.api.service.basics.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AuthDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new AuthDetailsImpl(user);
    }

    public AuthResponse save(AuthRequest authRequest){
        if(repo.findByUsername(authRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe!");
        }

        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(authRequest.getPassword()); // Ideal: criptografar com BCrypt
        user.setRole(Role.USER);
        repo.save(user);

        AuthDetailsImpl authDetailsImpl = new AuthDetailsImpl(user);
        String token = jwtService.generateToken(authDetailsImpl);
        return new AuthResponse(token); // ← já retorna o token encapsulado
    }
}
