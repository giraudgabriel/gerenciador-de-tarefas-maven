package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.projetomaven.entity.Desenvolvedor;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

public class DesenvolvedorDaoJpa extends IEntityDaoJpa<Desenvolvedor> implements DesenvolvedorDao {

    private EntityManager em;

    public DesenvolvedorDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public DesenvolvedorDaoJpa(EntityManager manager) {
        super(Desenvolvedor.class, manager);
        this.em = manager;
    }
}