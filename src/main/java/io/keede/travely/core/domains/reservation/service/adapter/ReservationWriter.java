package io.keede.travely.core.domains.reservation.service.adapter;


import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.entity.ReservationRepository;
import org.springframework.stereotype.Service;

/**
* @author keede
* Created on 2024/01/13
*/
@Service
public class ReservationWriter {

    private final ReservationRepository reservationRepository;

    public ReservationWriter(
            final ReservationRepository reservationRepository
    ) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation save(Reservation entity) {
        return this.reservationRepository.save(entity);
    }

}
