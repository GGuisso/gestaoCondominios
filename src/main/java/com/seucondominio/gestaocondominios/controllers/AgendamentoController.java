package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.AgendamentoDTO;
import com.seucondominio.gestaocondominios.services.IAgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private IAgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoDTO> createAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO createdAgendamento = agendamentoService.saveAgendamento(agendamentoDTO);
        return new ResponseEntity<>(createdAgendamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> updateAgendamento(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO updatedAgendamento = agendamentoService.updateAgendamento(id, agendamentoDTO);
        return ResponseEntity.ok(updatedAgendamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> getAgendamentoById(@PathVariable Long id) {
        AgendamentoDTO agendamentoDTO = agendamentoService.getAgendamentoById(id);
        return ResponseEntity.ok(agendamentoDTO);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> getAllAgendamentos() {
        List<AgendamentoDTO> agendamentos = agendamentoService.getAllAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        agendamentoService.deleteAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}
