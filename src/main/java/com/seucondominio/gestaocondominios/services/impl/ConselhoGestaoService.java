package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.ConselhoGestaoDTO;
import com.seucondominio.gestaocondominios.entities.ConselhoGestao;
import com.seucondominio.gestaocondominios.entities.Condominio;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.ConselhoGestaoMapperManual;
import com.seucondominio.gestaocondominios.repositories.ConselhoGestaoRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IConselhoGestaoService;
import com.seucondominio.gestaocondominios.repositories.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConselhoGestaoService implements IConselhoGestaoService {

    @Autowired
    private ConselhoGestaoRepository conselhoGestaoRepository;

    @Autowired
    private CondominioRepository condominioRepository;

    @Autowired
    private ConselhoGestaoMapperManual conselhoGestaoMapperManual;

    @Override
    public ConselhoGestaoDTO saveConselhoGestao(ConselhoGestaoDTO conselhoGestaoDTO) {
        ConselhoGestao conselhoGestao = conselhoGestaoMapperManual.toEntity(conselhoGestaoDTO);
        conselhoGestao.setCondominio(findCondominioById(conselhoGestaoDTO.getCondominioId()));
        conselhoGestao = conselhoGestaoRepository.save(conselhoGestao);
        return conselhoGestaoMapperManual.toDTO(conselhoGestao);
    }

    @Override
    public ConselhoGestaoDTO updateConselhoGestao(Long id, ConselhoGestaoDTO conselhoGestaoDTO) {
        ConselhoGestao conselhoGestao = findConselhoGestaoById(id);
        conselhoGestaoMapperManual.toEntity(conselhoGestaoDTO);
        conselhoGestao.setCondominio(findCondominioById(conselhoGestaoDTO.getCondominioId()));
        conselhoGestao = conselhoGestaoRepository.save(conselhoGestao);
        return conselhoGestaoMapperManual.toDTO(conselhoGestao);
    }

    @Override
    public ConselhoGestaoDTO getConselhoGestaoById(Long id) {
        ConselhoGestao conselhoGestao = findConselhoGestaoById(id);
        return conselhoGestaoMapperManual.toDTO(conselhoGestao);
    }

    @Override
    public List<ConselhoGestaoDTO> getAllConselhosGestao() {
        return conselhoGestaoRepository.findAll().stream()
            .map(conselhoGestaoMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteConselhoGestao(Long id) {
        ConselhoGestao conselhoGestao = findConselhoGestaoById(id);
        conselhoGestaoRepository.delete(conselhoGestao);
    }

    // Método privado para centralizar a lógica de busca do Conselho de Gestão
    private ConselhoGestao findConselhoGestaoById(Long id) {
        return conselhoGestaoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Conselho de Gestão não encontrado com ID: " + id));
    }

    // Método privado para centralizar a lógica de busca do Condomínio
    private Condominio findCondominioById(Long id) {
        return condominioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + id));
    }
}
