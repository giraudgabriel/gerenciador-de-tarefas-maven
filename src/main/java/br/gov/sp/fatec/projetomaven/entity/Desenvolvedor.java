package br.gov.sp.fatec.projetomaven.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "desenvolvedor")
@AttributeOverride(name = "id", column = @Column(name = "dev_id"))
@PrimaryKeyJoinColumn(name = "dev_id")
public class Desenvolvedor extends Usuario {
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "desenvolvedores")
    private Set<Tarefa> tarefas;
    
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