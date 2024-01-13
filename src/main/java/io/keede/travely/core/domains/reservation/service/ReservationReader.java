package io.keede.travely.core.domains.reservation.service;


import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.entity.ReservationRepository;
import io.keede.travely.core.exception.service.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author keede
* Created on 2024/01/13
*/
@Service
@Transactional(readOnly = true)
public class ReservationReader {

    private final ReservationRepository reservationRepository;

    public ReservationReader(
            final ReservationRepository reservationRepository
    ) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getMyReservations(ReservationDto.MyReservations myReservationsDto) {
        Long userId = myReservationsDto.userId();

        return this.reservationRepository.findMyReservations(userId);
    }

    public Reservation getMyReservation(ReservationDto.MyReservation myReservationDto) {
        Long reservationId = myReservationDto.reservationId();

        return this.reservationRepository.findMyReservation(reservationId)
                .orElseThrow(BusinessException::new);
    }

    public Reservation getMyReservation(Long reservationId) {
        return this.reservationRepository.findMyReservation(reservationId)
                .orElseThrow(BusinessException::new);
    }
}
