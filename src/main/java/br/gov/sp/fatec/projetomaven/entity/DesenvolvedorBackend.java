package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("Backend")
public class DesenvolvedorBackend extends DesenvolvedorFuncao{
    
}