package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.ChamadoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IChamadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
@Tag(name = "Chamado", description = "Operações relacionadas a chamados")
public class ChamadoController {

    @Autowired
    private IChamadoService chamadoService;

    @PostMapping
    @Operation(summary = "Cadastra Chamado", description = "Realiza o cadastro de um novo chamado")
    public ResponseEntity<ChamadoDTO> createChamado(@RequestBody ChamadoDTO chamadoDTO) {
        ChamadoDTO createdChamado = chamadoService.saveChamado(chamadoDTO);
        return new ResponseEntity<>(createdChamado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Chamado por ID", description = "Atualiza os dados de um chamado")
    public ResponseEntity<ChamadoDTO> updateChamado(
        @Parameter(description = "ID do chamado a ser atualizado", required = true) 
        @PathVariable Long id, 
        @RequestBody ChamadoDTO chamadoDTO) {
        ChamadoDTO updatedChamado = chamadoService.updateChamado(id, chamadoDTO);
        return ResponseEntity.ok(updatedChamado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Chamado por ID", description = "Retorna os detalhes de um chamado específico")
    public ResponseEntity<ChamadoDTO> getChamadoById(
        @Parameter(description = "ID do chamado a ser buscado", required = true) 
        @PathVariable Long id) {
        ChamadoDTO chamadoDTO = chamadoService.getChamadoById(id);
        return ResponseEntity.ok(chamadoDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Chamados", description = "Retorna os detalhes de todos os chamados cadastrados")
    public ResponseEntity<List<ChamadoDTO>> getAllChamados() {
        List<ChamadoDTO> chamados = chamadoService.getAllChamados();
        return ResponseEntity.ok(chamados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Chamado por ID", description = "Remove o cadastro de um chamado")
    public ResponseEntity<Void> deleteChamado(
        @Parameter(description = "ID do chamado a ser deletado", required = true) 
        @PathVariable Long id) {
        chamadoService.deleteChamado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/morador/{moradorId}")
    @Operation(summary = "Buscar Chamados por Morador", description = "Retorna os chamados feitos por um morador específico")
    public ResponseEntity<List<ChamadoDTO>> getChamadosByMoradorId(
        @Parameter(description = "ID do morador", required = true) 
        @PathVariable Long moradorId) {
        List<ChamadoDTO> chamados = chamadoService.getChamadosByMoradorId(moradorId);
        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/sindico/{sindicoId}")
    @Operation(summary = "Buscar Chamados por Síndico", description = "Retorna os chamados atribuídos a um síndico específico")
    public ResponseEntity<List<ChamadoDTO>> getChamadosBySindicoId(
        @Parameter(description = "ID do síndico", required = true) 
        @PathVariable Long sindicoId) {
        List<ChamadoDTO> chamados = chamadoService.getChamadosBySindicoId(sindicoId);
        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar Chamados por Status", description = "Retorna os chamados com um status específico")
    public ResponseEntity<List<ChamadoDTO>> getChamadosByStatus(
        @Parameter(description = "Status do chamado", required = true) 
        @PathVariable String status) {
        List<ChamadoDTO> chamados = chamadoService.getChamadosByStatus(status);
        return ResponseEntity.ok(chamados);
    }
}
