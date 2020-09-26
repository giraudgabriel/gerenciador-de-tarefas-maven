package br.gov.sp.fatec.projetomaven;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;


import br.gov.sp.fatec.projetomaven.dao.FuncionarioDao;
import br.gov.sp.fatec.projetomaven.dao.FuncionarioDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.GerenteDao;
import br.gov.sp.fatec.projetomaven.dao.GerenteDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.TarefaDao;
import br.gov.sp.fatec.projetomaven.dao.TarefaDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Funcionario;
import br.gov.sp.fatec.projetomaven.entity.Gerente;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;
import br.gov.sp.fatec.projetomaven.entity.Tarefa;

public class App {
    public static void main(String[] args) {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();

        // funcionario
        FuncionarioDao funcionarioDao = new FuncionarioDaoJpa(manager);
        funcionarioDao.cadastrarFuncionario("Gabriel", "gabriel2", "1234");
        Funcionario funcionario = funcionarioDao.buscarFuncionarioPorNomeUsuario("gabriel2");
        funcionario.setNome("Gabriel Fernandes Giraud");
        funcionarioDao.salvarFuncionario(funcionario);
        //funcionarioDao.removerFuncionario(funcionario.getId());
        

        // gerente
        GerenteDao gerenteDao = new GerenteDaoJpa(manager);
        Gerente gerente = new Gerente();
        gerente.setNome("Eu");
        gerente.setNomeUsuario("elgerente");
        gerente.setSenha("senha");
        gerente = gerenteDao.cadastrarGerente("Felipe Nicoletti", "nicoletti", "tcc");

        // tarefas
        TarefaDao tarefaDao = new TarefaDaoJpa(manager);
        Tarefa tarefa = new Tarefa();
        tarefa.setDataHoraCriacao(new Date());
        tarefa.setDescricao("Trabalho do Mineda");
        tarefa.setTitulo("Software FATEC");
        tarefa.setGerente(gerente);
        tarefa.setFuncionarios(null);
        tarefa = tarefaDao.cadastrarTarefa(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataHoraCriacao(), tarefa.getGerente());

        //funcionario tarefa
        tarefa = tarefaDao.adicionarFuncionarioATarefa(funcionario, tarefa);
        System.out.println(tarefa.getTitulo());
        List<Tarefa> tarefas = tarefaDao.buscarTarefasPorFuncionario(funcionario.getId());
        List<Tarefa> tarefasFiltroNome = tarefaDao.filtroNomeTarefa(funcionario.getId(), tarefa.getTitulo());

        for (Tarefa tar : tarefas) {
            System.out.println(tar.getTitulo());
        }

        for (Tarefa tar : tarefasFiltroNome) {
            System.out.println(tar.getTitulo());
        }
       
    }
}
