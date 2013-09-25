package br.com.fk1.labs.eventos.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Evento {
    
    @Id
    private Integer id;
    
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Evento com id " + id + " e nome " + nome;
    }
}
