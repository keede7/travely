package io.keede.travely.core.domains.reservation.service;

import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.entity.ReservationRepository;
import io.keede.travely.core.domains.user.entity.User;
import io.keede.travely.core.domains.user.entity.UserRepository;
import io.keede.travely.core.exception.service.BusinessException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
* @author keede
* Created on 2023/11/25
*/
@Service
public class ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final LodgingRepository lodgingRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ReservationCommandService(
            final ReservationRepository reservationRepository,
            final LodgingRepository lodgingRepository,
            final UserRepository userRepository,
            final ApplicationEventPublisher applicationEventPublisher
    ) {
        this.reservationRepository = reservationRepository;
        this.lodgingRepository = lodgingRepository;
        this.userRepository = userRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void create(final ReservationDto.Create create) {
        Lodging lodging = this.lodgingRepository.findById(create.lodgingId())
                .orElseThrow(BusinessException::new);

        lodging.checkToAllowReservation();

        User user = this.userRepository.findById(create.userId())
                .orElseThrow(BusinessException::new);

        Payment payment = Payment.paid(BigDecimal.TEN);

        Reservation reservation = new Reservation(lodging, user, payment);

        Reservation savedReservation = reservationRepository.save(reservation);

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

        Reservation reservation = this.reservationRepository.findMyReservation(cancel.reservationId())
                .orElseThrow(BusinessException::new);

        reservation.cancel();

        this.applicationEventPublisher.publishEvent(reservation.toRefundPayment());
    }

}
