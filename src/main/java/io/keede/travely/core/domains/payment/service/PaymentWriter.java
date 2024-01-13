package io.keede.travely.core.domains.payment.service;

import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.domains.payment.entity.PaymentRepository;
import org.springframework.stereotype.Service;

/**
* @author keede
* Created on 2024/01/13
*/

@Service
public class PaymentWriter {

    private final PaymentRepository paymentRepository;

    public PaymentWriter(
            final PaymentRepository paymentRepository
    ) {
        this.paymentRepository = paymentRepository;
    }

    public Payment save(Payment entity) {
        return this.paymentRepository.save(entity);
    }
}
