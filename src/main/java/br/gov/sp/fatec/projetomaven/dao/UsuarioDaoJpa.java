package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;
import br.gov.sp.fatec.projetomaven.entity.Usuario;

public class UsuarioDaoJpa extends IEntityDaoJpa<Usuario> implements UsuarioDao {

    private EntityManager em;

    public UsuarioDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public UsuarioDaoJpa(EntityManager manager) {
        super(Usuario.class, manager);
        this.em = manager;
    }

    @Override
    public Usuario buscarPorNomeUsuarioESenha(String nomeUsuario, String senha) {
        try {
            String jpql = "select g from Usuario g where g.nomeUsuario = :nomeUsuario and g.senha = :senha";
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("nomeUsuario", nomeUsuario);
            query.setParameter("senha", senha);
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}