package com.example.demo.Controller;

import com.example.demo.Model.PersonagemModel;
import com.example.demo.Service.PersonagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensMagicos")
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
}
