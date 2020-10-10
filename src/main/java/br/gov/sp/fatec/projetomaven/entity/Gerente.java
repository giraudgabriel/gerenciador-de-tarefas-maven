package br.gov.sp.fatec.projetomaven.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Table(name = "gerente")
@Entity
@PrimaryKeyJoinColumn(name = "ger_id")
public class Gerente extends Usuario {

    @Column(name = "titulo")
    private String titulo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gerente")
    private Set<Tarefa> tarefasGerenciadas;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Tarefa> getTarefasGerenciadas() {
        return tarefasGerenciadas;
    }

    public void setTarefasGerenciadas(Set<Tarefa> tarefasGerenciadas) {
        this.tarefasGerenciadas = tarefasGerenciadas;
    }

}