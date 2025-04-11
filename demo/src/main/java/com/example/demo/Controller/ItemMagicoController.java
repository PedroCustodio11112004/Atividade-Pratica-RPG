package com.example.demo.controller;

import com.example.demo.Model.ItemMagicoModel;
import com.example.demo.Service.ItemMagicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itensMagicos")
public class ItemMagicoController {

    private ItemMagicoService itemMagicoService;

    public ItemMagicoController(ItemMagicoService service) {
        this.itemMagicoService = service;
    }

    @PostMapping("/criarItemMagico")
    public ResponseEntity<ItemMagicoModel> postItemMagico(@RequestBody ItemMagicoModel item) {
        return ResponseEntity.ok(itemMagicoService.createItensMagico(item));
    }

    @GetMapping("/listarItensMAgico")
    public ResponseEntity<List<ItemMagicoModel>> getAllItemMagicos() {
        return ResponseEntity.ok(itemMagicoService.getAllItensMagicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagicoModel> getItemMagicoById(@PathVariable Long id) {
        return ResponseEntity.ok(itemMagicoService.getItensMagicoById(id));
    }
}