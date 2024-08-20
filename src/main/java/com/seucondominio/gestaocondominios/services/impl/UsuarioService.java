package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.UsuarioDTO;
import com.seucondominio.gestaocondominios.entities.Role;
import com.seucondominio.gestaocondominios.entities.Usuario;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.UsuarioMapperManual;
import com.seucondominio.gestaocondominios.repositories.RoleRepository;
import com.seucondominio.gestaocondominios.repositories.UsuarioRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapperManual usuarioMapperManual;

    @Override
    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapperManual.toEntity(usuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario = usuarioRepository.save(usuario);
        return usuarioMapperManual.toDTO(usuario);
    }

    @Override
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = findUsuarioById(id);
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setRoles(findRolesByIds(usuarioDTO.getRoleIds()));
        usuario = usuarioRepository.save(usuario);
        return usuarioMapperManual.toDTO(usuario);
    }

    @Override
    public UsuarioDTO getUsuarioById(Long id) {
        Usuario usuario = findUsuarioById(id);
        return usuarioMapperManual.toDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
            .map(usuarioMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteUsuario(Long id) {
        Usuario usuario = findUsuarioById(id);
        usuarioRepository.delete(usuario);
    }

    // Método privado para centralizar a lógica de busca do Usuario
    private Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
    }

    // Método privado para centralizar a lógica de busca das Roles
    private List<Role> findRolesByIds(Collection<Long> roleIds) {
        return roleRepository.findAllById(roleIds);
    }
}
