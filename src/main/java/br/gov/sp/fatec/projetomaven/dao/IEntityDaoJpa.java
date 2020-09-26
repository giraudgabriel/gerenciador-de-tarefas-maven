package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.projetomaven.entity.IEntity;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

public class IEntityDaoJpa<T extends IEntity> implements IEntityDao<T> {

    private EntityManager em;
    private Class<T> type;

    public IEntityDaoJpa(final Class<T> type) {
        this(type, PersistenceManager.getInstance().getEntityManager());
    }

    public IEntityDaoJpa(final Class<T> type, final EntityManager em) {
        this.type = type;
        this.em = em;
    }

    @Override
    public T salvar(final T model) {
        try {
            em.getTransaction().begin();
            salvarSemCommit(model);
            em.getTransaction().commit();
        } catch (final Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        return model;
    }

    @Override
    public T cadastrar(final T model) {
        return salvar(model);
    }

    @Override
    public T buscarPorId(final Long id) {
        return em.find(type, id);
    }

    @Override
    public void excluir(T model) {
        model = buscarPorId(model.getId());
        if (model == null)
            return;
        try {
            em.getTransaction().begin();
            em.remove(model);
            em.getTransaction().commit();
        } catch (final Exception e) {
            em.getTransaction().rollback();
        }

    }

    @Override
    public void excluir(final Long id) {
        final T model = buscarPorId(id);
        if (model == null)
            return;
        try {
            em.getTransaction().begin();
            em.remove(model);
            em.getTransaction().commit();
        } catch (final Exception e) {
            em.getTransaction().rollback();
        }

    }

    @Override
    public void salvarSemCommit(final T model) {
        if (model != null) {
            if (model.getId() == null) {
                em.persist(model);
            } else {
                em.merge(model);
            }
        }
    }

}