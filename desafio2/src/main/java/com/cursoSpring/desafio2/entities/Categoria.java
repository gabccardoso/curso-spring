package com.cursoSpring.desafio2.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    private Integer id;
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private Set<Atividade> atividades;
    public Categoria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
