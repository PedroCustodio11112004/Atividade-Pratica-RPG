package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
public class ItemMagicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private int forca;
    private int defesa;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private PersonagemModel personagem;

    public ItemMagicoModel() {}

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

    public int getDefesa() {
        return defesa;
    }

    public PersonagemModel getPersonagem() {
        return personagem;
    }
    public void setPersonagem(PersonagemModel personagem) {
        this.personagem = personagem;
    }

    public void setAtributos(int forca, int defesa) {
        if (forca < 0 || defesa < 0 || (forca == 0 && defesa == 0)) {
            throw new IllegalArgumentException("Força e Defesa não podem ser 0");
        }
        if (forca > 10 || defesa > 10) {
            throw new IllegalArgumentException("Força e Defesa não podem passar de 10");
        }
        if (tipo == TipoItem.Arma && defesa != 0) {
            throw new IllegalArgumentException("Arma deve ter Defesa 0");
        }
        if (tipo == TipoItem.Armadura && forca != 0) {
            throw new IllegalArgumentException("Armadura deve ter Força 0");
        }
        this.forca = forca;
        this.defesa = defesa;
    }
}
