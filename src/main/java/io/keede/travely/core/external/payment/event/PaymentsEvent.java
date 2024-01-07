package io.keede.travely.core.external.payment.event;


import io.keede.travely.core.external.payment.publish.RefundPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.event.TransactionalEventListener;

/**
* @author keede
* Created on 2024/01/07
*/
@Slf4j
public class PaymentsEvent {

    @TransactionalEventListener
    public void refund(RefundPayment refundPayment) {
        log.info("Refund Payment : {}", refundPayment);
    }

}
