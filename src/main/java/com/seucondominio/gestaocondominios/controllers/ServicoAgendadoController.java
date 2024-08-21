package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.ServicoAgendadoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IServicoAgendadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos-agendados")
@Tag(name = "Serviço Agendado", description = "Operações relacionadas a serviços agendados")
public class ServicoAgendadoController {

    @Autowired
    private IServicoAgendadoService servicoAgendadoService;

    @PostMapping
    @Operation(summary = "Cadastra Serviço Agendado", description = "Realiza o cadastro de um novo serviço agendado")
    public ResponseEntity<ServicoAgendadoDTO> createServicoAgendado(@RequestBody ServicoAgendadoDTO servicoAgendadoDTO) {
        ServicoAgendadoDTO createdServicoAgendado = servicoAgendadoService.saveServicoAgendado(servicoAgendadoDTO);
        return new ResponseEntity<>(createdServicoAgendado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Serviço Agendado por ID", description = "Atualiza os dados de um serviço agendado")
    public ResponseEntity<ServicoAgendadoDTO> updateServicoAgendado(
        @Parameter(description = "ID do serviço agendado a ser atualizado", required = true)
        @PathVariable Long id, 
        @RequestBody ServicoAgendadoDTO servicoAgendadoDTO) {
        ServicoAgendadoDTO updatedServicoAgendado = servicoAgendadoService.updateServicoAgendado(id, servicoAgendadoDTO);
        return ResponseEntity.ok(updatedServicoAgendado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar serviço agendado por ID", description = "Retorna os detalhes de um serviço agendado específico")
    public ResponseEntity<ServicoAgendadoDTO> getServicoAgendadoById(
        @Parameter(description = "ID do serviço agendado a ser buscado", required = true) 
        @PathVariable Long id) {
        ServicoAgendadoDTO servicoAgendadoDTO = servicoAgendadoService.getServicoAgendadoById(id);
        return ResponseEntity.ok(servicoAgendadoDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Serviços Agendados", description = "Retorna os detalhes de todos os serviços agendados cadastrados")
    public ResponseEntity<List<ServicoAgendadoDTO>> getAllServicosAgendados() {
        List<ServicoAgendadoDTO> servicosAgendados = servicoAgendadoService.getAllServicosAgendados();
        return ResponseEntity.ok(servicosAgendados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta serviço agendado por ID", description = "Remove o cadastro de um serviço agendado")
    public ResponseEntity<Void> deleteServicoAgendado(
        @Parameter(description = "ID do serviço agendado a ser deletado", required = true) 
        @PathVariable Long id) {
        servicoAgendadoService.deleteServicoAgendado(id);
        return ResponseEntity.noContent().build();
    }
}
