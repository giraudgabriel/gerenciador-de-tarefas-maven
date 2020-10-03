package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class DesenvolvedorFrontend extends Desenvolvedor{
    @Column(name = "ui")
    private boolean ui;
    @Column(name = "ux")
    private boolean ux;

    public boolean isUi() {
        return ui;
    }

    public void setUi(boolean ui) {
        this.ui = ui;
    }

    public boolean isUx() {
        return ux;
    }

    public void setUx(boolean ux) {
        this.ux = ux;
    }
}