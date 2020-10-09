package br.gov.sp.fatec.projetomaven.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "tarefa")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "tar_id"))
public class Tarefa extends IEntity{
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_hora_criacao")
    private Date dataHoraCriacao;

    @Column(name = "data_hora_entrega")
    private Date dataHoraEntrega;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gerente_id")
    private Gerente gerente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "desenvolvedor_tarefa", joinColumns = { @JoinColumn(name = "tar_id") }, inverseJoinColumns = {
            @JoinColumn(name = "dev_id") })
    private Set<Desenvolvedor> desenvolvedores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataHoraEntrega() {
        return dataHoraEntrega;
    }

    public void setDataHoraEntrega(Date dataHoraEntrega) {
        this.dataHoraEntrega = dataHoraEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Set<Desenvolvedor> getDesenvolvedores() {
        return desenvolvedores;
    }

    public void setDesenvolvedores(Set<Desenvolvedor> desenvolvedores) {
        this.desenvolvedores = desenvolvedores;
    }
}