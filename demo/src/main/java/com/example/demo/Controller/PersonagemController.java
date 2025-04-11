package com.example.demo.Controller;

import com.example.demo.Model.ItemMagicoModel;
import com.example.demo.Model.PersonagemModel;
import com.example.demo.Service.PersonagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    private PersonagemService personagemservice;

    public PersonagemController(PersonagemService service) {
        this.personagemservice = service;
    }

    @PostMapping("/criarPersonagem")
    public ResponseEntity<PersonagemModel> postPersoangem(@RequestBody PersonagemModel personagem) {
        return ResponseEntity.ok(personagemservice.createPersonagem(personagem));
    }

    @GetMapping("listar")
    public ResponseEntity<List<PersonagemModel>> getAllPersonagem() {
        return ResponseEntity.ok(personagemservice.getAllPersonagem());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemModel> getPersoagemById(@PathVariable Long id) {
        return ResponseEntity.ok(personagemservice.getByPersonagemId(id));
    }

    @PutMapping("/{id}/nomeAventureiro")
    public ResponseEntity<PersonagemModel> putNomeAventireiro(@PathVariable Long id, @RequestBody String nomeAventureiro) {
        return ResponseEntity.ok(personagemservice.updatePersonagem(id, nomeAventureiro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonagem(@PathVariable Long id) {
        personagemservice.deletePersonagem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/itens/{itemId}")
    public ResponseEntity<PersonagemModel> postItem(
            @PathVariable Long id, @PathVariable Long itemId) {
        return ResponseEntity.ok(personagemservice.addItem(id, itemId));
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemMagicoModel>> getItens(@PathVariable Long id) {
        return ResponseEntity.ok(personagemservice.getItemByPersonagem(id));
    }

    @DeleteMapping("/{id}/itens/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id, @PathVariable Long itemId) {
        personagemservice.deleteItem(id, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/amuleto")
    public ResponseEntity<ItemMagicoModel> getAmuleto(@PathVariable Long id) {
        return ResponseEntity.ok(personagemservice.getAmuleto(id));
    }
}
