package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.ConselhoFiscalDTO;
import com.seucondominio.gestaocondominios.services.IConselhoFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conselhosfiscais")
public class ConselhoFiscalController {

    @Autowired
    private IConselhoFiscalService conselhoFiscalService;

    @PostMapping
    public ResponseEntity<ConselhoFiscalDTO> createConselhoFiscal(@RequestBody ConselhoFiscalDTO conselhoFiscalDTO) {
        ConselhoFiscalDTO createdConselhoFiscal = conselhoFiscalService.saveConselhoFiscal(conselhoFiscalDTO);
        return new ResponseEntity<>(createdConselhoFiscal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConselhoFiscalDTO> updateConselhoFiscal(@PathVariable Long id, @RequestBody ConselhoFiscalDTO conselhoFiscalDTO) {
        ConselhoFiscalDTO updatedConselhoFiscal = conselhoFiscalService.updateConselhoFiscal(id, conselhoFiscalDTO);
        return ResponseEntity.ok(updatedConselhoFiscal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConselhoFiscalDTO> getConselhoFiscalById(@PathVariable Long id) {
        ConselhoFiscalDTO conselhoFiscalDTO = conselhoFiscalService.getConselhoFiscalById(id);
        return ResponseEntity.ok(conselhoFiscalDTO);
    }

    @GetMapping
    public ResponseEntity<List<ConselhoFiscalDTO>> getAllConselhosFiscais() {
        List<ConselhoFiscalDTO> conselhosFiscais = conselhoFiscalService.getAllConselhosFiscais();
        return ResponseEntity.ok(conselhosFiscais);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConselhoFiscal(@PathVariable Long id) {
        conselhoFiscalService.deleteConselhoFiscal(id);
        return ResponseEntity.noContent().build();
    }
}
