package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.AvisoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IAvisoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avisos")
@Tag(name = "Aviso", description = "Operações relacionadas aos avisos")
public class AvisoController {

    @Autowired
    private IAvisoService avisoService;

    @PostMapping
    @Operation(summary = "Cadastra Aviso", description = "Realiza o cadastro de um novo aviso")
    public ResponseEntity<AvisoDTO> createAviso(@RequestBody AvisoDTO avisoDTO) {
        AvisoDTO createdAviso = avisoService.saveAviso(avisoDTO);
        return new ResponseEntity<>(createdAviso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Aviso por ID", description = "Atualiza os dados de um aviso existente")
    public ResponseEntity<AvisoDTO> updateAviso(
        @Parameter(description = "ID do aviso a ser atualizado", required = true) 
        @PathVariable Long id, 
        @RequestBody AvisoDTO avisoDTO) {
        AvisoDTO updatedAviso = avisoService.updateAviso(id, avisoDTO);
        return ResponseEntity.ok(updatedAviso);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Aviso por ID", description = "Retorna os detalhes de um aviso específico")
    public ResponseEntity<AvisoDTO> getAvisoById(
        @Parameter(description = "ID do aviso a ser buscado", required = true) 
        @PathVariable Long id) {
        AvisoDTO avisoDTO = avisoService.getAvisoById(id);
        return ResponseEntity.ok(avisoDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Avisos", description = "Retorna os detalhes de todos os avisos cadastrados")
    public ResponseEntity<List<AvisoDTO>> getAllAvisos() {
        List<AvisoDTO> avisos = avisoService.getAllAvisos();
        return ResponseEntity.ok(avisos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Aviso por ID", description = "Remove o cadastro de um aviso")
    public ResponseEntity<Void> deleteAviso(
        @Parameter(description = "ID do aviso a ser deletado", required = true) 
        @PathVariable Long id) {
        avisoService.deleteAviso(id);
        return ResponseEntity.noContent().build();
    }
}
