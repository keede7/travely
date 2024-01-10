package io.keede.travely.core.external.payment.event;


import io.keede.travely.core.external.payment.publish.RefundPayment;
import io.keede.travely.core.external.payment.publish.SettlePayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.event.TransactionalEventListener;

/**
* @author keede
* Created on 2024/01/07
*/
@Slf4j
public class PaymentsEvent {

    @TransactionalEventListener
    public void refund(final RefundPayment refundPayment) {
        log.info("Refund Payment : {}", refundPayment);
    }

    @TransactionalEventListener
    public void settle(final SettlePayment settlePayment) {
        log.info("Settle Payment : {} ", settlePayment);
        /**
         *  ## 예약정보에 현재 예약에 대한 결제 진행상태를 추가한다.
         *  1. 예약정보가 저장된 데이터와 각 정보가 들어있음,
         *  2. 이 정보들을 가지고 결제를 진행시켜서 결과에 따라 다른 과정을 진행시킨다.
         */
    }

}
