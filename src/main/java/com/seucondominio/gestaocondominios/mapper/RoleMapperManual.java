package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.RoleDTO;
import com.seucondominio.gestaocondominios.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperManual {

    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }

    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
