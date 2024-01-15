package io.keede.travely.core.domains.payment.service;


import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.domains.payment.service.adapter.PaymentWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kyh
 * Created on 2024/01/12
 */
@Service
public class PaymentCommandService {

    private final PaymentWriter paymentWriter;

    public PaymentCommandService(
            final PaymentWriter paymentWriter
    ) {
        this.paymentWriter = paymentWriter;
    }

    @Transactional
    public void create(
            final Payment paymentEntity
    ) {
        this.paymentWriter.save(paymentEntity);
    }

}
