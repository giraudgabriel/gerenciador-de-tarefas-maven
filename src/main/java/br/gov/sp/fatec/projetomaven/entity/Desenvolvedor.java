package br.gov.sp.fatec.projetomaven.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "desenvolvedor")
@PrimaryKeyJoinColumn(name = "dev_id")
public class Desenvolvedor extends Usuario {
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "desenvolvedores")
    private Set<Tarefa> tarefas;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "desenvolvedor")
    private Set<DesenvolvedorFuncao> funcoes;

	public Set<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(Set<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

    public Set<DesenvolvedorFuncao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(Set<DesenvolvedorFuncao> funcoes) {
        this.funcoes = funcoes;
    }
}