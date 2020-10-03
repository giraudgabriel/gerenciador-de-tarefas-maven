package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class DesenvolvedorBackend extends Desenvolvedor{
    @Column(name = "bd")
    private boolean bd;

    public boolean hasBD() {
        return bd;
    }

    public void setBD(boolean bd) {
        this.bd = bd;
    }
    
}