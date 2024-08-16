package com.seucondominio.gestaocondominios.services.impl;

import com.seucondominio.gestaocondominios.dto.MoradorDTO;
import com.seucondominio.gestaocondominios.entities.Morador;
import com.seucondominio.gestaocondominios.entities.Condominio;
import com.seucondominio.gestaocondominios.entities.ConselhoGestao;
import com.seucondominio.gestaocondominios.entities.ConselhoFiscal;
import com.seucondominio.gestaocondominios.entities.Torre;
import com.seucondominio.gestaocondominios.entities.Unidade;
import com.seucondominio.gestaocondominios.exception.EntityNotFoundException;
import com.seucondominio.gestaocondominios.mapper.MoradorMapperManual;
import com.seucondominio.gestaocondominios.repositories.MoradorRepository;
import com.seucondominio.gestaocondominios.repositories.CondominioRepository;
import com.seucondominio.gestaocondominios.repositories.ConselhoGestaoRepository;
import com.seucondominio.gestaocondominios.repositories.ConselhoFiscalRepository;
import com.seucondominio.gestaocondominios.repositories.TorreRepository;
import com.seucondominio.gestaocondominios.repositories.UnidadeRepository;
import com.seucondominio.gestaocondominios.services.interfaces.IMoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoradorService implements IMoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private CondominioRepository condominioRepository;

    @Autowired
    private ConselhoGestaoRepository conselhoGestaoRepository;

    @Autowired
    private ConselhoFiscalRepository conselhoFiscalRepository;

    @Autowired
    private TorreRepository torreRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private MoradorMapperManual moradorMapperManual;

    @Override
    public MoradorDTO saveMorador(MoradorDTO moradorDTO) {
        Morador morador = moradorMapperManual.toEntity(moradorDTO);
        morador.setCondominio(findCondominioById(moradorDTO.getCondominioId()));
        morador.setTorre(findTorreById(moradorDTO.getTorreId()));
        morador.setUnidade(findUnidadeById(moradorDTO.getUnidadeId()));
        
        if (moradorDTO.getConselhoGestaoId() != null) {
            morador.setConselhoGestao(findConselhoGestaoById(moradorDTO.getConselhoGestaoId()));
        } else {
            morador.setConselhoGestao(null);
        }

        if (moradorDTO.getConselhoFiscalId() != null) {
            morador.setConselhoFiscal(findConselhoFiscalById(moradorDTO.getConselhoFiscalId()));
        } else {
            morador.setConselhoFiscal(null);
        }

        morador = moradorRepository.save(morador);
        return moradorMapperManual.toDTO(morador);
    }

    @Override
    public MoradorDTO updateMorador(Long id, MoradorDTO moradorDTO) {
        Morador morador = findMoradorById(id);
        moradorMapperManual.toEntity(moradorDTO);
        morador.setCondominio(findCondominioById(moradorDTO.getCondominioId()));
        morador.setTorre(findTorreById(moradorDTO.getTorreId()));
        morador.setUnidade(findUnidadeById(moradorDTO.getUnidadeId()));

        if (moradorDTO.getConselhoGestaoId() != null) {
            morador.setConselhoGestao(findConselhoGestaoById(moradorDTO.getConselhoGestaoId()));
        } else {
            morador.setConselhoGestao(null);
        }

        if (moradorDTO.getConselhoFiscalId() != null) {
            morador.setConselhoFiscal(findConselhoFiscalById(moradorDTO.getConselhoFiscalId()));
        } else {
            morador.setConselhoFiscal(null);
        }

        morador = moradorRepository.save(morador);
        return moradorMapperManual.toDTO(morador);
    }

    @Override
    public MoradorDTO getMoradorById(Long id) {
        Morador morador = findMoradorById(id);
        return moradorMapperManual.toDTO(morador);
    }

    @Override
    public List<MoradorDTO> getAllMoradores() {
        return moradorRepository.findAll().stream()
            .map(moradorMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteMorador(Long id) {
        Morador morador = findMoradorById(id);
        moradorRepository.delete(morador);
    }

    @Override
    public MoradorDTO getMoradorByCpf(String cpf) {
        Morador morador = moradorRepository.findByCpf(cpf)
            .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com CPF: " + cpf));
        return moradorMapperManual.toDTO(morador);
    }

    @Override
    public List<MoradorDTO> getMoradoresByEmail(String email) {
        return moradorRepository.findByEmail(email).stream()
            .map(moradorMapperManual::toDTO)
            .collect(Collectors.toList());
    }

    // Método privado para centralizar a lógica de busca do Morador
    private Morador findMoradorById(Long id) {
        return moradorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + id));
    }

    // Método privado para centralizar a lógica de busca do Condomínio
    private Condominio findCondominioById(Long id) {
        return condominioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado com ID: " + id));
    }

    // Método privado para centralizar a lógica de busca da Torre
    private Torre findTorreById(Long id) {
        return torreRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Torre não encontrada com ID: " + id));
    }

    // Método privado para centralizar a lógica de busca da Unidade
    private Unidade findUnidadeById(Long id) {
        return unidadeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada com ID: " + id));
    }

    // Método privado para centralizar a lógica de busca do Conselho de Gestão
    private ConselhoGestao findConselhoGestaoById(Long id) {
        return conselhoGestaoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Conselho de Gestão não encontrado com ID: " + id));
    }

    // Método privado para centralizar a lógica de busca do Conselho Fiscal
    private ConselhoFiscal findConselhoFiscalById(Long id) {
        return conselhoFiscalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Conselho Fiscal não encontrado com ID: " + id));
    }
}
