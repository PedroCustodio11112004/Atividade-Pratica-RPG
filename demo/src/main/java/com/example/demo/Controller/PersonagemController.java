package com.example.demo.Controller;

import com.example.demo.DTO.ItemMagicoResponse;
import com.example.demo.DTO.PersonagemRequest;
import com.example.demo.DTO.PersonagemResponse;
import com.example.demo.Service.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {
    private final PersonagemService personagemservice;

    public PersonagemController(PersonagemService service) {
        this.personagemservice = service;
    }

    @PostMapping("/criarPersonagem")
    @Operation(summary = "Cria um novo personagem", description = "Força Base e Defesa Base devem somar 10")
    @ApiResponse(responseCode = "200", description = "Personagem criado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<PersonagemResponse> postPersoangem(@RequestBody PersonagemRequest request) {
        try {
            return ResponseEntity.ok(personagemservice.createPersonagem(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os personagens")
    @ApiResponse(responseCode = "200", description = "Lista retornada")
    public ResponseEntity<List<PersonagemResponse>> getAllPersonagem() {
        return ResponseEntity.ok(personagemservice.getAllPersonagem());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um personagem por Id")
    @ApiResponse(responseCode = "200", description = "Personagem encontrado")
    @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    public ResponseEntity<PersonagemResponse> getPersoagemById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personagemservice.getByPersonagemId(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/nomeAventureiro")
    @Operation(summary = "Atualiza o nome aventureiro de um personagem")
    @ApiResponse(responseCode = "200", description = "Nome atualizado")
    @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    public ResponseEntity<PersonagemResponse> putNomeAventireiro(@PathVariable Long id, @RequestBody String nomeAventureiro) {
        try {
            return ResponseEntity.ok(personagemservice.updatePersonagem(id, nomeAventureiro));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um personagem por ID")
    @ApiResponse(responseCode = "204", description = "Personagem removido")
    @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    public ResponseEntity<Void> deletePersonagem(@PathVariable Long id) {
        try {
            personagemservice.deletePersonagem(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/itens/{itemId}")
    @Operation(summary = "Adiciona um item mágico a um personagem")
    @ApiResponse(responseCode = "200", description = "Item adicionado")
    @ApiResponse(responseCode = "404", description = "Personagem ou item não encontrado")
    public ResponseEntity<PersonagemResponse> postItemPersonagem(@PathVariable Long id, @PathVariable Long idItem) {
        try {
            return ResponseEntity.ok(personagemservice.addItemMagicoPersonagem(id, idItem));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/itens")
    @Operation(summary = "Lista os itens mágicos de um personagem")
    @ApiResponse(responseCode = "200", description = "Itens retornados")
    @ApiResponse(responseCode = "404", description = "Personagem não encontrado")
    public ResponseEntity<List<ItemMagicoResponse>> getItensPersonagem(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personagemservice.getItemByPersonagem(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/itens/{itemId}")
    @Operation(summary = "Remove um item mágico de um personagem")
    @ApiResponse(responseCode = "204", description = "Item removido")
    @ApiResponse(responseCode = "404", description = "Personagem ou item não encontrado")
    public ResponseEntity<Void> deleteItemPersonagem(@PathVariable Long id, @PathVariable Long idItem) {
        try {
            personagemservice.deleteItem(id, idItem);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/amuleto")
    @Operation(summary = "Busca o amuleto de um personagem")
    @ApiResponse(responseCode = "200", description = "Amuleto retornado")
    @ApiResponse(responseCode = "404", description = "Personagem ou amuleto não encontrado")
    public ResponseEntity<ItemMagicoResponse> getAmuletoPersonagem(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personagemservice.getAmuletoPersonagem(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}