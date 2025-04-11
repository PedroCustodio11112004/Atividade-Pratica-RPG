package com.example.demo.Repository;

import com.example.demo.Model.PersonagemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<PersonagemModel, Long> {
}
