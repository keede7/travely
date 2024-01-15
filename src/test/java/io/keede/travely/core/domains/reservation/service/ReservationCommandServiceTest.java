package io.keede.travely.core.domains.reservation.service;

import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.service.adapter.LodgingReader;
import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.service.adapter.ReservationReader;
import io.keede.travely.core.domains.reservation.service.adapter.ReservationWriter;
import io.keede.travely.core.domains.user.entity.User;
import io.keede.travely.core.domains.user.service.adapter.UserReader;
import io.keede.travely.core.exception.service.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.context.ApplicationEventPublisher;

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
    private ReservationWriter reservationWriter;

    @Mock
    private ReservationReader reservationReader;

    @Mock
    private LodgingReader lodgingReader;

    @Mock
    private UserReader userReader;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    private ReservationCommandService sut;

    @BeforeEach
    void setUp() {
        this.sut = new ReservationCommandService(
                this.reservationWriter,
                this.reservationReader,
                this.lodgingReader,
                this.userReader,
                this.applicationEventPublisher
        );
    }

    @Test
    void 예약_생성_성공() {

        final Lodging lodging = mock(Lodging.class);
        final User user = mock(User.class);
        final Reservation reservation = mock(Reservation.class);

        final ReservationDto.Create create = mock(ReservationDto.Create.class);

        given(this.lodgingReader.findById(anyLong()))
                .willReturn(lodging);
        given(this.userReader.findById(anyLong()))
                .willReturn(user);
        given(this.reservationWriter.save(any(Reservation.class)))
                .willReturn(reservation);

        this.sut.create(create);

        then(this.lodgingReader).should(times(1))
                .findById(anyLong());
        then(this.userReader).should(times(1))
                .findById(anyLong());
        then(this.reservationWriter).should(times(1))
                .save(any(Reservation.class));

    }

    @Test
    void 인원_초과로_예약_실패() {

        final Lodging lodging = mock(Lodging.class);

        final ReservationDto.Create create = mock(ReservationDto.Create.class);

        given(this.lodgingReader.findById(anyLong()))
                .willReturn(lodging);

        willThrow(BusinessException.class).given(lodging)
                .checkToAllowReservation();

        assertThrows(
                BusinessException.class,
                () -> this.sut.create(create)
        );

    }

    @Test
    void 예약_취소_성공() {

        final Reservation reservation = mock(Reservation.class);

        ReservationDto.Cancel cancel = mock(ReservationDto.Cancel.class);

        given(this.reservationReader.findMyReservation(anyLong()))
                .willReturn(reservation);

        willCallRealMethod().given(reservation)
                .cancel();

        this.sut.cancel(cancel);

        then(this.reservationReader).should(times(1))
                .findMyReservation(anyLong());

        then(reservation).should(times(1))
                .cancel();
    }

}