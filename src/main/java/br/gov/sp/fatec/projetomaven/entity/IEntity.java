package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public abstract class IEntity {
    @Id
    @GeneratedValue
    Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}