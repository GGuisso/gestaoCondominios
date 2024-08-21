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

    @GetMapping("/servicoAgendado/{servicoAgendadoId}")
    @Operation(summary = "Buscar Anexos por Serviço Agendado", description = "Retorna os anexos relacionados a um serviço agendado específico")
    public ResponseEntity<List<AnexoDTO>> getAnexosByServicoAgendadoId(
        @Parameter(description = "ID do serviço agendado", required = true) 
        @PathVariable Long servicoAgendadoId) {
        List<AnexoDTO> anexos = anexoService.getAnexosByServicoAgendadoId(servicoAgendadoId);
        return ResponseEntity.ok(anexos);
    }

    @GetMapping("/profissional/{profissionalId}")
    @Operation(summary = "Buscar Anexos por Profissional", description = "Retorna os anexos relacionados a um profissional específico")
    public ResponseEntity<List<AnexoDTO>> getAnexosByProfissionalId(
        @Parameter(description = "ID do profissional", required = true) 
        @PathVariable Long profissionalId) {
        List<AnexoDTO> anexos = anexoService.getAnexosByProfissionalId(profissionalId);
        return ResponseEntity.ok(anexos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Anexo por ID", description = "Remove um anexo pelo seu ID")
    public ResponseEntity<Void> deleteAnexo(
        @Parameter(description = "ID do anexo a ser deletado", required = true) 
        @PathVariable Long id) {
        anexoService.deleteAnexo(id);
        return ResponseEntity.noContent().build();
    }
}
