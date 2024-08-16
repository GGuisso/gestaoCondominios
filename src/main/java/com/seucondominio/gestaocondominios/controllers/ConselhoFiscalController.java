package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.ConselhoFiscalDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IConselhoFiscalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conselhosfiscais")
@Tag(name = "Conselho Fiscal", description = "Operações relacionadas aos conselhos fiscais")
public class ConselhoFiscalController {

    @Autowired
    private IConselhoFiscalService conselhoFiscalService;

    @PostMapping
    @Operation(summary = "Cadastra Conselho Fiscal", description = "Realiza o cadastro de um novo conselho fiscal")
    public ResponseEntity<ConselhoFiscalDTO> createConselhoFiscal(@RequestBody ConselhoFiscalDTO conselhoFiscalDTO) {
        ConselhoFiscalDTO createdConselhoFiscal = conselhoFiscalService.saveConselhoFiscal(conselhoFiscalDTO);
        return new ResponseEntity<>(createdConselhoFiscal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Conselho Fiscal por ID", description = "Atualiza os dados de um conselho fiscal existente")
    public ResponseEntity<ConselhoFiscalDTO> updateConselhoFiscal(
        @Parameter(description = "ID do conselho fiscal a ser atualizado", required = true) 
        @PathVariable Long id, 
        @RequestBody ConselhoFiscalDTO conselhoFiscalDTO) {
        ConselhoFiscalDTO updatedConselhoFiscal = conselhoFiscalService.updateConselhoFiscal(id, conselhoFiscalDTO);
        return ResponseEntity.ok(updatedConselhoFiscal);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Conselho Fiscal por ID", description = "Retorna os detalhes de um conselho fiscal específico")
    public ResponseEntity<ConselhoFiscalDTO> getConselhoFiscalById(
        @Parameter(description = "ID do conselho fiscal a ser buscado", required = true) 
        @PathVariable Long id) {
        ConselhoFiscalDTO conselhoFiscalDTO = conselhoFiscalService.getConselhoFiscalById(id);
        return ResponseEntity.ok(conselhoFiscalDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Conselhos Fiscais", description = "Retorna os detalhes de todos os conselhos fiscais cadastrados")
    public ResponseEntity<List<ConselhoFiscalDTO>> getAllConselhosFiscais() {
        List<ConselhoFiscalDTO> conselhosFiscais = conselhoFiscalService.getAllConselhosFiscais();
        return ResponseEntity.ok(conselhosFiscais);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Conselho Fiscal por ID", description = "Remove o cadastro de um conselho fiscal")
    public ResponseEntity<Void> deleteConselhoFiscal(
        @Parameter(description = "ID do conselho fiscal a ser deletado", required = true) 
        @PathVariable Long id) {
        conselhoFiscalService.deleteConselhoFiscal(id);
        return ResponseEntity.noContent().build();
    }
}
