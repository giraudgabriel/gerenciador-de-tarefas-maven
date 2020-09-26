package br.gov.sp.fatec.projetomaven.entity;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Funcionario extends Usuario {
    private BigDecimal salario;
    private String cargo;

    public BigDecimal getSalario() {
        return salario;
    } 

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
