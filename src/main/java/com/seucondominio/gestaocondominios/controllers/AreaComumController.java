package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.AreaComumDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IAreaComumService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areascomuns")
@Tag(name = "Área Comum", description = "Operações relacionadas às áreas comuns")
public class AreaComumController {

    @Autowired
    private IAreaComumService areaComumService;

    @PostMapping
    @Operation(summary = "Cadastra Área Comum", description = "Realiza o cadastro de uma nova área comum")
    public ResponseEntity<AreaComumDTO> createAreaComum(@RequestBody AreaComumDTO areaComumDTO) {
        AreaComumDTO createdAreaComum = areaComumService.saveAreaComum(areaComumDTO);
        return new ResponseEntity<>(createdAreaComum, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Área Comum por ID", description = "Atualiza os dados de uma área comum existente")
    public ResponseEntity<AreaComumDTO> updateAreaComum(
        @Parameter(description = "ID da área comum a ser atualizada", required = true) 
        @PathVariable Long id, 
        @RequestBody AreaComumDTO areaComumDTO) {
        AreaComumDTO updatedAreaComum = areaComumService.updateAreaComum(id, areaComumDTO);
        return ResponseEntity.ok(updatedAreaComum);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Área Comum por ID", description = "Retorna os detalhes de uma área comum específica")
    public ResponseEntity<AreaComumDTO> getAreaComumById(
        @Parameter(description = "ID da área comum a ser buscada", required = true) 
        @PathVariable Long id) {
        AreaComumDTO areaComumDTO = areaComumService.getAreaComumById(id);
        return ResponseEntity.ok(areaComumDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Áreas Comuns", description = "Retorna os detalhes de todas as áreas comuns cadastradas")
    public ResponseEntity<List<AreaComumDTO>> getAllAreasComuns() {
        List<AreaComumDTO> areasComuns = areaComumService.getAllAreasComuns();
        return ResponseEntity.ok(areasComuns);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Área Comum por ID", description = "Remove o cadastro de uma área comum")
    public ResponseEntity<Void> deleteAreaComum(
        @Parameter(description = "ID da área comum a ser deletada", required = true) 
        @PathVariable Long id) {
        areaComumService.deleteAreaComum(id);
        return ResponseEntity.noContent().build();
    }
}
