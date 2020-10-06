package br.gov.sp.fatec.projetomaven.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("F")
@DiscriminatorColumn(name = "cargo", discriminatorType = DiscriminatorType.STRING)
public class DesenvolvedorFrontend extends Desenvolvedor {

}