package io.keede.travely.core.external.payment.publish;

import io.keede.travely.core.domains.payment.entity.Payment;

/**
* @author keede
* Created on 2024/01/07
*/
public record RefundPayment(
        Payment payment
) {
}
