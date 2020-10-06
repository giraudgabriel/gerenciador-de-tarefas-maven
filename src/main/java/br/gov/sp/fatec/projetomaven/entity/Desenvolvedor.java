package br.gov.sp.fatec.projetomaven.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "desenvolvedor")
@PrimaryKeyJoinColumn(name = "id")
public class Desenvolvedor extends Usuario {
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "desenvolvedores")
    private Set<Tarefa> tarefas;
}