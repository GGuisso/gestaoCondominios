package com.seucondominio.gestaocondominios.mapper;

import com.seucondominio.gestaocondominios.dto.UsuarioDTO;
import com.seucondominio.gestaocondominios.entities.Role;
import com.seucondominio.gestaocondominios.entities.Usuario;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UsuarioMapperManual {

    private final RoleRepository roleRepository;

    public UsuarioMapperManual(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRoles(
            usuarioDTO.getRoleIds().stream()
                .map(id -> roleRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Role não encontrada com ID: " + id))
                )
                .collect(Collectors.toList())
        );
        return usuario;
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setRoleIds(
            usuario.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toList())
        );
        return usuarioDTO;
    }
}
