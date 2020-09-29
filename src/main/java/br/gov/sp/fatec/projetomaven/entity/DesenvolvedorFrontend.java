package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "desenvolvedor_frontend")
@DiscriminatorValue("Frontend")
public class DesenvolvedorFrontend extends Desenvolvedor{
    private boolean ui;
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