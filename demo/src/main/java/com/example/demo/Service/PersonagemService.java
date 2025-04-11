
package com.example.demo.Service;

import com.example.demo.Model.ItemMagicoModel;
import com.example.demo.Model.PersonagemModel;
import com.example.demo.Model.TipoItem;
import com.example.demo.Repository.ItemMagicoRepository;
import com.example.demo.Repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {
    private PersonagemRepository personagemRepository;
    private ItemMagicoRepository itemMagicoRepository;

    public PersonagemService(PersonagemRepository personagemRepository, ItemMagicoRepository itemMagicoRepository) {
        this.personagemRepository = personagemRepository;
        this.itemMagicoRepository = itemMagicoRepository;
    }

    public List<PersonagemModel> getAllPersonagem() {
        return personagemRepository.findAll();
    }

    public PersonagemModel getByPersonagemId(Long id) {
        return personagemRepository.findById(id).orElse(null);
    }

    public PersonagemModel createPersonagem (PersonagemModel personagemModel) {
        personagemModel.setAtributosBase(personagemModel.getForcaBase(), personagemModel.getDefesaBase());
        return personagemRepository.save(personagemModel);
    }

    public PersonagemModel updatePersonagem(Long id, String nomeAventureiro) {
        PersonagemModel personagem = getByPersonagemId(id);
        personagem.setNomeAventureiro(nomeAventureiro);
        return personagemRepository.save(personagem);
    }

    public void deletePersonagem(Long id) {
        personagemRepository.deleteById(id);
    }

    public PersonagemModel addItem(Long idPersonage, Long idItem) {
        PersonagemModel personagem = getByPersonagemId(idPersonage);
        ItemMagicoModel item = itemMagicoRepository.findById(idItem).get();
        personagem.adicionarItem(item);
        return personagemRepository.save(personagem);
    }

    public List<ItemMagicoModel> getItemByPersonagem(Long idPersonagem) {
        return itemMagicoRepository.findByPersonagemId(idPersonagem);
    }

    public void deleteItem(Long idPersonagem, Long idItem) {
        PersonagemModel personagem = getByPersonagemId(idPersonagem);
        ItemMagicoModel item = personagem.getItensMagicos().stream()
                .filter(i -> i.getId().equals(idItem))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado."));        personagem.getItensMagicos().remove(item);
        personagemRepository.save(personagem);
    }

    public ItemMagicoModel getAmuleto(Long idPersonage) {
        return getItemByPersonagem(idPersonage).stream()
                .filter(i -> i.getTipo() == TipoItem.Amuleto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Amuleto não encontrado."));
    }
}
