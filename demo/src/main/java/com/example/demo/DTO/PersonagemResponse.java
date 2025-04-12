package com.example.demo.DTO;

import com.example.demo.Model.ClassePersonagem;

import java.util.List;

public class PersonagemResponse {
    private Long id;
    private String nome;
    private String nomeAventureiro;
    private ClassePersonagem classe;
    private int level;
    private int forcaBase;
    private int defesaBase;
    private int forca; // Total calculado
    private int defesa; // Total calculado
    private List<ItemMagicoResponse> itensMagicos;


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

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public ClassePersonagem getClasse() {
        return classe;
    }

    public void setClasse(ClassePersonagem classe) {
        this.classe = classe;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getForcaBase() {
        return forcaBase;
    }

    public void setForcaBase(int forcaBase) {
        this.forcaBase = forcaBase;
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    public void setDefesaBase(int defesaBase) {
        this.defesaBase = defesaBase;
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

    public List<ItemMagicoResponse> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagicoResponse> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }
}


