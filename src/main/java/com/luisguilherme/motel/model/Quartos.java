package com.luisguilherme.motel.model;

import com.luisguilherme.motel.enums.StatusDoQuarto;
import jakarta.persistence.*;

@Entity
@Table(name = "mt02_quartos")
public class Quartos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt02_codigo_quartos")
    private Long id;
    @Column(name = "mt02_numero")
    private Long numero;
    @Column(name = "mt02_descricao")
    private String descricao;
    @Column(name = "mt02_capacidade_pessoa")
    private Long capacidadePessoa;
    @Enumerated(EnumType.STRING)
    @Column(name = "mt02_status_do_quarto")
    private StatusDoQuarto statusDoQuarto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCapacidadePessoa() {
        return capacidadePessoa;
    }

    public void setCapacidadePessoa(Long capacidadePessoa) {
        this.capacidadePessoa = capacidadePessoa;
    }

    public StatusDoQuarto getStatusDoQuarto() {
        return statusDoQuarto;
    }

    public void setStatusDoQuarto(StatusDoQuarto statusDoQuarto) {
        this.statusDoQuarto = statusDoQuarto;
    }

    public Quartos(Long id, Long numero, String descricao, Long capacidadePessoa, StatusDoQuarto statusDoQuarto) {
        this.id = id;
        this.numero = numero;
        this.descricao = descricao;
        this.capacidadePessoa = capacidadePessoa;
        this.statusDoQuarto = statusDoQuarto;
    }

    public Quartos(Long numero, String descricao, Long capacidadePessoa, StatusDoQuarto statusDoQuarto) {
        this.numero = numero;
        this.descricao = descricao;
        this.capacidadePessoa = capacidadePessoa;
        this.statusDoQuarto = statusDoQuarto;
    }

    public Quartos() {
    }
}
