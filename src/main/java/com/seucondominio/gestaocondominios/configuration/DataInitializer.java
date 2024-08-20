package com.seucondominio.gestaocondominios.configuration;

import com.seucondominio.gestaocondominios.entities.Role;
import com.seucondominio.gestaocondominios.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.saveAll(Arrays.asList(
                new Role("ROLE_ADMIN"),
                new Role("ROLE_SINDICO"),
                new Role("ROLE_MORADOR")
            ));
        }
    }
}
