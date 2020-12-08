package br.gov.sp.fatec.projetomaven.dao;

import java.util.List;

import br.gov.sp.fatec.projetomaven.entity.Desenvolvedor;

public interface DesenvolvedorDao extends IEntityDao<Desenvolvedor>{
    public List<Desenvolvedor> buscarTodos();
    public Desenvolvedor login(String nomeUsuario, String senha);
}