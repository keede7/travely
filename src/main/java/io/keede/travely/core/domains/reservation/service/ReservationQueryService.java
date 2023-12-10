package io.keede.travely.core.domains.reservation.service;


import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.entity.ReservationRepository;
import io.keede.travely.core.exception.service.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kyh
 * Created on 2023/12/04
 */
@Service
public class ReservationQueryService {

    private final ReservationRepository reservationRepository;

    public ReservationQueryService(final ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional(readOnly = true)
    public Reservation getMyReservation(ReservationDto.MyReservation myReservationDto) {
        Long reservationId = myReservationDto.reservationId();

        Reservation reservation = this.reservationRepository.findMyReservation(reservationId)
                .orElseThrow(BusinessException::new);

        return reservation;
    }

    @Transactional(readOnly = true)
    public List<Reservation> getMyReservations(ReservationDto.MyReservations myReservationsDto) {
        Long userId = myReservationsDto.userId();

        List<Reservation> reservations = this.reservationRepository.findMyReservations(userId);

        return reservations;
    }
}
