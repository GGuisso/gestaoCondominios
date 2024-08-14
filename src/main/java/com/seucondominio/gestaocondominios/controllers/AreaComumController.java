package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.AreaComumDTO;
import com.seucondominio.gestaocondominios.services.IAreaComumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areascomuns")
public class AreaComumController {

    @Autowired
    private IAreaComumService areaComumService;

    // POST: Criar uma nova área comum
    @PostMapping
    public ResponseEntity<AreaComumDTO> createAreaComum(@RequestBody AreaComumDTO areaComumDTO) {
        AreaComumDTO createdAreaComum = areaComumService.saveAreaComum(areaComumDTO);
        return new ResponseEntity<>(createdAreaComum, HttpStatus.CREATED);
    }

    // PUT: Atualizar uma área comum existente
    @PutMapping("/{id}")
    public ResponseEntity<AreaComumDTO> updateAreaComum(@PathVariable Long id, @RequestBody AreaComumDTO areaComumDTO) {
        AreaComumDTO updatedAreaComum = areaComumService.updateAreaComum(id, areaComumDTO);
        return ResponseEntity.ok(updatedAreaComum);
    }

    // GET: Obter uma área comum pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<AreaComumDTO> getAreaComumById(@PathVariable Long id) {
        AreaComumDTO areaComumDTO = areaComumService.getAreaComumById(id);
        return ResponseEntity.ok(areaComumDTO);
    }

    // GET: Obter todas as áreas comuns
    @GetMapping
    public ResponseEntity<List<AreaComumDTO>> getAllAreasComuns() {
        List<AreaComumDTO> areasComuns = areaComumService.getAllAreasComuns();
        return ResponseEntity.ok(areasComuns);
    }

    // DELETE: Deletar uma área comum
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAreaComum(@PathVariable Long id) {
        areaComumService.deleteAreaComum(id);
        return ResponseEntity.noContent().build();
    }
}
