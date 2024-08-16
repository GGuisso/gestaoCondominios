package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.UnidadeDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IUnidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
@Tag(name = "Unidade", description = "Operações relacionadas a unidades")
public class UnidadeController {

    @Autowired
    private IUnidadeService unidadeService;

    @PostMapping
    @Operation(summary = "Cadastra Unidade", description = "Realiza o cadastro de uma nova unidade")
    public ResponseEntity<UnidadeDTO> createUnidade(@RequestBody UnidadeDTO unidadeDTO) {
        UnidadeDTO createdUnidade = unidadeService.saveUnidade(unidadeDTO);
        return new ResponseEntity<>(createdUnidade, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Unidade por ID", description = "Atualiza os dados de uma unidade")
    public ResponseEntity<UnidadeDTO> updateUnidade(
        @Parameter(description = "ID da unidade a ser atualizada", required = true) 
        @PathVariable Long id, 
        @RequestBody UnidadeDTO unidadeDTO) {
        UnidadeDTO updatedUnidade = unidadeService.updateUnidade(id, unidadeDTO);
        return ResponseEntity.ok(updatedUnidade);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Unidade por ID", description = "Retorna os detalhes de uma unidade específica")
    public ResponseEntity<UnidadeDTO> getUnidadeById(
        @Parameter(description = "ID da unidade a ser buscada", required = true) 
        @PathVariable Long id) {
        UnidadeDTO unidadeDTO = unidadeService.getUnidadeById(id);
        return ResponseEntity.ok(unidadeDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Unidades", description = "Retorna os detalhes de todas as unidades cadastradas")
    public ResponseEntity<List<UnidadeDTO>> getAllUnidades() {
        List<UnidadeDTO> unidades = unidadeService.getAllUnidades();
        return ResponseEntity.ok(unidades);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Unidade por ID", description = "Remove o cadastro de uma unidade")
    public ResponseEntity<Void> deleteUnidade(
        @Parameter(description = "ID da unidade a ser deletada", required = true) 
        @PathVariable Long id) {
        unidadeService.deleteUnidade(id);
        return ResponseEntity.noContent().build();
    }
}
