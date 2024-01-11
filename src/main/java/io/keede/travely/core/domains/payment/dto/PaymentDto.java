package io.keede.travely.core.domains.payment.dto;

import io.keede.travely.core.domains.payment.entity.PaymentType;

/**
 * @author kyh
 * Created on 2024/01/11
 */
public class PaymentDto {

    public record Paid(
            long price,
            PaymentType paymentType
    ) {

    }

}
