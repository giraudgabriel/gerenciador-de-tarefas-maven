package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "desenvolvedor_backend")
@DiscriminatorValue("Backend")
public class DesenvolvedorBackend extends Desenvolvedor{
    private boolean bd;

    public boolean hasBD() {
        return bd;
    }

    public void setBD(boolean bd) {
        this.bd = bd;
    }
    
}