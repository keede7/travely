package io.keede.travely.core.external.payment.publish;

import io.keede.travely.core.domains.reservation.entity.Reservation;

/**
* @author keede
* Created on 2024/01/07
*/
public record RefundPayment(
        Reservation reservation
) {
}
