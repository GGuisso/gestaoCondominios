package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.AgendamentoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IAgendamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@Tag(name = "Agendamento", description = "Operações relacionadas a agendamentos")
public class AgendamentoController {

    @Autowired
    private IAgendamentoService agendamentoService;

    @PostMapping
    @Operation(summary = "Cadastra Agendamento", description = "Realiza o cadastro de um novo agendamento")
    public ResponseEntity<AgendamentoDTO> createAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO createdAgendamento = agendamentoService.saveAgendamento(agendamentoDTO);
        return new ResponseEntity<>(createdAgendamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Agendamento por ID", description = "Atualiza os dados de um agendamento")
    public ResponseEntity<AgendamentoDTO> updateAgendamento(
        @Parameter(description = "ID do agendamento a ser atualizado", required = true) 
        @PathVariable Long id, 
        @RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO updatedAgendamento = agendamentoService.updateAgendamento(id, agendamentoDTO);
        return ResponseEntity.ok(updatedAgendamento);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Agendamento por ID", description = "Retorna os detalhes de um agendamento específico")
    public ResponseEntity<AgendamentoDTO> getAgendamentoById(
        @Parameter(description = "ID do agendamento a ser buscado", required = true) 
        @PathVariable Long id) {
        AgendamentoDTO agendamentoDTO = agendamentoService.getAgendamentoById(id);
        return ResponseEntity.ok(agendamentoDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Agendamentos", description = "Retorna os detalhes de todos os agendamentos cadastrados")
    public ResponseEntity<List<AgendamentoDTO>> getAllAgendamentos() {
        List<AgendamentoDTO> agendamentos = agendamentoService.getAllAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Agendamento por ID", description = "Remove o cadastro de um agendamento")
    public ResponseEntity<Void> deleteAgendamento(
        @Parameter(description = "ID do agendamento a ser deletado", required = true) 
        @PathVariable Long id) {
        agendamentoService.deleteAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}
