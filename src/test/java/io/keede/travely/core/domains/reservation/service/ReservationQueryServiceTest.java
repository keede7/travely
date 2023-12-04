package io.keede.travely.core.domains.reservation.service;

import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.entity.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

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

        given(this.reservationRepository.findById(anyLong()))
                .willReturn(Optional.of(mock(Reservation.class)));

        this.sut.getMyReservation(myReservation);

        then(this.reservationRepository).should(times(1))
                .findById(anyLong());

    }
}
