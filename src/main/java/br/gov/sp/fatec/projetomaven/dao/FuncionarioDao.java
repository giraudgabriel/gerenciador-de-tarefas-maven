package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Funcionario;

public interface FuncionarioDao {
    public Funcionario cadastrarFuncionario(String nome, String nomeUsuario, String senha);

    public Funcionario salvarFuncionario(Funcionario funcionario);

    public Funcionario buscarFuncionarioPorId(Long id);

    public Funcionario buscarFuncionarioPorNomeUsuario(String nomeUsuario);

    public void removerFuncionario(String nomeUsuario);

    public void removerFuncionario(Long idFuncionario);
}