package com.locador.api.config;

import com.locador.api.model.basics.User;
import com.locador.api.enumeracoes.Role;
import com.locador.api.repository.basics.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Verifica se já existe algum usuário no banco
            if (userRepository.count() == 0) {
                // Cria um usuário admin padrão
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin"));
                adminUser.setRole(Role.ADMIN);
                
                userRepository.save(adminUser);
                
                System.out.println("Usuário admin criado com sucesso!");
            }
        };
    }
}