package com.luisguilherme.motel.repository;

import com.luisguilherme.motel.enums.StatusEntrada;
import com.luisguilherme.motel.model.Entradas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<Entradas, Long> {
    List<Entradas> findAllByStatusEntrada (StatusEntrada statusEntrada);

    List<Entradas> findAllByDataRegistroEntrada (LocalDate dataRegistroEntrada);

}
