package io.keede.travely.core.domains.reservation.entity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
* @author keede
* Created on 2023/11/25
*/
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @EntityGraph(attributePaths = {"lodging", "user"})
    @Query(value =
            "SELECT reservation " +
            "FROM Reservation reservation " +
            "WHERE reservation.user.id = :userId"
    )
    List<Reservation> findMyReservations(@Param("reservationId") Long userId);

    @EntityGraph(attributePaths = {"lodging", "user"})
    @Query(value =
            "SELECT reservation " +
            "FROM Reservation reservation " +
            "WHERE reservation.id = :reservationId "
    )
    Optional<Reservation> findMyReservation(@Param("reservationId") Long reservationId);

}
