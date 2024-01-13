package io.keede.travely.core.domains.reservation.service;

import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.service.LodgingReader;
import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.user.entity.User;
import io.keede.travely.core.domains.user.service.UserReader;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author keede
* Created on 2023/11/25
*/
@Service
public class ReservationCommandService {

    private final ReservationWriter reservationWriter;
    private final ReservationReader reservationReader;
    private final LodgingReader lodgingReader;
    private final UserReader userReader;

    private final ApplicationEventPublisher applicationEventPublisher;

    public ReservationCommandService(
            final ReservationWriter reservationWriter,
            final ReservationReader reservationReader,
            final LodgingReader lodgingReader,
            final UserReader userReader,
            final ApplicationEventPublisher applicationEventPublisher
    ) {
        this.reservationWriter = reservationWriter;
        this.reservationReader = reservationReader;
        this.lodgingReader = lodgingReader;
        this.userReader = userReader;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void create(final ReservationDto.Create create) {
        Lodging lodging = this.lodgingReader.findById(create.lodgingId());

        lodging.checkToAllowReservation();

        User user = this.userReader.findById(create.userId());

        Payment payment = create.toPayment();

        Reservation reservation = new Reservation(lodging, user, payment);

        Reservation savedReservation = this.reservationWriter.save(reservation);

        // TODO : 이벤트 처리부에서 예약 등록을 할지 결정
        this.applicationEventPublisher.publishEvent(
                reservation.toSettlePayment(
                    lodging.getId(),
                    user.getId(),
                    savedReservation
                )
        );
    }

    @Transactional
    public void cancel(final ReservationDto.Cancel cancel) {
        /**
         *  1. 해당 예약이 있는지 조회한다,
         *  2. 해당 사용자가 맞는지 조회한다 ( 이후 프로세스 적용시 필요없음 )
         *  3. 해당 예약의 결제정보를 조회한다.
         *  4. 예약을 취소시킨다.
         *  5. 예약 결제정보를 취소한다. ( 외부 API )
         *      5-1 환불시킨다.
         */
        Reservation reservation = this.reservationReader.findMyReservation(cancel.reservationId());

        reservation.cancel();

        this.applicationEventPublisher.publishEvent(reservation.toRefundPayment());
    }

}
