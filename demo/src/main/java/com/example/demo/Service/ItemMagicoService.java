package com.example.demo.Service;

import com.example.demo.DTO.ItemMagicoRequest;
import com.example.demo.DTO.ItemMagicoResponse;
import com.example.demo.Model.ItemMagicoModel;
import com.example.demo.Repository.ItemMagicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMagicoService {
    private final ItemMagicoRepository itemMagicoRepository;

    public ItemMagicoService(ItemMagicoRepository itemMagicoRepository) {
        this.itemMagicoRepository = itemMagicoRepository;
    }

    public ItemMagicoResponse createItemMagico(ItemMagicoRequest request) {
        ItemMagicoModel item = new ItemMagicoModel();
        item.setNome(request.getNome());
        item.setTipo(request.getTipo());
        item.setAtributos(request.getForca(), request.getDefesa());
        ItemMagicoModel saved = itemMagicoRepository.save(item);
        return toResponse(saved);
    }

    public List<ItemMagicoResponse> getAllItensMagicos() {
        return itemMagicoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ItemMagicoResponse getItemMagicoById(Long id) {
        ItemMagicoModel item = itemMagicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));
        return toResponse(item);
    }

    private ItemMagicoResponse toResponse(ItemMagicoModel model) {
        ItemMagicoResponse itemMagicoresponse = new ItemMagicoResponse();
        itemMagicoresponse.setId(model.getId());
        itemMagicoresponse.setNome(model.getNome());
        itemMagicoresponse.setTipo(model.getTipo());
        itemMagicoresponse.setForca(model.getForca());
        itemMagicoresponse.setDefesa(model.getDefesa());
        itemMagicoresponse.setPersonagemId(model.getPersonagem() != null ? model.getPersonagem().getId() : null);
        return itemMagicoresponse;
    }
}