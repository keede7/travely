package io.keede.travely.core.domains.reservation.service;

import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.service.adapter.ReservationReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.BDDMockito.*;

/**
 * @author keede
 * Created on 2023/12/4
 */
@BusinessMockTestConfiguration
class ReservationQueryServiceTest {

    @Mock
    private ReservationReader reservationReader;

    private ReservationQueryService sut;

    @BeforeEach
    void setUp() {
        this.sut = new ReservationQueryService(
                this.reservationReader
        );
    }

    @Test
    void 내_예약_조회_성공() {

        final ReservationDto.MyReservation myReservationDto = mock(ReservationDto.MyReservation.class);

        Reservation reservation = mock(Reservation.class);

        given(this.reservationReader.findMyReservation(myReservationDto))
                .willReturn(reservation);

        Reservation myReservation = this.sut.getMyReservation(myReservationDto);

        then(this.reservationReader).should(times(1))
                .findMyReservation(myReservationDto);

    }

    @Test
    void 내_모든_예약_조회_성공() {

        final ReservationDto.MyReservations myReservationsDto = mock(ReservationDto.MyReservations.class);

        given(this.reservationReader.getMyReservations(myReservationsDto))
                .willReturn(mock(List.class));

        List<Reservation> reservations = this.sut.getMyReservations(myReservationsDto);

        then(this.reservationReader).should(times(1))
                .getMyReservations(myReservationsDto);

    }
}
