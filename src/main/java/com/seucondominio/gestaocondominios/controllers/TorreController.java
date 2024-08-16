package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.TorreDTO;
import com.seucondominio.gestaocondominios.services.interfaces.ITorreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torres")
@Tag(name = "Torre", description = "Operações relacionadas a torres")
public class TorreController {

    @Autowired
    private ITorreService torreService;

    @PostMapping
    @Operation(summary = "Cadastra Torre", description = "Realiza o cadastro de uma nova torre")
    public ResponseEntity<TorreDTO> createTorre(@RequestBody TorreDTO torreDTO) {
        TorreDTO createdTorre = torreService.saveTorre(torreDTO);
        return new ResponseEntity<>(createdTorre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Torre por ID", description = "Atualiza os dados de uma torre")
    public ResponseEntity<TorreDTO> updateTorre(
        @Parameter(description = "ID da torre a ser atualizada", required = true) 
        @PathVariable Long id, 
        @RequestBody TorreDTO torreDTO) {
        TorreDTO updatedTorre = torreService.updateTorre(id, torreDTO);
        return ResponseEntity.ok(updatedTorre);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Torre por ID", description = "Retorna os detalhes de uma torre específica")
    public ResponseEntity<TorreDTO> getTorreById(
        @Parameter(description = "ID da torre a ser buscada", required = true) 
        @PathVariable Long id) {
        TorreDTO torreDTO = torreService.getTorreById(id);
        return ResponseEntity.ok(torreDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Torres", description = "Retorna os detalhes de todas as torres cadastradas")
    public ResponseEntity<List<TorreDTO>> getAllTorres() {
        List<TorreDTO> torres = torreService.getAllTorres();
        return ResponseEntity.ok(torres);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Torre por ID", description = "Remove o cadastro de uma torre")
    public ResponseEntity<Void> deleteTorre(
        @Parameter(description = "ID da torre a ser deletada", required = true) 
        @PathVariable Long id) {
        torreService.deleteTorre(id);
        return ResponseEntity.noContent().build();
    }
}
