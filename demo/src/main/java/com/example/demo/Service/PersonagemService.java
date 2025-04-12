package com.example.demo.Service;


import com.example.demo.DTO.ItemMagicoResponse;
import com.example.demo.DTO.PersonagemRequest;
import com.example.demo.DTO.PersonagemResponse;
import com.example.demo.Model.ItemMagicoModel;
import com.example.demo.Model.PersonagemModel;
import com.example.demo.Model.TipoItem;
import com.example.demo.Repository.ItemMagicoRepository;
import com.example.demo.Repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonagemService {
    private final PersonagemRepository personagemRepository;
    private final ItemMagicoRepository itemMagicoRepository;

    public PersonagemService(PersonagemRepository personagemRepository, ItemMagicoRepository itemMagicoRepository) {
        this.personagemRepository = personagemRepository;
        this.itemMagicoRepository = itemMagicoRepository;
    }

    public PersonagemResponse createPersonagem(PersonagemRequest request) {
        PersonagemModel personagem = new PersonagemModel();
        personagem.setNome(request.getNome());
        personagem.setNomeAventureiro(request.getNomeAventureiro());
        personagem.setClasse(request.getClasse());
        personagem.setLevel(request.getLevel());
        personagem.setAtributosBase(request.getForcaBase(), request.getDefesaBase());
        PersonagemModel saved = personagemRepository.save(personagem);
        return toResponse(saved);
    }

    public List<PersonagemResponse> getAllPersonagem() {
        return personagemRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PersonagemResponse getByPersonagemId(Long id) {
        PersonagemModel personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        return toResponse(personagem);
    }

    public PersonagemResponse updatePersonagem(Long id, String nomeAventureiro) {
        PersonagemModel personagemModel = personagemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        personagemModel.setNomeAventureiro(nomeAventureiro);
        PersonagemModel saved = personagemRepository.save(personagemModel);
        return toResponse(saved);
    }

    public void deletePersonagem(Long id) {
        PersonagemModel personagemModel = personagemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        personagemRepository.delete(personagemModel);
    }

    public PersonagemResponse addItemMagicoPersonagem(Long personagemId, Long itemId) {
        PersonagemModel personagemModel = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        ItemMagicoModel item = itemMagicoRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));
        personagemModel.adicionarItem(item);
        PersonagemModel saved = personagemRepository.save(personagemModel);
        return toResponse(saved);
    }

    public List<ItemMagicoResponse> getItemByPersonagem(Long personagemId) {
        PersonagemModel personagemModel = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        return personagemModel.getItensMagicos().stream()
                .map(this::toItemResponse)
                .collect(Collectors.toList());
    }

    public void deleteItem(Long personagemId, Long itemId) {
        PersonagemModel personagemModel = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        ItemMagicoModel item = personagemModel.getItensMagicos().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));
        personagemModel.getItensMagicos().remove(item);
        personagemRepository.save(personagemModel);
    }

    public ItemMagicoResponse getAmuletoPersonagem(Long personagemId) {
        PersonagemModel personagemModel = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new IllegalArgumentException("Personagem não encontrado"));
        ItemMagicoModel amuleto = personagemModel.getItensMagicos().stream()
                .filter(i -> i.getTipo() == TipoItem.Amuleto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Amuleto não encontrado"));
        return toItemResponse(amuleto);
    }

    private PersonagemResponse toResponse(PersonagemModel model) {
        PersonagemResponse personagemResponse = new PersonagemResponse();
        personagemResponse.setId(model.getId());
        personagemResponse.setNome(model.getNome());
        personagemResponse.setNomeAventureiro(model.getNomeAventureiro());
        personagemResponse.setClasse(model.getClasse());
        personagemResponse.setLevel(model.getLevel());
        personagemResponse.setForcaBase(model.getForcaBase());
        personagemResponse.setDefesaBase(model.getDefesaBase());
        personagemResponse.setForca(model.getForca());
        personagemResponse.setDefesa(model.getDefesa());
        personagemResponse.setItensMagicos(model.getItensMagicos().stream()
                .map(this::toItemResponse)
                .collect(Collectors.toList()));
        return personagemResponse;
    }

    private ItemMagicoResponse toItemResponse(ItemMagicoModel model) {
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