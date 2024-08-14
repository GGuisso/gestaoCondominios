package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.CondominioDTO;
import com.seucondominio.gestaocondominios.services.ICondominioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/condominios")
public class CondominioController {

    @Autowired
    private ICondominioService condominioService;

    @PostMapping
    public ResponseEntity<CondominioDTO> createCondominio(@RequestBody CondominioDTO condominioDTO) {
        CondominioDTO createdCondominio = condominioService.saveCondominio(condominioDTO);
        return new ResponseEntity<>(createdCondominio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondominioDTO> updateCondominio(@PathVariable Long id, @RequestBody CondominioDTO condominioDTO) {
        CondominioDTO updatedCondominio = condominioService.updateCondominio(id, condominioDTO);
        return ResponseEntity.ok(updatedCondominio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondominioDTO> getCondominioById(@PathVariable Long id) {
        CondominioDTO condominioDTO = condominioService.getCondominioById(id);
        return ResponseEntity.ok(condominioDTO);
    }

    @GetMapping
    public ResponseEntity<List<CondominioDTO>> getAllCondominios() {
        List<CondominioDTO> condominios = condominioService.getAllCondominios();
        return ResponseEntity.ok(condominios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondominio(@PathVariable Long id) {
        condominioService.deleteCondominio(id);
        return ResponseEntity.noContent().build();
    }
}
