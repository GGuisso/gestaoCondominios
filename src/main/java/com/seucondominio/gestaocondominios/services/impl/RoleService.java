package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.RoleDTO;
import com.seucondominio.gestaocondominios.entities.Role;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.RoleMapperManual;
import com.seucondominio.gestaocondominios.repositories.RoleRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapperManual roleMapperManual;

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        Role role = roleMapperManual.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapperManual.toDTO(role);
    }

    @Override
    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        Role role = findRoleById(id);
        role.setName(roleDTO.getName());
        role = roleRepository.save(role);
        return roleMapperManual.toDTO(role);
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        Role role = findRoleById(id);
        return roleMapperManual.toDTO(role);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
            .map(roleMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteRole(Long id) {
        Role role = findRoleById(id);
        roleRepository.delete(role);
    }

    // Método privado para centralizar a lógica de busca do Role
    private Role findRoleById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Role não encontrado com ID: " + id));
    }
}
