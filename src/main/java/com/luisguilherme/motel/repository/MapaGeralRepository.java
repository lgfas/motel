package com.luisguilherme.motel.repository;

import com.luisguilherme.motel.model.MapaGeral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MapaGeralRepository extends JpaRepository<MapaGeral,Long> {

    @Query("SELECT m.total FROM MapaGeral m WHERE m.total IS NOT NULL ORDER BY m.id DESC LIMIT 1")
    Float calcularTotal();
}
