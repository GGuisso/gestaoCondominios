package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.AnexoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IAnexoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anexos")
@Tag(name = "Anexo", description = "Operações relacionadas a anexos")
public class AnexoController {

    @Autowired
    private IAnexoService anexoService;

    @PostMapping
    @Operation(summary = "Cadastra Anexo", description = "Realiza o cadastro de um novo anexo")
    public ResponseEntity<AnexoDTO> createAnexo(@RequestBody AnexoDTO anexoDTO) {
        AnexoDTO createdAnexo = anexoService.saveAnexo(anexoDTO);
        return new ResponseEntity<>(createdAnexo, HttpStatus.CREATED);
    }

    @GetMapping("/chamado/{chamadoId}")
    @Operation(summary = "Buscar Anexos por Chamado", description = "Retorna os anexos relacionados a um chamado específico")
    public ResponseEntity<List<AnexoDTO>> getAnexosByChamadoId(
        @Parameter(description = "ID do chamado", required = true) 
        @PathVariable Long chamadoId) {
        List<AnexoDTO> anexos = anexoService.getAnexosByChamadoId(chamadoId);
        return ResponseEntity.ok(anexos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Anexo por ID", description = "Remove o cadastro de um anexo")
    public ResponseEntity<Void> deleteAnexo(
        @Parameter(description = "ID do anexo a ser deletado", required = true) 
        @PathVariable Long id) {
        anexoService.deleteAnexo(id);
        return ResponseEntity.noContent().build();
    }
}
