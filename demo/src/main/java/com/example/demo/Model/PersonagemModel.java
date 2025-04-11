package com.example.demo.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PersonagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    private ClassePersonagem classe;

    private int level;
    private int forcaBase;
    private int defesaBase;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMagicoModel> itensMagicos = new ArrayList<>();

    public PersonagemModel() {}

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

    public List<ItemMagicoModel> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagicoModel> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

    public int getForcaBase() {
        return forcaBase;
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    // Método para definir atributos base com validação
    public void setAtributosBase(int forca, int defesa) {
        if (forca + defesa != 10 || forca < 0 || defesa < 0) {
            throw new IllegalArgumentException("Força e Defesa devem somar exatamente 10 pontos.");
        }
        this.forcaBase = forca;
        this.defesaBase = defesa;
    }

    // Força total (base + itens)
    public int getForca() {
        return forcaBase + itensMagicos.stream().mapToInt(ItemMagicoModel::getForca).sum();
    }

    // Defesa total (base + itens)
    public int getDefesa() {
        return defesaBase + itensMagicos.stream().mapToInt(ItemMagicoModel::getDefesa).sum();
    }

    // Método para adicionar item com validação de amuleto
    public void adicionarItem(ItemMagicoModel item) {
        if (item.getTipo() == TipoItem.AMULETO) {
            long amuletoCount = itensMagicos.stream()
                    .filter(i -> i.getTipo() == TipoItem.AMULETO)
                    .count();
            if (amuletoCount >= 1) {
                throw new IllegalStateException("Personagem só pode ter 1 Amuleto.");
            }
        }
        item.setPersonagem(this);
        this.itensMagicos.add(item);
    }
}