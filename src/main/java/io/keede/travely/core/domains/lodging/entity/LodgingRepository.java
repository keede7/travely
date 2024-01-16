package io.keede.travely.core.domains.lodging.entity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
* @author keede
* Created on 2023/10/21
*/
public interface LodgingRepository extends JpaRepository<Lodging, Long> {

    @EntityGraph(attributePaths = { "reservations" }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT lodging " +
            "FROM Lodging lodging ")
    List<Lodging> findLodgingAll();

    @EntityGraph(attributePaths = { "reservations" }, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT lodging " +
            "FROM Lodging lodging " +
            "WHERE lodging.id = :id ")
    Optional<Lodging> findLodgingById(@Param("id") Long lodgingId);
}
