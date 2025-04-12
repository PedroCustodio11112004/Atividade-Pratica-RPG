package com.example.demo.Controller;

import com.example.demo.DTO.ItemMagicoRequest;
import com.example.demo.DTO.ItemMagicoResponse;
import com.example.demo.Service.ItemMagicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensMagicos")
public class ItemMagicoController {
    private final ItemMagicoService itemMagicoservice;

    public ItemMagicoController(ItemMagicoService service) {
        this.itemMagicoservice = service;
    }

    @PostMapping("/criarItemMagico")
    @Operation(summary = "Cria um novo item mágico", description = "Arma: Defesa = 0, Força ≤ 10.\n" +
            "Armadura: Força = 0, Defesa ≤ 10.\n" +
            "Amuleto: Força e Defesa ≤ 10, limite de 1 por personagem.\n" +
            "Itens não podem ter Força = 0 e Defesa = 0")
    @ApiResponse(responseCode = "200", description = "Item criado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<ItemMagicoResponse> postItemMagico(@RequestBody ItemMagicoRequest request) {
        try {
            return ResponseEntity.ok(itemMagicoservice.createItemMagico(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listarItensMagicos")
    @Operation(summary = "Lista todos os itens mágicos")
    @ApiResponse(responseCode = "200", description = "Itens retornados")
    public ResponseEntity<List<ItemMagicoResponse>> getAllItemMagico() {
        return ResponseEntity.ok(itemMagicoservice.getAllItensMagicos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um item mágico por ID")
    @ApiResponse(responseCode = "200", description = "Item encontrado")
    @ApiResponse(responseCode = "404", description = "Item não encontrado")
    public ResponseEntity<ItemMagicoResponse> getItemMagicoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(itemMagicoservice.getItemMagicoById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}