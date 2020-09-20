package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Gerente;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

    
public class GerenteDaoJpa implements GerenteDao {

    private EntityManager em;

    public GerenteDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public GerenteDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Gerente cadastrarGerente(String nome, String nomeUsuario, String senha) {
        Gerente gerente = new Gerente();
        gerente.setNome(nome);
        gerente.setNomeUsuario(nomeUsuario);
        gerente.setSenha(senha);
        return salvarGerente(gerente);
    }

    @Override
    public Gerente salvarGerente(Gerente gerente) {
        try {
            em.getTransaction().begin();
            if (gerente.getId() == null) {
                em.persist(gerente);
            } else {
                em.merge(gerente);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return gerente;
    }

    @Override
    public Gerente buscarGerentePorId(Long id) {
        return em.find(Gerente.class, id);
    }

    @Override
    public Gerente buscarGerentePorNomeUsuario(String nomeUsuario) {
        String jpql = "select g from Gerente g where g.nomeUsuario = :nomeUsuario";
        TypedQuery<Gerente> query = em.createQuery(jpql, Gerente.class);
        query.setParameter("nomeUsuario", nomeUsuario);
        return query.getSingleResult();
    }

    @Override
    public void removerGerente(String nomeUsuario) {
        Gerente gerente = buscarGerentePorNomeUsuario(nomeUsuario);
        if (gerente == null)
            return;
        try {
            em.getTransaction().begin();
            em.remove(gerente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void removerGerente(Long idGerente) {
        Gerente gerente = buscarGerentePorId(idGerente);
        if (gerente == null)
            return;
        try {
            em.getTransaction().begin();
            em.remove(gerente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

}