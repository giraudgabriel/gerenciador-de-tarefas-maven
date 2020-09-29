package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Desenvolvedor;
import br.gov.sp.fatec.projetomaven.entity.Tarefa;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

public class TarefaDaoJpa extends IEntityDaoJpa<Tarefa> implements TarefaDao {

    private final EntityManager em;

    public TarefaDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public TarefaDaoJpa(final EntityManager em) {
        super(Tarefa.class, em);
        this.em = em;
    }

    @Override
    public Tarefa buscarTarefaPorNome(final String nomeTarefa) {
        final String jpql = "select t from Tarefa t where t.titulo = :nomeTarefa";
        final TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("nomeTarefa", nomeTarefa);
        return query.getSingleResult();
    }

    @Override
    public List<Tarefa> buscarTarefasPorDesenvolvedor(final Long id) {
        final String jpql = "select t from Tarefa t inner join t.desenvolvedores dev on dev.id = :id";
        final TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Tarefa> buscarTarefasPorDesenvolvedor(final String nomeUsuario) {
        final String jpql = "select t from Tarefa t inner join t.desenvolvedores dev on dev.nomeUsuario = :nomeUsuario";
        final TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("nomeUsuario", nomeUsuario);
        return query.getResultList();
    }

    @Override
    public List<Tarefa> buscarTarefasPorGerente(final Long id) {
        final String jpql = "select t from Tarefa t inner join t.gerente  g on g.id = :id";
        final TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void removerTarefa(final String nomeTarefa) {
        final Tarefa tarefa = buscarTarefaPorNome(nomeTarefa);
        if (tarefa == null)
            return;
        try {
            em.getTransaction().begin();
            em.remove(tarefa);
            em.getTransaction().commit();
        } catch (final Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public Tarefa adicionarDesenvolvedor(final Desenvolvedor desenvolvedor, final Tarefa tarefa) {
        if (tarefa.getDesenvolvedores() == null) {
            tarefa.setDesenvolvedores(new HashSet<Desenvolvedor>());
        }
        if (desenvolvedor != null) {
            tarefa.getDesenvolvedores().add(desenvolvedor);
            return salvar(tarefa);
        }
        return null;
    }

    @Override
    public List<Tarefa> buscarTarefasPorGerente(final String nomeUsuario) {
        final String jpql = "select t from Tarefa t inner join t.gerente  g on g.nomeUsuario = :nomeUsuario";
        final TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("nomeUsuario", nomeUsuario);
        return query.getResultList();
    }

    @Override
    public List<Tarefa> buscarTarefasPorFiltro(final String filtro) {
        final String jpql = "select t from Tarefa t where t.titulo like :filtro or t.descricao like :filtro";
        final TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("filtro", filtro);
        return query.getResultList();
    }

    @Override
    public List<Tarefa> buscarTarefasPorFiltro(final String filtro, String nomeUsuarioFuncionario) {
        final String jpql = "select t from Tarefa t inner join t.desenvolvedores dev on dev.nomeUsuario = :nomeUsuarioFuncionario where t.titulo like :filtro or t.descricao like :filtro ";
        final TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("nomeUsuarioFuncionario", nomeUsuarioFuncionario);
        query.setParameter("filtro", filtro);
        return query.getResultList();
    }
}
