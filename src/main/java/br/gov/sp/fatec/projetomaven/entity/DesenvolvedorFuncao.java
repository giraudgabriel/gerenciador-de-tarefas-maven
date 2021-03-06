package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "desenvolvedor_funcao")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AttributeOverride(name = "id", column = @Column(name = "dev_fun_id"))
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "funcao")
public class DesenvolvedorFuncao extends IEntity{
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dev_id")
    private Desenvolvedor desenvolvedor;


    public Desenvolvedor getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }
   
}