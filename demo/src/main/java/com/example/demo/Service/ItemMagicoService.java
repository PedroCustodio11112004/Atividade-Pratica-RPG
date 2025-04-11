package com.example.demo.Service;

import com.example.demo.Model.ItemMagicoModel;
import com.example.demo.Repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMagicoService {

    private ItemMagicoRepository itemMagicoRepository;

    public ItemMagicoService(ItemMagicoRepository itemMagicoRepository) {
        this.itemMagicoRepository = itemMagicoRepository;
    }

    public List<ItemMagicoModel> getAllItensMagicos() {
        return itemMagicoRepository.findAll();
    }

    public ItemMagicoModel getItensMagicoById(long id) {
        return itemMagicoRepository.findById(id).orElse(null);
    }

    public ItemMagicoModel createItensMagico(ItemMagicoModel itemMagicoModel) {
        return itemMagicoRepository.save(itemMagicoModel);
    }


}
