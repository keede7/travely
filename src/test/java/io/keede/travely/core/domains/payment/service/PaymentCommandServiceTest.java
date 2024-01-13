package io.keede.travely.core.domains.payment.service;


import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.domains.payment.entity.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.BDDMockito.*;

/**
 * @author kyh
 * Created on 2024/01/12
 */
@BusinessMockTestConfiguration
class PaymentCommandServiceTest {


    @Mock
    private PaymentRepository paymentRepository;

    private PaymentCommandService sut;

    @BeforeEach
    void setUp() {
        this.sut = new PaymentCommandService(
                this.paymentRepository
        );
    }

    @Test
    void 결제_성공() {

        Payment payment = mock(Payment.class);

        given(this.paymentRepository.save(payment))
                .willReturn(any(Payment.class));

        this.sut.create(payment);

        then(this.paymentRepository).should(times(1))
                .save(payment);

    }

}