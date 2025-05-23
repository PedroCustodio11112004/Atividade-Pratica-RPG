package com.example.demo.Repository;

import com.example.demo.Model.ItemMagicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemMagicoRepository extends JpaRepository<ItemMagicoModel, Long> {
    List<ItemMagicoModel> findByPersonagemId(Long personagemId);
}
