package io.keede.travely.core.domains.payment.service;


import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.domains.payment.service.adapter.PaymentWriter;
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
    private PaymentWriter paymentWriter;

    private PaymentCommandService sut;

    @BeforeEach
    void setUp() {
        this.sut = new PaymentCommandService(
                this.paymentWriter
        );
    }

    @Test
    void 결제_성공() {

        Payment payment = mock(Payment.class);

        given(this.paymentWriter.save(payment))
                .willReturn(any(Payment.class));

        this.sut.create(payment);

        then(this.paymentWriter).should(times(1))
                .save(payment);

    }

}