package io.keede.travely.core.domains.reservation.service;

import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.entity.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

/**
* @author keede
* Created on 2023/12/4
*/
@BusinessMockTestConfiguration
class ReservationQueryServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    private ReservationQueryService sut;

    @BeforeEach
    void setUp() {
        this.sut = new ReservationQueryService(
                this.reservationRepository
        );
    }

    @Test
    void 내_예약_조회_성공() {

        final ReservationDto.MyReservation myReservation = mock(ReservationDto.MyReservation.class);

        given(this.reservationRepository.findMyReservation(anyLong()))
                .willReturn(Optional.of(mock(Reservation.class)));

        this.sut.getMyReservation(myReservation);

        then(this.reservationRepository).should(times(1))
                .findMyReservation(anyLong());

    }

    @Test
    void 내_모든_예약_조회_성공() {

        final ReservationDto.MyReservations myReservations = mock(ReservationDto.MyReservations.class);

        given(this.reservationRepository.findMyReservations(anyLong()))
                .willReturn(mock(List.class));

        this.sut.getMyReservations(myReservations);

        then(this.reservationRepository).should(times(1))
                .findMyReservations(anyLong());

    }
}
