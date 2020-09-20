package br.gov.sp.fatec.projetomaven;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.projetomaven.dao.FuncionarioDao;
import br.gov.sp.fatec.projetomaven.dao.FuncionarioDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Funcionario;
import br.gov.sp.fatec.projetomaven.entity.Gerente;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;


public class App {
    public static void main(String[] args) {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();

        //funcionario
        FuncionarioDao funcionarioDao = new FuncionarioDaoJpa(manager);
        funcionarioDao.cadastrarFuncionario("Gabriel", "gabriel2", "1234");
        Funcionario funcionario = funcionarioDao.buscarFuncionarioPorNomeUsuario("gabriel2");
        funcionario.setNome("Gabriel Fernandes Giraud");
        funcionarioDao.salvarFuncionario(funcionario);
        funcionarioDao.removerFuncionario(funcionario.getId());

        //gerente
        Gerente gerente = new Gerente();
        gerente.setNome("Eu");
        gerente.setNomeUsuario("elgerente");
        gerente.setSenha("senha");

        //tarefas

        manager.clear();
        manager.close();
    }
}
