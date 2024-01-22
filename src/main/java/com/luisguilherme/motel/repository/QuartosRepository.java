package com.luisguilherme.motel.repository;

import com.luisguilherme.motel.model.Quartos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartosRepository extends JpaRepository<Quartos, Long> {

}
