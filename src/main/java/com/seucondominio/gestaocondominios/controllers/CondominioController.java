package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.CondominioDTO;
import com.seucondominio.gestaocondominios.services.interfaces.ICondominioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/condominios")
@Tag(name = "Condomínio", description = "Operações relacionadas a condomínios")
public class CondominioController {

    @Autowired
    private ICondominioService condominioService;

    @PostMapping
    @Operation(summary = "Cadastra Condomínio", description = "Realiza o cadastro de um novo condomínio")
    public ResponseEntity<CondominioDTO> createCondominio(@RequestBody CondominioDTO condominioDTO) {
        CondominioDTO createdCondominio = condominioService.saveCondominio(condominioDTO);
        return new ResponseEntity<>(createdCondominio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Condomínio por ID", description = "Atualiza os dados de um condomínio")
    public ResponseEntity<CondominioDTO> updateCondominio(
        @Parameter(description = "ID do condomínio a ser atualizado", required = true) 
        @PathVariable Long id, 
        @RequestBody CondominioDTO condominioDTO) {
        CondominioDTO updatedCondominio = condominioService.updateCondominio(id, condominioDTO);
        return ResponseEntity.ok(updatedCondominio);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Condomínio por ID", description = "Retorna os detalhes de um condomínio específico")
    public ResponseEntity<CondominioDTO> getCondominioById(
        @Parameter(description = "ID do condomínio a ser buscado", required = true) 
        @PathVariable Long id) {
        CondominioDTO condominioDTO = condominioService.getCondominioById(id);
        return ResponseEntity.ok(condominioDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Condomínios", description = "Retorna os detalhes de todos os condomínios cadastrados")
    public ResponseEntity<List<CondominioDTO>> getAllCondominios() {
        List<CondominioDTO> condominios = condominioService.getAllCondominios();
        return ResponseEntity.ok(condominios);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Condomínio por ID", description = "Remove o cadastro de um condomínio")
    public ResponseEntity<Void> deleteCondominio(
        @Parameter(description = "ID do condomínio a ser deletado", required = true) 
        @PathVariable Long id) {
        condominioService.deleteCondominio(id);
        return ResponseEntity.noContent().build();
    }
}
