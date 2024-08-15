package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.AnexoDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IAnexoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anexos")
public class AnexoController {

    @Autowired
    private IAnexoService anexoService;

    @PostMapping
    public ResponseEntity<AnexoDTO> createAnexo(@RequestBody AnexoDTO anexoDTO) {
        AnexoDTO createdAnexo = anexoService.saveAnexo(anexoDTO);
        return new ResponseEntity<>(createdAnexo, HttpStatus.CREATED);
    }

    @GetMapping("/chamado/{chamadoId}")
    public ResponseEntity<List<AnexoDTO>> getAnexosByChamadoId(@PathVariable Long chamadoId) {
        List<AnexoDTO> anexos = anexoService.getAnexosByChamadoId(chamadoId);
        return ResponseEntity.ok(anexos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnexo(@PathVariable Long id) {
        anexoService.deleteAnexo(id);
        return ResponseEntity.noContent().build();
    }
}
