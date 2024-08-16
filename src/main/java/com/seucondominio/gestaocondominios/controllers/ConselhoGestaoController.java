package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.ConselhoGestaoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IConselhoGestaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conselhosgestao")
@Tag(name = "Conselho de Gestão", description = "Operações relacionadas aos conselhos de gestão")
public class ConselhoGestaoController {

    @Autowired
    private IConselhoGestaoService conselhoGestaoService;

    @PostMapping
    @Operation(summary = "Cadastra Conselho de Gestão", description = "Realiza o cadastro de um novo conselho de gestão")
    public ResponseEntity<ConselhoGestaoDTO> createConselhoGestao(@RequestBody ConselhoGestaoDTO conselhoGestaoDTO) {
        ConselhoGestaoDTO createdConselhoGestao = conselhoGestaoService.saveConselhoGestao(conselhoGestaoDTO);
        return new ResponseEntity<>(createdConselhoGestao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Conselho de Gestão por ID", description = "Atualiza os dados de um conselho de gestão existente")
    public ResponseEntity<ConselhoGestaoDTO> updateConselhoGestao(
        @Parameter(description = "ID do conselho de gestão a ser atualizado", required = true) 
        @PathVariable Long id, 
        @RequestBody ConselhoGestaoDTO conselhoGestaoDTO) {
        ConselhoGestaoDTO updatedConselhoGestao = conselhoGestaoService.updateConselhoGestao(id, conselhoGestaoDTO);
        return ResponseEntity.ok(updatedConselhoGestao);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Conselho de Gestão por ID", description = "Retorna os detalhes de um conselho de gestão específico")
    public ResponseEntity<ConselhoGestaoDTO> getConselhoGestaoById(
        @Parameter(description = "ID do conselho de gestão a ser buscado", required = true) 
        @PathVariable Long id) {
        ConselhoGestaoDTO conselhoGestaoDTO = conselhoGestaoService.getConselhoGestaoById(id);
        return ResponseEntity.ok(conselhoGestaoDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Conselhos de Gestão", description = "Retorna os detalhes de todos os conselhos de gestão cadastrados")
    public ResponseEntity<List<ConselhoGestaoDTO>> getAllConselhosGestao() {
        List<ConselhoGestaoDTO> conselhosGestao = conselhoGestaoService.getAllConselhosGestao();
        return ResponseEntity.ok(conselhosGestao);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Conselho de Gestão por ID", description = "Remove o cadastro de um conselho de gestão")
    public ResponseEntity<Void> deleteConselhoGestao(
        @Parameter(description = "ID do conselho de gestão a ser deletado", required = true) 
        @PathVariable Long id) {
        conselhoGestaoService.deleteConselhoGestao(id);
        return ResponseEntity.noContent().build();
    }
}
