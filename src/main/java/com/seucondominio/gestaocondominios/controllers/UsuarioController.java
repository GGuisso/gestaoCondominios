package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.UsuarioDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario", description = "Operações relacionadas a usuários")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Cadastra Usuário", description = "Realiza o cadastro de um novo usuário")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO createdUsuario = usuarioService.saveUsuario(usuarioDTO);
        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Usuário por ID", description = "Atualiza os dados de um usuário")
    public ResponseEntity<UsuarioDTO> updateUsuario(
        @Parameter(description = "ID do usuário a ser atualizado", required = true)
        @PathVariable Long id, 
        @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updatedUsuario = usuarioService.updateUsuario(id, usuarioDTO);
        return ResponseEntity.ok(updatedUsuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os detalhes de um usuário específico")
    public ResponseEntity<UsuarioDTO> getUsuarioById(
        @Parameter(description = "ID do usuário a ser buscado", required = true) 
        @PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Usuários", description = "Retorna os detalhes de todos os usuários cadastrados")
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta usuário por ID", description = "Remove o cadastro de um usuário")
    public ResponseEntity<Void> deleteUsuario(
        @Parameter(description = "ID do usuário a ser deletado", required = true) 
        @PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
