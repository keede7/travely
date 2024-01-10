package io.keede.travely.core.external.payment.publish;


import io.keede.travely.core.domains.reservation.entity.Reservation;

/**
 * @author kyh
 * Created on 2024/01/10
 */
public record SettlePayment(
        Long lodgingId,
        Long userId,
        Reservation reservation
) {
}
