package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.ConselhoGestaoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IConselhoGestaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conselhosgestao")
public class ConselhoGestaoController {

    @Autowired
    private IConselhoGestaoService conselhoGestaoService;

    @PostMapping
    public ResponseEntity<ConselhoGestaoDTO> createConselhoGestao(@RequestBody ConselhoGestaoDTO conselhoGestaoDTO) {
        ConselhoGestaoDTO createdConselhoGestao = conselhoGestaoService.saveConselhoGestao(conselhoGestaoDTO);
        return new ResponseEntity<>(createdConselhoGestao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConselhoGestaoDTO> updateConselhoGestao(@PathVariable Long id, @RequestBody ConselhoGestaoDTO conselhoGestaoDTO) {
        ConselhoGestaoDTO updatedConselhoGestao = conselhoGestaoService.updateConselhoGestao(id, conselhoGestaoDTO);
        return ResponseEntity.ok(updatedConselhoGestao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConselhoGestaoDTO> getConselhoGestaoById(@PathVariable Long id) {
        ConselhoGestaoDTO conselhoGestaoDTO = conselhoGestaoService.getConselhoGestaoById(id);
        return ResponseEntity.ok(conselhoGestaoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ConselhoGestaoDTO>> getAllConselhosGestao() {
        List<ConselhoGestaoDTO> conselhosGestao = conselhoGestaoService.getAllConselhosGestao();
        return ResponseEntity.ok(conselhosGestao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConselhoGestao(@PathVariable Long id) {
        conselhoGestaoService.deleteConselhoGestao(id);
        return ResponseEntity.noContent().build();
    }
}
