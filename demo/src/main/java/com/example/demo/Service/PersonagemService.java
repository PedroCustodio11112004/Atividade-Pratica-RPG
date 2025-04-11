
package com.example.demo.Service;

import com.example.demo.Model.PersonagemModel;
import com.example.demo.Repository.ItemMagicoRepository;
import com.example.demo.Repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {
    private PersonagemRepository personagemRepository;

    public PersonagemService(PersonagemRepository personagemRepository, ItemMagicoRepository itemMagicoRepository) {
        this.personagemRepository = personagemRepository;
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


}
