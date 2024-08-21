package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.ProfissionalDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IProfissionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
@Tag(name = "Profissional", description = "Operações relacionadas a profissionais")
public class ProfissionalController {

    @Autowired
    private IProfissionalService profissionalService;

    @PostMapping
    @Operation(summary = "Cadastra Profissional", description = "Realiza o cadastro de um novo profissional")
    public ResponseEntity<ProfissionalDTO> createProfissional(@RequestBody ProfissionalDTO profissionalDTO) {
        ProfissionalDTO createdProfissional = profissionalService.saveProfissional(profissionalDTO);
        return new ResponseEntity<>(createdProfissional, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Profissional por ID", description = "Atualiza os dados de um profissional")
    public ResponseEntity<ProfissionalDTO> updateProfissional(
        @Parameter(description = "ID do profissional a ser atualizado", required = true)
        @PathVariable Long id, 
        @RequestBody ProfissionalDTO profissionalDTO) {
        ProfissionalDTO updatedProfissional = profissionalService.updateProfissional(id, profissionalDTO);
        return ResponseEntity.ok(updatedProfissional);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar profissional por ID", description = "Retorna os detalhes de um profissional específico")
    public ResponseEntity<ProfissionalDTO> getProfissionalById(
        @Parameter(description = "ID do profissional a ser buscado", required = true) 
        @PathVariable Long id) {
        ProfissionalDTO profissionalDTO = profissionalService.getProfissionalById(id);
        return ResponseEntity.ok(profissionalDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Profissionais", description = "Retorna os detalhes de todos os profissionais cadastrados")
    public ResponseEntity<List<ProfissionalDTO>> getAllProfissionais() {
        List<ProfissionalDTO> profissionais = profissionalService.getAllProfissionais();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar profissional por CPF", description = "Retorna os detalhes de um profissional específico")
    public ResponseEntity<ProfissionalDTO> getProfissionalByCpf(
        @Parameter(description = "CPF do profissional a ser buscado", required = true) 
        @PathVariable String cpf) {
        ProfissionalDTO profissionalDTO = profissionalService.getProfissionalByCpf(cpf);
        return ResponseEntity.ok(profissionalDTO);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar profissional por EMAIL", description = "Retorna os detalhes de um profissional específico")
    public ResponseEntity<List<ProfissionalDTO>> getProfissionaisByEmail(
        @Parameter(description = "E-mail do profissional a ser buscado", required = true) 
        @PathVariable String email) {
        List<ProfissionalDTO> profissionais = profissionalService.getProfissionaisByEmail(email);
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar profissional por STATUS", description = "Retorna os detalhes de profissionais específicos por status")
    public ResponseEntity<List<ProfissionalDTO>> getProfissionaisByStatus(
        @Parameter(description = "Status do profissional a ser buscado", required = true) 
        @PathVariable String status) {
        List<ProfissionalDTO> profissionais = profissionalService.getProfissionaisByStatus(status);
        return ResponseEntity.ok(profissionais);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta profissional por ID", description = "Remove o cadastro de um profissional")
    public ResponseEntity<Void> deleteProfissional(
        @Parameter(description = "ID do profissional a ser deletado", required = true) 
        @PathVariable Long id) {
        profissionalService.deleteProfissional(id);
        return ResponseEntity.noContent().build();
    }
}
