package br.gov.sp.fatec.projetomaven.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.projetomaven.entity.DesenvolvedorFuncao;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

public class DesenvolvedorFuncaoDaoJpa extends IEntityDaoJpa<DesenvolvedorFuncao> implements DesenvolvedorFuncaoDao{

     private EntityManager em;

    public DesenvolvedorFuncaoDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public DesenvolvedorFuncaoDaoJpa(EntityManager manager) {
        super(DesenvolvedorFuncao.class, manager);
        this.em = manager;
    }    
}