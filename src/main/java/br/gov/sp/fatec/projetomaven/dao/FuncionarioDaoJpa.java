package br.gov.sp.fatec.projetomaven.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.sp.fatec.projetomaven.entity.Funcionario;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

public class FuncionarioDaoJpa implements FuncionarioDao {

    private EntityManager em;

    public FuncionarioDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public FuncionarioDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Funcionario cadastrarFuncionario(String nome, String nomeUsuario, String senha) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setNomeUsuario(nomeUsuario);
        funcionario.setSenha(senha);
        return salvarFuncionario(funcionario);
    }

    @Override
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        try {
            em.getTransaction().begin();
            if (funcionario.getId() == null) {
                em.persist(funcionario);
            } else {
                em.merge(funcionario);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return funcionario;
    }

    @Override
    public Funcionario buscarFuncionarioPorId(Long id) {
        return em.find(Funcionario.class, id);
    }

    @Override
    public Funcionario buscarFuncionarioPorNomeUsuario(String nomeUsuario) {
        String jpql = "select f from Funcionario f where f.nomeUsuario = :nomeUsuario";
        TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
        return query.getSingleResult();
    }

    @Override
    public void removerFuncionario(String nomeUsuario) {
        Funcionario funcionario = buscarFuncionarioPorNomeUsuario(nomeUsuario);
        if (funcionario == null)
            return;
        try {
            em.getTransaction().begin();
            em.remove(funcionario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void removerFuncionario(Long idFuncionario) {
        Funcionario funcionario = buscarFuncionarioPorId(idFuncionario);
        if (funcionario == null)
            return;
        try {
            em.getTransaction().begin();
            em.remove(funcionario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

}