package com.luisguilherme.motel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mt04_entrada_consumo")
public class EntradaConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt04_codigo_entrada_consumo")
    private Long id;
    @Column(name = "mt04_total")
    private Float total;

    @ManyToOne
    @JoinColumn(name = "fkmt04mt03_codigo_itens")
    private Itens itens;
    @Column(name = "mt04_quantidade")
    private Integer quantidade;
    @OneToOne
    @JoinColumn(name = "fkmt04mt01_codigo_entradas")
    private Entradas entradas;
}
