package com.luisguilherme.motel.model;

import com.luisguilherme.motel.Enum.StatusDoQuarto;
import jakarta.persistence.*;

@Entity
@Table(name = "mt02_quartos")
public class Quartos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt02_codigo_quartos")
    private Long id;
    @Column(name = "mt02_numero")
    private Integer numero;
    @Column(name = "mt02_descricao")
    private String descricao;
    @Column(name = "mt02_capacidade_pessoa")
    private Integer capacidadePessoa;
    @Column(name = "mt02_status_do_quarto")
    private StatusDoQuarto statusDoQuarto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCapacidadePessoa() {
        return capacidadePessoa;
    }

    public void setCapacidadePessoa(Integer capacidadePessoa) {
        this.capacidadePessoa = capacidadePessoa;
    }

    public StatusDoQuarto getStatusDoQuarto() {
        return statusDoQuarto;
    }

    public void setStatusDoQuarto(StatusDoQuarto statusDoQuarto) {
        this.statusDoQuarto = statusDoQuarto;
    }
}
