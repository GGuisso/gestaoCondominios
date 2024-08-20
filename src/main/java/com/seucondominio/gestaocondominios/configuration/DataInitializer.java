package com.seucondominio.gestaocondominios.configuration;

import com.seucondominio.gestaocondominios.entities.Role;
import com.seucondominio.gestaocondominios.entities.Usuario;
import com.seucondominio.gestaocondominios.repositories.RoleRepository;
import com.seucondominio.gestaocondominios.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.saveAll(Arrays.asList(
                new Role("ROLE_ADMIN"),
                new Role("ROLE_SINDICO"),
                new Role("ROLE_MORADOR"),
                new Role("ROLE_APLICATION") // Nova role adicionada
            ));
        }

        // Criar o usuário "teste" com a role ROLE_APLICATION
        if (usuarioRepository.findByUsername("teste").isEmpty()) {
            Usuario usuarioTeste = new Usuario();
            usuarioTeste.setUsername("teste");
            usuarioTeste.setPassword(passwordEncoder.encode("123"));
            usuarioTeste.setRoles(Arrays.asList(
                roleRepository.findByName("ROLE_APLICATION").orElseThrow(() -> new RuntimeException("Role not found"))
            ));
            usuarioRepository.save(usuarioTeste);
        }
    }
}
