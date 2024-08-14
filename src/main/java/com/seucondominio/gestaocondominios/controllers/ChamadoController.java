package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.ChamadoDTO;
import com.seucondominio.gestaocondominios.services.IChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    @Autowired
    private IChamadoService chamadoService;

    @PostMapping
    public ResponseEntity<ChamadoDTO> createChamado(@RequestBody ChamadoDTO chamadoDTO) {
        ChamadoDTO createdChamado = chamadoService.saveChamado(chamadoDTO);
        return new ResponseEntity<>(createdChamado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDTO> updateChamado(@PathVariable Long id, @RequestBody ChamadoDTO chamadoDTO) {
        ChamadoDTO updatedChamado = chamadoService.updateChamado(id, chamadoDTO);
        return ResponseEntity.ok(updatedChamado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> getChamadoById(@PathVariable Long id) {
        ChamadoDTO chamadoDTO = chamadoService.getChamadoById(id);
        return ResponseEntity.ok(chamadoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> getAllChamados() {
        List<ChamadoDTO> chamados = chamadoService.getAllChamados();
        return ResponseEntity.ok(chamados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChamado(@PathVariable Long id) {
        chamadoService.deleteChamado(id);
        return ResponseEntity.noContent().build();
    }
}
