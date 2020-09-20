package br.gov.sp.fatec.projetomaven.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "tar_tarefa")
@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tar_id")
    private Long id;

    @Column(name = "tar_titulo")
    private String titulo;

    @Column(name = "tar_descricao")
    private String descricao;

    @Column(name = "tar_data_hora_criacao")
    private Date dataHoraCriacao;

    @Column(name = "tar_data_hora_entrega")
    private Date dataHoraEntrega;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tar_gerente_id")
    private Gerente gerente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "fut_funcionario_tarefa", joinColumns = { @JoinColumn(name = "tar_id") }, inverseJoinColumns = {
            @JoinColumn(name = "fun_id") })
    private Set<Funcionario> funcionarios;

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

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}