package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("B")
@DiscriminatorColumn(name = "cargo", discriminatorType = DiscriminatorType.STRING)

public class DesenvolvedorBackend extends Desenvolvedor{
    
}