package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.SindicoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.ISindicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sindicos")
public class SindicoController {

    @Autowired
    private ISindicoService sindicoService;

    @PostMapping
    public ResponseEntity<SindicoDTO> createSindico(@RequestBody SindicoDTO sindicoDTO) {
        SindicoDTO createdSindico = sindicoService.saveSindico(sindicoDTO);
        return new ResponseEntity<>(createdSindico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SindicoDTO> updateSindico(@PathVariable Long id, @RequestBody SindicoDTO sindicoDTO) {
        SindicoDTO updatedSindico = sindicoService.updateSindico(id, sindicoDTO);
        return ResponseEntity.ok(updatedSindico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SindicoDTO> getSindicoById(@PathVariable Long id) {
        SindicoDTO sindicoDTO = sindicoService.getSindicoById(id);
        return ResponseEntity.ok(sindicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<SindicoDTO>> getAllSindicos() {
        List<SindicoDTO> sindicos = sindicoService.getAllSindicos();
        return ResponseEntity.ok(sindicos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSindico(@PathVariable Long id) {
        sindicoService.deleteSindico(id);
        return ResponseEntity.noContent().build();
    }
}
