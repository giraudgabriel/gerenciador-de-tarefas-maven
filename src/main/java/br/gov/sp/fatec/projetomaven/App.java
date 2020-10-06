package br.gov.sp.fatec.projetomaven;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import br.gov.sp.fatec.projetomaven.dao.DesenvolvedorDao;
import br.gov.sp.fatec.projetomaven.dao.DesenvolvedorDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.GerenteDao;
import br.gov.sp.fatec.projetomaven.dao.GerenteDaoJpa;
import br.gov.sp.fatec.projetomaven.dao.TarefaDao;
import br.gov.sp.fatec.projetomaven.dao.TarefaDaoJpa;
import br.gov.sp.fatec.projetomaven.entity.Desenvolvedor;
import br.gov.sp.fatec.projetomaven.entity.DesenvolvedorFrontend;
import br.gov.sp.fatec.projetomaven.entity.Gerente;
import br.gov.sp.fatec.projetomaven.entity.PersistenceManager;
import br.gov.sp.fatec.projetomaven.entity.Tarefa;

public class App {
    public static void main(String[] args) {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
        // desenvolvedor
        DesenvolvedorDao desenvolvedorDao = new DesenvolvedorDaoJpa();
        DesenvolvedorFrontend desenvolvedor = new DesenvolvedorFrontend();
        desenvolvedor.setNome("Gabriel");
        desenvolvedor.setNomeUsuario("gabriel");
        desenvolvedor.setSenha("senha");
        desenvolvedorDao.salvar(desenvolvedor);

        // gerente
        GerenteDao gerenteDao = new GerenteDaoJpa(manager);
        Gerente gerente = new Gerente();
        gerente.setNome("Eu");
        gerente.setNomeUsuario("elgerente");
        gerente.setSenha("senha");
        gerente = gerenteDao.cadastrar(gerente);

        //tarefas
        TarefaDao tarefaDao = new TarefaDaoJpa(manager);
        Tarefa tarefa = new Tarefa();
        tarefa.setDataHoraCriacao(new Date());
        tarefa.setDescricao("Trabalho do Mineda");
        tarefa.setTitulo("Software FATEC");
        tarefa.setGerente(gerente);
        tarefa.setDesenvolvedores(new HashSet<Desenvolvedor>());
        tarefa.getDesenvolvedores().add(desenvolvedor);
        tarefa = tarefaDao.salvar(tarefa);

        // funcionario tarefa
        // tarefa = tarefaDao.adicionarDesenvolvedor(desenvolvedor, tarefa);

        System.out.println(tarefa.getTitulo());
        List<Tarefa> tarefas = tarefaDao.buscarTarefasPorDesenvolvedor(desenvolvedor.getId());

        List<Tarefa> tarefasFiltroNome = tarefaDao.buscarTarefasPorFiltro("Software");

        for (Tarefa tar : tarefas) {
            System.out.println(tar.getTitulo());
        }

        for (Tarefa tar : tarefasFiltroNome) {
            System.out.println(tar.getTitulo());
        }

        tarefasFiltroNome = tarefaDao.buscarTarefasPorFiltro("Software", desenvolvedor.getNomeUsuario());

        for (Tarefa tar : tarefasFiltroNome) {
            System.out.println(tar.getTitulo());
        }

        manager.close();
    }
}
