package com.cursoSpring.desafio2.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_participante")
public class Participante {

    @Id
    private Integer id;
    private String nome;
    private String email;

    @ManyToMany
    @JoinTable(name = "tb_participante_atividade",
                joinColumns = @JoinColumn(name = "participante_id"),
                inverseJoinColumns = @JoinColumn(name = "atividade_id"))
    private Set<Atividade> atividades;

    public Participante() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
