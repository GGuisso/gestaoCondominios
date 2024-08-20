package com.seucondominio.gestaocondominios.controllers;

import com.seucondominio.gestaocondominios.dto.RoleDTO;
import com.seucondominio.gestaocondominios.services.interfaces.IRoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role", description = "Operações relacionadas a roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping
    @Operation(summary = "Cadastra Role", description = "Realiza o cadastro de uma nova role")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO createdRole = roleService.saveRole(roleDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Role por ID", description = "Atualiza os dados de uma role")
    public ResponseEntity<RoleDTO> updateRole(
        @Parameter(description = "ID da role a ser atualizada", required = true)
        @PathVariable Long id, 
        @RequestBody RoleDTO roleDTO) {
        RoleDTO updatedRole = roleService.updateRole(id, roleDTO);
        return ResponseEntity.ok(updatedRole);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Role por ID", description = "Retorna os detalhes de uma role específica")
    public ResponseEntity<RoleDTO> getRoleById(
        @Parameter(description = "ID da role a ser buscada", required = true) 
        @PathVariable Long id) {
        RoleDTO roleDTO = roleService.getRoleById(id);
        return ResponseEntity.ok(roleDTO);
    }

    @GetMapping
    @Operation(summary = "Lista Roles", description = "Retorna os detalhes de todas as roles cadastradas")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta Role por ID", description = "Remove o cadastro de uma role")
    public ResponseEntity<Void> deleteRole(
        @Parameter(description = "ID da role a ser deletada", required = true) 
        @PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
