package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Gerente;

public interface GerenteDao extends IEntityDao<Gerente> {
    public Gerente buscarGerentePorNomeUsuario(String nomeUsuario);
}