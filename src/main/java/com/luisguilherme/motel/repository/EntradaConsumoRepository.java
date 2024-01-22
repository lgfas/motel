package com.luisguilherme.motel.repository;

import com.luisguilherme.motel.model.EntradaConsumo;
import com.luisguilherme.motel.model.Entradas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaConsumoRepository extends JpaRepository<EntradaConsumo, Long> {

    List<EntradaConsumo> findAllByEntradas(Entradas entradas);

}
