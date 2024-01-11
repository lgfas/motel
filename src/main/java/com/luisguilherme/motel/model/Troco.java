package com.luisguilherme.motel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mt05_troco")
public class Troco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt05_codigo_troco")
    private Long id;
    @Column(name = "mt05_valor_entrada")
    private Float valorEntrada;
    @Column(name = "mt05_troco")
    private Float troco;
    @OneToOne
    @JoinColumn(name = "fkmt05mt01_codigo_entradas")
    private Entradas entradas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(Float valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public Float getTroco() {
        return troco;
    }

    public void setTroco(Float troco) {
        this.troco = troco;
    }

    public Entradas getEntradas() {
        return entradas;
    }

    public void setEntradas(Entradas entradas) {
        this.entradas = entradas;
    }
}
