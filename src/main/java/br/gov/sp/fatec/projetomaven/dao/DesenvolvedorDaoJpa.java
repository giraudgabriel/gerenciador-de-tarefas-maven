package br.gov.sp.fatec.projetomaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @Override
    public List<Desenvolvedor> buscarTodos() {
        String jpql = "select g from Desenvolvedor g";
        TypedQuery<Desenvolvedor> query = em.createQuery(jpql, Desenvolvedor.class);
        return query.getResultList();
    }

    @Override
    public Desenvolvedor login(String nomeUsuario, String senha) {
        String jpql = "select g from Desenvolvedor g where g.nomeUsuario = :nomeUsuario and g.senha =:senha";
        TypedQuery<Desenvolvedor> query = em.createQuery(jpql, Desenvolvedor.class);
        query.setParameter("nomeUsuario", nomeUsuario);
        query.setParameter("senha", senha);
        return query.getSingleResult();
    }
}