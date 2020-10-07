package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}