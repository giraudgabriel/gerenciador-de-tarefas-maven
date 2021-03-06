package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Gerente;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

public class GerenteDaoJpa extends IEntityDaoJpa<Gerente> implements GerenteDao {

    private EntityManager em;

    public GerenteDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public GerenteDaoJpa(EntityManager manager) {
        super(Gerente.class, manager);
        this.em = manager;
    }

    @Override
    public Gerente buscarGerentePorNomeUsuario(String nomeUsuario) {
        String jpql = "select g from Gerente g where g.nomeUsuario = :nomeUsuario";
        TypedQuery<Gerente> query = em.createQuery(jpql, Gerente.class);
        query.setParameter("nomeUsuario", nomeUsuario);
        return query.getSingleResult();
    }

}