package com.example.demo.DTO;

import com.example.demo.Model.ItemMagicoModel;
import com.example.demo.Model.TipoItem;

public class ItemMagicoResponse extends ItemMagicoModel {
    private Long id;
    private String nome;
    private TipoItem tipo;
    private int forca;
    private int defesa;
    private Long personagemId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public Long getPersonagemId() {
        return personagemId;
    }

    public void setPersonagemId(Long personagemId) {
        this.personagemId = personagemId;
    }
}
