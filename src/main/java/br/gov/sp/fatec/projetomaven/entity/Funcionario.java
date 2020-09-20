package br.gov.sp.fatec.projetomaven.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Table(name = "fun_funcionario")
@Entity
@PrimaryKeyJoinColumn(name = "fun_id")
public class Funcionario extends Usuario{
    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String nomeUsuario, String senha) {
        super();
        setNome(nome);
        setNomeUsuario(nomeUsuario);
        setSenha(senha);
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "funcionarios")
    private Set<Tarefa> tarefas;

    public Set<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(Set<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
    
}