package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.MoradorDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IMoradorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moradores")
@Tag(name = "Morador", description = "Operações relacionadas a moradores")
public class MoradorController {

    @Autowired
    private IMoradorService moradorService;

    @PostMapping
    @Operation(summary = "Cadastra Morador", description = "Realiza o cadastro de um novo morador")
    public ResponseEntity<MoradorDTO> createMorador(@RequestBody MoradorDTO moradorDTO) {
        MoradorDTO createdMorador = moradorService.saveMorador(moradorDTO);
        return new ResponseEntity<>(createdMorador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Morador por ID", description = "Atualiza os dados de um morador")
    public ResponseEntity<MoradorDTO> updateMorador(
        @Parameter(description = "ID do morador a ser atualizado", required = true)
        @PathVariable Long id, 
        @RequestBody MoradorDTO moradorDTO) {
        MoradorDTO updatedMorador = moradorService.updateMorador(id, moradorDTO);
        return ResponseEntity.ok(updatedMorador);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar morador por ID", description = "Retorna os detalhes de um morador específico")
    public ResponseEntity<MoradorDTO> getMoradorById(
        @Parameter(description = "ID do morador a ser buscado", required = true) 
        @PathVariable Long id) {
        MoradorDTO moradorDTO = moradorService.getMoradorById(id);
        return ResponseEntity.ok(moradorDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Moradores", description = "Retorna os detalhes de todos os moradores cadastrados")
    public ResponseEntity<List<MoradorDTO>> getAllMoradores() {
        List<MoradorDTO> moradores = moradorService.getAllMoradores();
        return ResponseEntity.ok(moradores);
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar morador por CPF", description = "Retorna os detalhes de um morador específico")
    public ResponseEntity<MoradorDTO> getMoradorByCpf(
        @Parameter(description = "CPF do morador a ser buscado", required = true) 
        @PathVariable String cpf) {
        MoradorDTO moradorDTO = moradorService.getMoradorByCpf(cpf);
        return ResponseEntity.ok(moradorDTO);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar morador por EMAIL", description = "Retorna os detalhes de um morador específico")
    public ResponseEntity<List<MoradorDTO>> getMoradoresByEmail(
        @Parameter(description = "E-mail do morador a ser buscado", required = true) 
        @PathVariable String email) {
        List<MoradorDTO> moradores = moradorService.getMoradoresByEmail(email);
        return ResponseEntity.ok(moradores);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta morador por ID", description = "Remove o cadastro de um morador")
    public ResponseEntity<Void> deleteMorador(
        @Parameter(description = "ID do morador a ser deletado", required = true) 
        @PathVariable Long id) {
        moradorService.deleteMorador(id);
        return ResponseEntity.noContent().build();
    }
}
