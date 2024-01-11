package io.keede.travely.core.domains.reservation.dto;

import io.keede.travely.core.domains.payment.dto.PaymentDto;
import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.domains.payment.entity.PaymentType;

import java.math.BigDecimal;

/**
 * @author kyh
 * Created on 2023/11/25
 */
public class ReservationDto {
    public record Create(
            Long lodgingId,
            Long userId,
            PaymentDto.Paid paid
    ) {
        public Payment toPayment() {
            return Payment.pay(
                    BigDecimal.valueOf(this.getPrice()),
                    this.getPaymentType()
            );
        }

        private long getPrice() {
            return this.paid.price();
        }

        private PaymentType getPaymentType() {
            return this.paid.paymentType();
        }
    }

    public record Cancel(
            Long reservationId,
            Long userId
    ) {

    }

    public record MyReservation(
            Long reservationId
    ) {

    }

    public record MyReservations(
            Long userId
    ) {

    }
}