package br.gov.sp.fatec.projetomaven.dao;

import br.gov.sp.fatec.projetomaven.entity.IEntity;

public interface IEntityDao<T extends IEntity> {
    public T salvar(T model);
    public void salvarSemCommit(T model);
    public T cadastrar(T model);
    public T buscarPorId(Long id);
    public void excluir(T model);
    public void excluir(Long id);
}