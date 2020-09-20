package br.gov.sp.fatec.projetomaven;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.projetomaven.dao.FuncionarioDao;
import br.gov.sp.fatec.projetomaven.dao.FuncionarioDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Funcionario;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();

        FuncionarioDao funcionarioDao = new FuncionarioDaoJpa(manager);

        funcionarioDao.cadastrarFuncionario("Gabriel", "gabriel", "1234");
        Funcionario funcionario = funcionarioDao.buscarFuncionarioPorNomeUsuario("gabriel");
        funcionario.setNome("Gabriel Fernandes Giraud");
        funcionarioDao.salvarFuncionario(funcionario);

        manager.clear();
        manager.close();
    }
}
