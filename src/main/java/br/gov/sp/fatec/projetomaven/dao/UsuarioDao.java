package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.Usuario;

public interface UsuarioDao extends IEntityDao<Usuario> {
    public Usuario buscarPorNomeUsuarioESenha(String nomeUsuario, String senha);
}