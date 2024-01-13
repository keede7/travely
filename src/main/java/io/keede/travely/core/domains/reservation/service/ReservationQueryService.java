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

    private final ReservationReader reservationReader;

    public ReservationQueryService(
            final ReservationReader reservationReader
    ) {
        this.reservationReader = reservationReader;
    }

    @Transactional(readOnly = true)
    public Reservation getMyReservation(ReservationDto.MyReservation myReservationDto) {
        return this.reservationReader.getMyReservation(myReservationDto);
    }

    @Transactional(readOnly = true)
    public List<Reservation> getMyReservations(ReservationDto.MyReservations myReservationsDto) {
        return this.reservationReader.getMyReservations(myReservationsDto);
    }
}
