package io.keede.travely.core.domains.lodging.entity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author keede
* Created on 2023/10/21
*/
public interface LodgingRepository extends JpaRepository<Lodging, Long> {

    @EntityGraph(attributePaths = { "reservations" }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT lodging " +
            "FROM Lodging lodging ")
    List<Lodging> findLodgingAll();
}
