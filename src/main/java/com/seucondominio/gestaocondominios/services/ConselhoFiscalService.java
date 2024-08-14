package com.seucondominio.gestaocondominios.services;

import com.seucondominio.gestaocondominios.dto.ConselhoFiscalDTO;
import com.seucondominio.gestaocondominios.entities.ConselhoFiscal;
import com.seucondominio.gestaocondominios.entities.Condominio;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.ConselhoFiscalMapperManual;
import com.seucondominio.gestaocondominios.repositories.ConselhoFiscalRepository;
import com.seucondominio.gestaocondominios.repositories.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConselhoFiscalService implements IConselhoFiscalService {

    @Autowired
    private ConselhoFiscalRepository conselhoFiscalRepository;

    @Autowired
    private CondominioRepository condominioRepository;

    @Autowired
    private ConselhoFiscalMapperManual conselhoFiscalMapperManual;

    @Override
    public ConselhoFiscalDTO saveConselhoFiscal(ConselhoFiscalDTO conselhoFiscalDTO) {
        ConselhoFiscal conselhoFiscal = conselhoFiscalMapperManual.toEntity(conselhoFiscalDTO);
        conselhoFiscal.setCondominio(findCondominioById(conselhoFiscalDTO.getCondominioId()));
        conselhoFiscal = conselhoFiscalRepository.save(conselhoFiscal);
        return conselhoFiscalMapperManual.toDTO(conselhoFiscal);
    }

    @Override
    public ConselhoFiscalDTO updateConselhoFiscal(Long id, ConselhoFiscalDTO conselhoFiscalDTO) {
        ConselhoFiscal conselhoFiscal = findConselhoFiscalById(id);
        conselhoFiscalMapperManual.toEntity(conselhoFiscalDTO);
        conselhoFiscal.setCondominio(findCondominioById(conselhoFiscalDTO.getCondominioId()));
        conselhoFiscal = conselhoFiscalRepository.save(conselhoFiscal);
        return conselhoFiscalMapperManual.toDTO(conselhoFiscal);
    }

    @Override
    public ConselhoFiscalDTO getConselhoFiscalById(Long id) {
        ConselhoFiscal conselhoFiscal = findConselhoFiscalById(id);
        return conselhoFiscalMapperManual.toDTO(conselhoFiscal);
    }

    @Override
    public List<ConselhoFiscalDTO> getAllConselhosFiscais() {
        return conselhoFiscalRepository.findAll().stream()
            .map(conselhoFiscalMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteConselhoFiscal(Long id) {
        ConselhoFiscal conselhoFiscal = findConselhoFiscalById(id);
        conselhoFiscalRepository.delete(conselhoFiscal);
    }

    // Método privado para centralizar a lógica de busca do Conselho Fiscal
    private ConselhoFiscal findConselhoFiscalById(Long id) {
        return conselhoFiscalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Conselho Fiscal não encontrado com ID: " + id));
    }

    // Método privado para centralizar a lógica de busca do Condomínio
    private Condominio findCondominioById(Long id) {
        return condominioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + id));
    }
}
