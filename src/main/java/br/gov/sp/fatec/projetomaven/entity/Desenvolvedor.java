package br.gov.sp.fatec.projetomaven.entity;

import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "desenvolvedor")
@Entity
@PrimaryKeyJoinColumn(name = "dev_id")
@DiscriminatorColumn(name = "cargo")
@DiscriminatorValue("Desenvolvedor")
public class Desenvolvedor extends Funcionario {

    private String cargo;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "desenvolvedores")
    private Set<Tarefa> tarefas;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}