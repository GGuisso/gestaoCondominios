package com.seucondominio.gestaocondominios.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Atualizado para o padrão da nova versão
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Use authorizeHttpRequests e as novas APIs
            );
        return http.build();
    }
}
