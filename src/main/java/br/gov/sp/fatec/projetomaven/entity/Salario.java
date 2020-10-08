package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Salario extends Usuario {
    private Float salario;

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }
    
}