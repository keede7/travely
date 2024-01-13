package io.keede.travely.core.domains.payment.service;


import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.domains.payment.entity.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kyh
 * Created on 2024/01/12
 */
@Service
public class PaymentCommandService {

    private final PaymentRepository paymentRepository;

    public PaymentCommandService(
            final PaymentRepository paymentRepository
    ) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public void create(
            final Payment paymentEntity
    ) {
        this.paymentRepository.save(paymentEntity);
    }

}
