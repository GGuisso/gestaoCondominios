package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.SindicoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.ISindicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sindicos")
@Tag(name = "Síndico", description = "Operações relacionadas a síndicos")
public class SindicoController {

    @Autowired
    private ISindicoService sindicoService;

    @PostMapping
    @Operation(summary = "Cadastra Síndico", description = "Realiza o cadastro de um novo síndico")
    public ResponseEntity<SindicoDTO> createSindico(@RequestBody SindicoDTO sindicoDTO) {
        SindicoDTO createdSindico = sindicoService.saveSindico(sindicoDTO);
        return new ResponseEntity<>(createdSindico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Síndico por ID", description = "Atualiza os dados de um síndico")
    public ResponseEntity<SindicoDTO> updateSindico(
        @Parameter(description = "ID do síndico a ser atualizado", required = true) 
        @PathVariable Long id, 
        @RequestBody SindicoDTO sindicoDTO) {
        SindicoDTO updatedSindico = sindicoService.updateSindico(id, sindicoDTO);
        return ResponseEntity.ok(updatedSindico);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Síndico por ID", description = "Retorna os detalhes de um síndico específico")
    public ResponseEntity<SindicoDTO> getSindicoById(
        @Parameter(description = "ID do síndico a ser buscado", required = true) 
        @PathVariable Long id) {
        SindicoDTO sindicoDTO = sindicoService.getSindicoById(id);
        return ResponseEntity.ok(sindicoDTO);
    }
    
    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar Síndico por CPF", description = "Retorna os detalhes de um síndico pelo CPF")
    public ResponseEntity<SindicoDTO> getSindicoByCpf(@PathVariable String cpf) {
        SindicoDTO sindicoDTO = sindicoService.getSindicoByCpf(cpf);
        return ResponseEntity.ok(sindicoDTO);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar Síndico por E-mail", description = "Retorna os detalhes de um síndico pelo e-mail")
    public ResponseEntity<SindicoDTO> getSindicoByEmail(@PathVariable String email) {
        SindicoDTO sindicoDTO = sindicoService.getSindicoByEmail(email);
        return ResponseEntity.ok(sindicoDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Síndicos", description = "Retorna os detalhes de todos os síndicos cadastrados")
    public ResponseEntity<List<SindicoDTO>> getAllSindicos() {
        List<SindicoDTO> sindicos = sindicoService.getAllSindicos();
        return ResponseEntity.ok(sindicos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Síndico por ID", description = "Remove o cadastro de um síndico")
    public ResponseEntity<Void> deleteSindico(
        @Parameter(description = "ID do síndico a ser deletado", required = true) 
        @PathVariable Long id) {
        sindicoService.deleteSindico(id);
        return ResponseEntity.noContent().build();
    }
}
