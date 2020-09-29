package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Tarefa;
import br.gov.sp.fatec.projetomaven.entity.Desenvolvedor;

import java.util.List;

public interface TarefaDao extends IEntityDao<Tarefa> {

    public Tarefa adicionarDesenvolvedor(Desenvolvedor desenvolvedor, Tarefa tarefa);

    public Tarefa buscarTarefaPorNome(String nomeTarefa);

    public List<Tarefa> buscarTarefasPorDesenvolvedor(String nomeUsuario);

    public List<Tarefa> buscarTarefasPorDesenvolvedor(Long id);

    public List<Tarefa> buscarTarefasPorGerente(Long id);

    public List<Tarefa> buscarTarefasPorGerente(String nomeUsuario);

    public List<Tarefa> buscarTarefasPorFiltro(String filtro);

    List<Tarefa> buscarTarefasPorFiltro(final String filtro, String nomeUsuarioFuncionario);

    public void removerTarefa(String nomeTarefa);

}
