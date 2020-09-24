package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Funcionario;
import br.gov.sp.fatec.projetomaven.entity.Gerente;
import br.gov.sp.fatec.projetomaven.entity.Tarefa;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

public class TarefaDaoJpa implements TarefaDao{

    private EntityManager em;

    public TarefaDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public TarefaDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Tarefa cadastraTarefa(Long id, String titulo, String descricao, Date dataHoraCriacao, Date dataHoraEntrega, Gerente gerente){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setDataHoraCriacao(dataHoraCriacao);
        tarefa.setDataHoraEntrega(dataHoraEntrega);
        tarefa.setGerente(gerente);

        return salvarTarefa(tarefa);
    }

    @Override
    public Tarefa salvarTarefa(Tarefa tarefa){
        try{
            em.getTransaction().begin();
            if(tarefa.getId() == null){
                em.persist(tarefa);
            }
            else {
                em.merge(tarefa);
            }
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        return tarefa;
    }

    @Override
    public Tarefa buscarTarefa(Long id){return em.find(Tarefa.class,id);}

    @Override
    public Tarefa buscarTarefaPorNome(String nomeTarefa){
        String jpql = "select t from Tarefa t where t.titulo = :nomeTarefa";
        TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("nomeTarefa", nomeTarefa);
        return query.getSingleResult();
    }

    @Override
    public List<Tarefa> buscarTarefasPorFuncionario(Long id){
        String jpql = "select t from Tarefa t inner join t.funcionarios  f on f.id = :id";
        TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Tarefa> buscarTarefasPorGerente(Long id){
        String jpql = "select t from Tarefa t inner join t.gerente  g on g.id = :id";
        TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void removerTarefa(String nomeTarefa){
        Tarefa tarefa = buscarTarefaPorNome(nomeTarefa);
        if (tarefa == null)
            return;
        try {
            em.getTransaction().begin();
            em.remove(tarefa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void removerTarefa(Long id){
        Tarefa tarefa = buscarTarefa(id);
        if (tarefa == null)
            return;
        try {
            em.getTransaction().begin();
            em.remove(tarefa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}
