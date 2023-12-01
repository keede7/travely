package io.keede.travely.core.domains.reservation.service;

import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.entity.ReservationRepository;
import io.keede.travely.core.domains.user.entity.User;
import io.keede.travely.core.domains.user.entity.UserRepository;
import io.keede.travely.core.exception.service.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

/**
* @author keede
* Created on 2023/11/25
*/
@BusinessMockTestConfiguration
class ReservationCommandServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private LodgingRepository lodgingRepository;
    @Mock
    private UserRepository userRepository;

    private ReservationCommandService sut;

    @BeforeEach
    void setUp() {
        this.sut = new ReservationCommandService(
                this.reservationRepository,
                this.lodgingRepository,
                this.userRepository
        );
    }

    @Test
    void 예약_생성_성공() {

        final Lodging lodging = mock(Lodging.class);
        final User user = mock(User.class);
        final Reservation reservation = mock(Reservation.class);

        final ReservationDto.Create create = mock(ReservationDto.Create.class);

        given(this.lodgingRepository.findById(anyLong()))
                .willReturn(Optional.of(lodging));
        given(this.userRepository.findById(anyLong()))
                .willReturn(Optional.of(user));
        given(this.reservationRepository.save(any(Reservation.class)))
                .willReturn(reservation);

        this.sut.create(create);

        then(this.lodgingRepository).should(times(1))
                .findById(anyLong());
        then(this.userRepository).should(times(1))
                .findById(anyLong());
        then(this.reservationRepository).should(times(1))
                .save(any(Reservation.class));

    }

    @Test
    void 인원_초과로_예약_실패() {

        final Lodging lodging = mock(Lodging.class);

        final ReservationDto.Create create = mock(ReservationDto.Create.class);

        given(this.lodgingRepository.findById(anyLong()))
                .willReturn(Optional.of(lodging));

        willThrow(BusinessException.class).given(lodging)
                .checkToAllowReservation();

        assertThrows(
                BusinessException.class,
                () -> this.sut.create(create)
        );

    }

}