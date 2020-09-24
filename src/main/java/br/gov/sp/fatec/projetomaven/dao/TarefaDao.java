package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Tarefa;
import br.gov.sp.fatec.projetomaven.entity.Gerente;
import br.gov.sp.fatec.projetomaven.entity.Funcionario;

import java.util.Date;
import java.util.List;

public interface TarefaDao {

    public Tarefa cadastraTarefa(Long id, String titulo, String descricao, Date dataHoraCriacao, Date dataHoraEntrega,Gerente gerente);

    public Tarefa salvarTarefa(Tarefa tarefa);

    public Tarefa buscarTarefa(Long id);

    public Tarefa buscarTarefaPorNome(String nomeTarefa);

    public List<Tarefa> buscarTarefasPorFuncionario(Long id);

    public List<Tarefa> buscarTarefasPorGerente(Long id);

    public void removerTarefa(String nomeTarefa);

    public void removerTarefa(Long id);

}
