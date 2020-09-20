package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Gerente;

public interface GerenteDao {
    public Gerente cadastrarGerente(String nome, String nomeUsuario, String senha);

    public Gerente salvarGerente(Gerente Gerente);

    public Gerente buscarGerentePorId(Long id);

    public Gerente buscarGerentePorNomeUsuario(String nomeUsuario);

    public void removerGerente(String nomeUsuario);

    public void removerGerente(Long idGerente);
}