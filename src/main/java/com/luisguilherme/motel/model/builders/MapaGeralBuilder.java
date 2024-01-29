package com.luisguilherme.motel.model.builders;

import com.luisguilherme.motel.model.MapaGeral;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalTime;

public class MapaGeralBuilder {

    private Integer apartment;
    private Float entrada;
    private String report;
    private Float saida;
    private Float total;
    private LocalTime hora;
    private LocalDate data;

    public MapaGeralBuilder apartment(Integer apartment) {
        this.apartment = apartment;
        return this;
    }

    public MapaGeralBuilder entrada(Float entrada) {
        this.entrada = entrada;
        return this;
    }

    public MapaGeralBuilder report(String report) {
        this.report = report;
        return this;
    }

    public MapaGeralBuilder saida(Float saida) {
        this.saida = saida;
        return this;
    }

    public MapaGeralBuilder total(Float total) {
        this.total = total;
        return this;
    }

    public MapaGeralBuilder hora(LocalTime hora) {
        this.hora = hora;
        return this;
    }

    public MapaGeralBuilder data(LocalDate data) {
        this.data = data;
        return this;
    }

    public MapaGeral build() {
        MapaGeral mapaGeral = new MapaGeral();

        mapaGeral.setApartment(apartment);
        mapaGeral.setEntrada(entrada);
        mapaGeral.setReport(report);
        mapaGeral.setSaida(saida);
        mapaGeral.setTotal(total);
        mapaGeral.setHora(hora);
        mapaGeral.setData(data);

        return mapaGeral;
    }

}
