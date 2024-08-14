package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.MoradorDTO;
import com.seucondominio.gestaocondominios.services.IMoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moradores")
public class MoradorController {

    @Autowired
    private IMoradorService moradorService;

    @PostMapping
    public ResponseEntity<MoradorDTO> createMorador(@RequestBody MoradorDTO moradorDTO) {
        MoradorDTO createdMorador = moradorService.saveMorador(moradorDTO);
        return new ResponseEntity<>(createdMorador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoradorDTO> updateMorador(@PathVariable Long id, @RequestBody MoradorDTO moradorDTO) {
        MoradorDTO updatedMorador = moradorService.updateMorador(id, moradorDTO);
        return ResponseEntity.ok(updatedMorador);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoradorDTO> getMoradorById(@PathVariable Long id) {
        MoradorDTO moradorDTO = moradorService.getMoradorById(id);
        return ResponseEntity.ok(moradorDTO);
    }

    @GetMapping
    public ResponseEntity<List<MoradorDTO>> getAllMoradores() {
        List<MoradorDTO> moradores = moradorService.getAllMoradores();
        return ResponseEntity.ok(moradores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMorador(@PathVariable Long id) {
        moradorService.deleteMorador(id);
        return ResponseEntity.noContent().build();
    }
}
