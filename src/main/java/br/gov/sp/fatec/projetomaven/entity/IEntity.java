package br.gov.sp.fatec.projetomaven.entity;

public abstract class IEntity {
    Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}