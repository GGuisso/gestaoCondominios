package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.AvisoDTO;
import com.seucondominio.gestaocondominios.services.IAvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avisos")
public class AvisoController {

    @Autowired
    private IAvisoService avisoService;

    @PostMapping
    public ResponseEntity<AvisoDTO> createAviso(@RequestBody AvisoDTO avisoDTO) {
        AvisoDTO createdAviso = avisoService.saveAviso(avisoDTO);
        return new ResponseEntity<>(createdAviso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvisoDTO> updateAviso(@PathVariable Long id, @RequestBody AvisoDTO avisoDTO) {
        AvisoDTO updatedAviso = avisoService.updateAviso(id, avisoDTO);
        return ResponseEntity.ok(updatedAviso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisoDTO> getAvisoById(@PathVariable Long id) {
        AvisoDTO avisoDTO = avisoService.getAvisoById(id);
        return ResponseEntity.ok(avisoDTO);
    }

    @GetMapping
    public ResponseEntity<List<AvisoDTO>> getAllAvisos() {
        List<AvisoDTO> avisos = avisoService.getAllAvisos();
        return ResponseEntity.ok(avisos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAviso(@PathVariable Long id) {
        avisoService.deleteAviso(id);
        return ResponseEntity.noContent().build();
    }
}
