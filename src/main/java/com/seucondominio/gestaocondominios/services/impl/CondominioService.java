package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.CondominioDTO;
import com.seucondominio.gestaocondominios.entities.Condominio;
import com.seucondominio.gestaocondominios.entities.Role;
import com.seucondominio.gestaocondominios.entities.Usuario;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.CondominioMapperManual;
import com.seucondominio.gestaocondominios.repositories.CondominioRepository;
import com.seucondominio.gestaocondominios.repositories.RoleRepository;
import com.seucondominio.gestaocondominios.repositories.UsuarioRepository;
import com.seucondominio.gestaocondominios.services.interfaces.ICondominioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CondominioService implements ICondominioService {

    @Autowired
    private CondominioRepository condominioRepository;

    @Autowired
    private CondominioMapperManual condominioMapperManual;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CondominioDTO saveCondominio(CondominioDTO condominioDTO) {
        Condominio condominio = condominioMapperManual.toEntity(condominioDTO);

        // Cria o usuário admin para o condomínio
        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setUsername(condominio.getCnpj()); // username igual ao CNPJ
        usuarioAdmin.setPassword(passwordEncoder.encode(condominio.getCnpj())); // senha igual ao CNPJ

        // Busca a role admin e aplica ao usuário
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        usuarioAdmin.setRoles(Collections.singletonList(roleAdmin));

        // Salva o usuário admin no banco de dados
        usuarioAdmin = usuarioRepository.save(usuarioAdmin);

        // Associa o usuário admin ao condomínio
        condominio.setUsuarioAdmin(usuarioAdmin);

        // Salva o condomínio
        condominio = condominioRepository.save(condominio);

        return condominioMapperManual.toDTO(condominio);
    }

    @Override
    public CondominioDTO updateCondominio(Long id, CondominioDTO condominioDTO) {
        Condominio condominio = findCondominioById(id);
        condominioMapperManual.toEntity(condominioDTO); // Atualiza os campos
        condominio.setId(id);  // Garante que o ID do condomínio seja mantido
        condominio = condominioRepository.save(condominio);
        return condominioMapperManual.toDTO(condominio);
    }

    @Override
    public CondominioDTO getCondominioById(Long id) {
        Condominio condominio = findCondominioById(id);
        return condominioMapperManual.toDTO(condominio);
    }

    @Override
    public List<CondominioDTO> getAllCondominios() {
        return condominioRepository.findAll().stream()
            .map(condominioMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteCondominio(Long id) {
        Condominio condominio = findCondominioById(id);
        condominioRepository.delete(condominio);
    }

    // Método privado para centralizar a lógica de busca do Condominio
    private Condominio findCondominioById(Long id) {
        return condominioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + id));
    }
}
